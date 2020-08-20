# [RabbitMQ之消息确认机制（事务+Confirm）](https://blog.csdn.net/u013256816/article/details/55515234)

## 概述
   在使用RabbitMQ的时候，我们可以通过消息持久化操作来解决因为服务器的异常奔溃导致的消息丢失，除此之外我们还会遇到一个问题，当消息的发布者在将消息发送出去之后，消息到底有没有正确到达broker代理服务器呢？如果不进行特殊配置的话，默认情况下发布操作是不会返回任何信息给生产者的，也就是默认情况下我们的生产者是不知道消息有没有正确到达broker的，如果在消息到达broker之前已经丢失的话，持久化操作也解决不了这个问题，因为消息根本就没到达代理服务器，你怎么进行持久化，那么这个问题该怎么解决呢？

   RabbitMQ为我们提供了两种方式：

   * 通过AMQP事务机制实现，这也是AMQP协议层面提供的解决方案；
   * 通过将channel设置成confirm模式来实现；

## 事务机制
   这里首先探讨下RabbitMQ事务机制。

   RabbitMQ中与事务机制有关的方法有三个：txSelect(), txCommit()以及txRollback(), txSelect用于将当前channel设置成transaction模式，txCommit用于提交事务，txRollback用于回滚事务，在通过txSelect开启事务之后，我们便可以发布消息给broker代理服务器了，如果txCommit提交成功了，则消息一定到达了broker了，如果在txCommit执行之前broker异常崩溃或者由于其他原因抛出异常，这个时候我们便可以捕获异常通过txRollback回滚事务了。
   ```java
   channel.txSelect();
   channel.basicPublish(ConfirmConfig.exchangeName, ConfirmConfig.routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, ConfirmConfig.msg_10B.getBytes());
   channel.txCommit();

   try {
       channel.txSelect();
       channel.basicPublish(exchange, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
       int result = 1 / 0;
       channel.txCommit();
   } catch (Exception e) {
       e.printStackTrace();
       channel.txRollback();
   }
   ```
## Confirm模式
   概述
   上面我们介绍了RabbitMQ可能会遇到的一个问题，即生成者不知道消息是否真正到达broker，随后通过AMQP协议层面为我们提供了事务机制解决了这个问题，但是采用事务机制实现会降低RabbitMQ的消息吞吐量，那么有没有更加高效的解决方式呢？答案是采用Confirm模式。

   producer端confirm模式的实现原理
   生产者将信道设置成confirm模式，一旦信道进入confirm模式，所有在该信道上面发布的消息都会被指派一个唯一的ID(从1开始)，一旦消息被投递到所有匹配的队列之后，broker就会发送一个确认给生产者（包含消息的唯一ID）,这就使得生产者知道消息已经正确到达目的队列了，如果消息和队列是可持久化的，那么确认消息会将消息写入磁盘之后发出，broker回传给生产者的确认消息中deliver-tag域包含了确认消息的序列号，此外broker也可以设置basic.ack的multiple域，表示到这个序列号之前的所有消息都已经得到了处理。

   confirm模式最大的好处在于他是异步的，一旦发布一条消息，生产者应用程序就可以在等信道返回确认的同时继续发送下一条消息，当消息最终得到确认之后，生产者应用便可以通过回调方法来处理该确认消息，如果RabbitMQ因为自身内部错误导致消息丢失，就会发送一条nack消息，生产者应用程序同样可以在回调方法中处理该nack消息。

   在channel 被设置成 confirm 模式之后，所有被 publish 的后续消息都将被 confirm（即 ack） 或者被nack一次。但是没有对消息被 confirm 的快慢做任何保证，并且同一条消息不会既被 confirm又被nack 。

   开启confirm模式的方法
   生产者通过调用channel的confirmSelect方法将channel设置为confirm模式，如果没有设置no-wait标志的话，broker会返回confirm.select-ok表示同意发送者将当前channel信道设置为confirm模式(从目前RabbitMQ最新版本3.6来看，如果调用了channel.confirmSelect方法，默认情况下是直接将no-wait设置成false的，也就是默认情况下broker是必须回传confirm.select-ok的)。

> 已经在transaction事务模式的channel是不能再设置成confirm模式的，即这两种模式是不能共存的。

