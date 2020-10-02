# 消费者带来的问题


* 1 这种写法，程序启动日志是正常的，但是无法设置队列持久化属性；可以通过config，这样就等价于情况2
```java
    @RabbitListener(queues = "${t2message.rabbitmq.queue}")
    public void receiveMessage(Message message) {}
```
* 2 其实我想解决的也只是这个日志中的Error
```java
    @RabbitListener(queuesToDeclare = @Queue(value = "${t2message.rabbitmq.queue}", durable = "true"))
    public void receiveMessage(Message message) {}
```

```text
2020-10-02 15:08:03.782 [ERROR] [0.67.39.16:5011] [o.s.a.r.c.CachingConnectionFactory      ] - Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=403, reply-text=ACCESS_REFUSED - access to queue 't2.payment.result.queue' in vhost 't2-payment-result' refused for user 't2-payment', class-id=50, method-id=10)
2020-10-02 15:08:03.791 [ WARN] [           main] [o.s.amqp.rabbit.core.RabbitAdmin        ] - Failed to declare queue: Queue [name=t2.payment.result.queue, durable=true, autoDelete=false, exclusive=false, arguments={}, actualName=t2.payment.result.queue], continuing... com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=403, reply-text=ACCESS_REFUSED - access to queue 't2.payment.result.queue' in vhost 't2-payment-result' refused for user 't2-payment', class-id=50, method-id=10)
```  
* 3
```java
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${t2message.rabbitmq.queue}", durable = "true"),
            exchange = @Exchange(value = "${t2message.rabbitmq.exchange}"),
            key = "${t2message.rabbitmq.routing-key}")
    )
    public void receiveMessage(Message message) {}
```

```text
2020-10-02 14:18:18.227 [ERROR] [0.67.39.17:5011] [o.s.a.r.c.CachingConnectionFactory      ] - Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=403, reply-text=ACCESS_REFUSED - access to exchange 't2.payment.direct' in vhost 't2-payment-result' refused for user 't2-payment', class-id=40, method-id=10)
2020-10-02 14:18:18.235 [ WARN] [           main] [o.s.amqp.rabbit.core.RabbitAdmin        ] - Failed to declare exchange: Exchange [name=t2.payment.direct, type=direct, durable=true, autoDelete=false, internal=false, arguments={}], continuing... com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=403, reply-text=ACCESS_REFUSED - access to exchange 't2.payment.direct' in vhost 't2-payment-result' refused for user 't2-payment', class-id=40, method-id=10)
2020-10-02 14:18:18.248 [ERROR] [0.67.39.17:5011] [o.s.a.r.c.CachingConnectionFactory      ] - Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=403, reply-text=ACCESS_REFUSED - access to queue 't2.payment.result.queue' in vhost 't2-payment-result' refused for user 't2-payment', class-id=50, method-id=10)
2020-10-02 14:18:18.261 [ WARN] [           main] [o.s.amqp.rabbit.core.RabbitAdmin        ] - Failed to declare queue: Queue [name=t2.payment.result.queue, durable=true, autoDelete=false, exclusive=false, arguments={}, actualName=t2.payment.result.queue], continuing... com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=403, reply-text=ACCESS_REFUSED - access to queue 't2.payment.result.queue' in vhost 't2-payment-result' refused for user 't2-payment', class-id=50, method-id=10)
```

## 解决
[遇到这样的问题，网上搜索了一下](https://blog.csdn.net/weixin_38319645/article/details/91983299)
> 经过排查发现，rabbitmq admin用户对 / 目录的访问权限不足，通过对admin用户赋权即可成功访问.
>>  rabbitmqctl set_permissions -p / admin .* .* .*


