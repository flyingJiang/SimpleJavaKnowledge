# [RabbitMQ之消息持久化](https://blog.csdn.net/u013256816/article/details/60875666/)

## queue的持久化
   **queue的持久化是通过durable=true来实现的。**
   一般程序中这么使用：
   ```java
   Connection connection = connectionFactory.newConnection();
   Channel channel = connection.createChannel();
   channel.queueDeclare("queue.persistent.name", true, false, false, null);
   ```
   关键的是第二个参数设置为true,即durable=true.
   ```java
   Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,
                                    Map<String, Object> arguments) throws IOException;
   ```
   queue：queue的名称
   exclusive：排他队列，如果一个队列被声明为排他队列，该队列仅对首次申明它的连接可见，并在连接断开时自动删除。这里需要注意三点：
   * 1. 排他队列是基于连接可见的，同一连接的不同信道是可以同时访问同一连接创建的排他队列；
   * 2.“首次”，如果一个连接已经声明了一个排他队列，其他连接是不允许建立同名的排他队列的，这个与普通队列不同；
   * 3.即使该队列是持久化的，一旦连接关闭或者客户端退出，该排他队列都会被自动删除的，这种队列适用于一个客户端发送读取消息的应用场景。
   autoDelete：自动删除，如果该队列没有任何订阅的消费者的话，该队列会被自动删除。这种队列适用于临时队列。
   ```java
   Queue.DeclareOk queueDeclare() throws IOException;
   Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,
                                    Map<String, Object> arguments) throws IOException;
   void queueDeclareNoWait(String queue, boolean durable, boolean exclusive, boolean autoDelete,
                               Map<String, Object> arguments) throws IOException;
   Queue.DeclareOk queueDeclarePassive(String queue) throws IOException;
   ```
   其中需要说明的是queueDeclarePassive(String queue)可以用来检测一个queue是否已经存在。如果该队列存在，则会返回true；如果不存在，就会返回异常，但是不会创建新的队列。

## 消息的持久化
   设置消息的持久化
   ```java
   channel.basicPublish("exchange.persistent", "persistent", MessageProperties.PERSISTENT_TEXT_PLAIN, "persistent_test_message".getBytes());
   ```
   这里的关键是：MessageProperties.PERSISTENT_TEXT_PLAIN
   首先看一下basicPublish的方法：
   ```java
   void basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body) throws IOException;
   void basicPublish(String exchange, String routingKey, boolean mandatory, BasicProperties props, byte[] body)
           throws IOException;
   void basicPublish(String exchange, String routingKey, boolean mandatory, boolean immediate, BasicProperties props, byte[] body)
           throws IOException;
   ```

   ```java
   public BasicProperties(
               String contentType,//消息类型如：text/plain
               String contentEncoding,//编码
               Map<String,Object> headers,
               Integer deliveryMode,//1:nonpersistent 2:persistent
               Integer priority,//优先级
               String correlationId,
               String replyTo,//反馈队列
               String expiration,//expiration到期时间
               String messageId,
               Date timestamp,
               String type,
               String userId,
               String appId,
               String clusterId)
   ```
   这里的deliveryMode=1代表不持久化，deliveryMode=2代表持久化。
   ```java
   public static final BasicProperties PERSISTENT_TEXT_PLAIN =
       new BasicProperties("text/plain",
                           null,
                           null,
                           2,
                           0, null, null, null,
                           null, null, null, null,
                           null, null);
   ```
   可以看到这其实就是讲**deliveryMode设置为2**的BasicProperties的对象，为了方便编程而出现的一个东东。
   换一种实现方式：

## exchange的持久化
   ```java
   Exchange.DeclareOk exchangeDeclare(String exchange, String type, boolean durable) throws IOException;
   Exchange.DeclareOk exchangeDeclare(String exchange, String type, boolean durable, boolean autoDelete,
                                      Map<String, Object> arguments) throws IOException;
   Exchange.DeclareOk exchangeDeclare(String exchange, String type) throws IOException;
   Exchange.DeclareOk exchangeDeclare(String exchange,
                                             String type,
                                             boolean durable,
                                             boolean autoDelete,
                                             boolean internal,
                                             Map<String, Object> arguments) throws IOException;
   void exchangeDeclareNoWait(String exchange,
                              String type,
                              boolean durable,
                              boolean autoDelete,
                              boolean internal,
                              Map<String, Object> arguments) throws IOException;
   Exchange.DeclareOk exchangeDeclarePassive(String name) throws IOException;
   ```
   一般只需要：channel.exchangeDeclare(exchangeName, “direct/topic/header/fanout”, true);即**在声明的时候讲durable字段设置为true即可**。


# [RabbitMQ之mandatory和immediate](https://blog.csdn.net/u013256816/article/details/54914525)
  mandatory和immediate是AMQP协议中basic.publish方法中的两个标识位，它们都有当消息传递过程中不可达目的地时将消息返回给生产者的功能。对于刚开始接触RabbitMQ的朋友特别容易被这两个参数搞混，这里博主整理了写资料，简单讲解下这两个标识位。

  mandatory
  当mandatory标志位设置为true时，如果exchange根据自身类型和消息routeKey无法找到一个符合条件的queue，那么会调用basic.return方法将消息返回给生产者（Basic.Return + Content-Header + Content-Body）；当mandatory设置为false时，出现上述情形broker会直接将消息扔掉。

  immediate
  当immediate标志位设置为true时，如果exchange在将消息路由到queue(s)时发现对于的queue上么有消费者，那么这条消息不会放入队列中。当与消息routeKey关联的所有queue（一个或者多个）都没有消费者时，该消息会通过basic.return方法返还给生产者。

  概括来说，mandatory标志告诉服务器至少将该消息route到一个队列中，否则将消息返还给生产者；immediate标志告诉服务器如果该消息关联的queue上有消费者，则马上将消息投递给它，如果所有queue都没有消费者，直接把消息返还给生产者，不用将消息入队列等待消费者了。
