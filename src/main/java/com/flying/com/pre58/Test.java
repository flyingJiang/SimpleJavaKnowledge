package com.flying.com.pre58;

/**
 * 本人3年开发经验、18年年底开始跑路找工作，在互联网寒冬下成功拿到阿里巴巴、今日头条、58同城等公司offer，岗位是Java后端开发，最终选择去了58同城。
 * 面试了很多家公司，感觉大部分公司考察的点都差不多，所以将自己的心得记下来，希望能给正在找或者准备找工作的朋友提供一点帮助。另外，目前在58同城也做面试官的工作，身份从求职者变为面试官，看问题的很多角度也不一样，所以下文中既有求职者的视角，也有面试官的视角
 * （PS:感谢大家的支持，问我要完整面试题的希望交流指导的太多，没办法一个个发给大家，大家看下后面链接，整理成了pdf分享下给大家
 * 面试流程
 *   先说下面试流程，一般大公司都有3-4轮技术面，1轮的HR面。就58同城而言，我共经历了4轮技术面，前两轮主要是问基础和项目实现，第3轮是交叉面，两个面试官，主要是问项目实现和拓展。第4轮是部门老大面，主要就问一些架构、技术和业务的理解、个人发展比较抽象的东西了。
 * 要注意的点
 * 简历一到两页为最佳，将项目经历写2-3个左右就差不多了，一定要写最有亮点的项目
 * 工作经历的起始时间要写清楚，另外大公司都有背调，不要合并或省略一些比较短的工作经历，影响的可能不只是这次面试，而是之后可能都无法进这家公司
 * 博客没什么好文章，github没好项目就不要写在简历中了
 * 对于面试官的问题，想清楚再回答，如果觉得需要的时间可能比较长，可以跟面试官说我思考下、我整理下思路之类的
 * 面试的过程中注意语速和吐字，本人在做面试官后，发现很多人说话语速很快，或吐字不清，导致原本会的问题也会让面试官觉得你没答到点上（尤其是电话面）
 * 面试完后不要直接问面试结果
 * 技术考察
 * 总的来说，技术相关的考察主要分为两大块，一是基础，二是经验。
 * 基础包括java基础、数据库、中间件等，来自于日常的积累和面试前的准备。
 * 经验包括以往做过的项目、解决的问题、以及一些场景题（比如你的项目如果流量大了十倍如何保证可用）。
 *   本文主要说一些大概的问题，整体的进阶的技术点比较多，下面放了下链接，如果失效就可以进群获取，有些是我自己总结的，有些是收（抄）集（袭）了别人的。
 * 集合
 * 集合分为两大块：java.util包下的非线程安全集合和java.util.concurrent下的线程安全集合。
 * List
 * ArrayList与LinkedList的实现和区别
 * Map
 * HashMap：了解其数据结构、hash冲突如何解决（链表和红黑树）、扩容时机、扩容时避免rehash的优化
 * LinkedHashMap：了解基本原理、哪两种有序、如何用它实现LRU
 * TreeMap：了解数据结构、了解其key对象为什么必须要实现Compare接口、如何用它实现一致性哈希
 * Set
 * Set基本上都是由对应的map实现，简单看看就好
 * 常见问题
 * hashmap如何解决hash冲突，为什么hashmap中的链表需要转成红黑树？
 * hashmap什么时候会触发扩容？
 * jdk1.8之前并发操作hashmap时为什么会有死循环的问题？
 * hashmap扩容时每个entry需要再计算一次hash吗？
 * hashmap的数组长度为什么要保证是2的幂？
 * 如何用LinkedHashMap实现LRU？
 * 如何用TreeMap实现一致性hash？
 * 中间件、存储、以及其他框架
 * Spring
 * bean的生命周期、循环依赖问题、spring cloud（如项目中有用过）、AOP的实现、spring事务传播
 * 常见问题
 * java动态代理和cglib动态代理的区别（经常结合spring一起问所以就放这里了）
 * spring中bean的生命周期是怎样的？
 * 属性注入和构造器注入哪种会有循环依赖的问题？
 * Dubbo（或其他Rpc框架）
 * 了解一个常用RPC框架如Dubbo的实现：服务发现、路由、异步调用、限流降级、失败重试
 * 常见问题
 * Dubbo如何做负载均衡？
 * Dubbo如何做限流降级？
 * Dubbo如何优雅的下线服务？
 * Dubbo如何实现异步调用的？
 * RocketMq（或其他消息中间件）
 * 了解一个常用消息中间件如RocketMq的实现：如何保证高可用和高吞吐、消息顺序、重复消费、事务消息、延迟消息、死信队列
 * 常见问题
 * RocketMq如何保证高可用的？
 * RocketMq如何保证高吞吐的？
 * RocketMq的消息是有序的吗？
 * RocketMq的消息局部顺序是如何保证的?
 * RocketMq事务消息的实现机制？
 * RocketMq会有重复消费的问题吗？如何解决？
 * RocketMq支持什么级别的延迟消息？如何实现的？
 * RocketMq是推模型还是拉模型？
 * Consumer的负载均衡是怎么样的？
 * 不知道会不会失效,如果失效点击（723770387）或者扫描下面二维码，链接补发不过来，希望大家能理解,
 * 链接：网盘
 * 提取码：pexe
 *
 *
 *
 * Redis（或其他缓存系统）
 * redis工作模型、redis持久化、redis过期淘汰机制、redis分布式集群的常见形式、分布式锁、缓存击穿、缓存雪崩、缓存一致性问题
 * 常见问题
 * redis性能为什么高?
 * 单线程的redis如何利用多核cpu机器？
 * redis的缓存淘汰策略？
 * redis如何持久化数据？
 * redis有哪几种数据结构？
 * redis集群有哪几种形式？
 * 有海量key和value都比较小的数据，在redis中如何存储才更省内存？
 * 如何保证redis和DB中的数据一致性？
 * 如何解决缓存穿透和缓存雪崩？
 * 如何用redis实现分布式锁？
 *
 * Mysql
 * 事务隔离级别、锁、索引的数据结构、聚簇索引和非聚簇索引、最左匹配原则、查询优化（explain等命令）
 * 推荐文章：
 * -- http://hedengcheng.com/?p=771
 * -- https://tech.meituan.com/2014/06/30/mysql-index.html
 * -- http://hbasefly.com/2017/08/19/mysql-transaction/
 * 常见问题
 * Mysql(innondb 下同) 有哪几种事务隔离级别？
 * 不同事务隔离级别分别会加哪些锁？
 * mysql的行锁、表锁、间隙锁、意向锁分别是做什么的？
 * 说说什么是最左匹配？
 * 如何优化慢查询？
 * mysql索引为什么用的是b+ tree而不是b tree、红黑树
 * 分库分表如何选择分表键
 * 分库分表的情况下，查询时一般是如何做排序的？
 * zk
 * zk大致原理（可以了解下原理相近的Raft算法）、zk实现分布式锁、zk做集群master选举
 * 常见问题
 * 如何用zk实现分布式锁，与redis分布式锁有和优缺点
 */
public class Test {
}
