# [RejectedExecutionHandler 线程池四种拒绝任务策略](https://blog.csdn.net/yancychas/article/details/97396366?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-9.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-9.nonecase)
《Java线程池》：任务拒绝策略
在没有分析线程池原理之前先来分析下为什么有任务拒绝的情况发生。

这里先假设一个前提：线程池有一个任务队列，用于缓存所有待处理的任务，正在处理的任务将从任务队列中移除。因此在任务队列长度有限的情况下就会出现新任务的拒绝处理问题，需要有一种策略来处理应该加入任务队列却因为队列已满无法加入的情况。另外在线程池关闭的时候也需要对任务加入队列操作进行额外的协调处理。

RejectedExecutionHandler提供了四种方式来处理任务拒绝策略
1、直接丢弃（DiscardPolicy）
2、丢弃队列中最老的任务(DiscardOldestPolicy)。
3、抛异常(AbortPolicy)
4、将任务分给调用线程来执行(CallerRunsPolicy)。

这四种策略是独立无关的，是对任务拒绝处理的四中表现形式。最简单的方式就是直接丢弃任务。但是却有两种方式，到底是该丢弃哪一个任务，比如可以丢弃当前将要加入队列的任务本身（DiscardPolicy）或者丢弃任务队列中最旧任务（DiscardOldestPolicy）。丢弃最旧任务也不是简单的丢弃最旧的任务，而是有一些额外的处理。除了丢弃任务还可以直接抛出一个异常（RejectedExecutionException），这是比较简单的方式。抛出异常的方式（AbortPolicy）尽管实现方式比较简单，但是由于抛出一个RuntimeException，因此会中断调用者的处理过程。除了抛出异常以外还可以不进入线程池执行，在这种方式（CallerRunsPolicy）中任务将有调用者线程去执行。