## 编程模式
   对于固定消息体大小和线程数，如果消息持久化，生产者confirm(或者采用事务机制)，消费者ack那么对性能有很大的影响.

   消息持久化的优化没有太好方法，用更好的物理存储（SAS, SSD, RAID卡）总会带来改善。生产者confirm这一环节的优化则主要在于客户端程序的优化之上。归纳起来，客户端实现生产者confirm有三种编程方式：

   普通confirm模式：每发送一条消息后，调用waitForConfirms()方法，等待服务器端confirm。实际上是一种串行confirm了。
   ```java
   channel.basicPublish(ConfirmConfig.exchangeName, ConfirmConfig.routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, ConfirmConfig.msg_10B.getBytes());
   if(!channel.waitForConfirms()){
   	System.out.println("send message failed.");
   }
   ```
   批量confirm模式：每发送一批消息后，调用waitForConfirms()方法，等待服务器端confirm。
   ```java
   channel.confirmSelect();
   for(int i=0;i<batchCount;i++){
   	channel.basicPublish(ConfirmConfig.exchangeName, ConfirmConfig.routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, ConfirmConfig.msg_10B.getBytes());
   }
   if(!channel.waitForConfirms()){
   	System.out.println("send message failed.");
   }
   ```
   异步confirm模式：提供一个回调方法，服务端confirm了一条或者多条消息后Client端会回调这个方法。
   ```java
    SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
    channel.confirmSelect();
           channel.addConfirmListener(new ConfirmListener() {
               public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                   if (multiple) {
                       confirmSet.headSet(deliveryTag + 1).clear();
                   } else {
                       confirmSet.remove(deliveryTag);
                   }
               }
               public void handleNack(long deliveryTag, boolean multiple) throws IOException {
               	System.out.println("Nack, SeqNo: " + deliveryTag + ", multiple: " + multiple);
                   if (multiple) {
                       confirmSet.headSet(deliveryTag + 1).clear();
                   } else {
                       confirmSet.remove(deliveryTag);
                   }
               }
           });

           while (true) {
               long nextSeqNo = channel.getNextPublishSeqNo();
               channel.basicPublish(ConfirmConfig.exchangeName, ConfirmConfig.routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, ConfirmConfig.msg_10B.getBytes());
               confirmSet.add(nextSeqNo);
           }
   ```

## 消息确认（Consumer端）
   为了保证消息从队列可靠地到达消费者，RabbitMQ提供消息确认机制(message acknowledgment)。消费者在声明队列时，可以指定noAck参数，当noAck=false时，RabbitMQ会等待消费者显式发回ack信号后才从内存(和磁盘，如果是持久化消息的话)中移去消息。否则，RabbitMQ会在队列中消息被消费后立即删除它。

   **采用消息确认机制后，只要令noAck=false**，消费者就有足够的时间处理消息(任务)，不用担心处理消息过程中消费者进程挂掉后消息丢失的问题，因为RabbitMQ会一直持有消息直到消费者显式调用basicAck为止。

   当noAck=false时，对于RabbitMQ服务器端而言，队列中的消息分成了两部分：一部分是等待投递给消费者的消息；一部分是已经投递给消费者，但是还没有收到消费者ack信号的消息。如果服务器端一直没有收到消费者的ack信号，并且消费此消息的消费者已经断开连接，则服务器端会安排该消息重新进入队列，等待投递给下一个消费者（也可能还是原来的那个消费者）。

   **RabbitMQ不会为未ack的消息设置超时时间，它判断此消息是否需要重新投递给消费者的唯一依据是消费该消息的消费者连接是否已经断开。这么设计的原因是RabbitMQ允许消费者消费一条消息的时间可以很久很久。**

   RabbitMQ管理平台界面上可以看到当前队列中Ready状态和Unacknowledged状态的消息数，分别对应上文中的等待投递给消费者的消息数和已经投递给消费者但是未收到ack信号的消息数。也可以通过命令行来查看上述信息：

   代码示例（关闭自动消息确认，进行手动ack）：
   ```java
   QueueingConsumer consumer = new QueueingConsumer(channel);
           channel.basicConsume(ConfirmConfig.queueName, false, consumer);

           while(true){
               QueueingConsumer.Delivery delivery = consumer.nextDelivery();
               String msg = new String(delivery.getBody());
        // do something with msg.
               channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
           }
   ```
   broker将在下面的情况中对消息进行confirm：

   * broker发现当前消息无法被路由到指定的queues中（如果设置了mandatory属性，则broker会发送basic.return）
   * 非持久属性的消息到达了其所应该到达的所有queue中（和镜像queue中）
   * 持久消息到达了其所应该到达的所有queue中（和镜像中），并被持久化到了磁盘（fsync）
   * 持久消息从其所在的所有queue中被consume了（如果必要则会被ack）

   basicRecover：是路由不成功的消息可以使用recovery重新发送到队列中。
   basicReject：是接收端告诉服务器这个消息我拒绝接收,不处理,可以设置是否放回到队列中还是丢掉，而且只能一次拒绝一个消息,官网中有明确说明不能批量拒绝消息，为解决批量拒绝消息才有了basicNack。
   basicNack：可以一次拒绝N条消息，客户端可以设置basicNack方法的multiple参数为true，服务器会拒绝指定了delivery_tag的所有未确认的消息(tag是一个64位的long值，最大值是9223372036854775807)。

# [RabbitMQ：消息发送确认 与 消息接收确认（ACK）](https://blog.csdn.net/weixin_34209406/article/details/93278781?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase)
  消息可靠总结
  持久化
    exchange要持久化
    queue要持久化
    message要持久化
  消息确认
    启动消费返回（@ReturnList注解，生产者就可以知道哪些消息没有发出去）
    生产者和Server（broker）之间的消息确认
    消费者和Server（broker）之间的消息确认














