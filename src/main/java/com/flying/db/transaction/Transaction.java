package com.flying.db.transaction;

/**
 *
 * 面试官：你说熟悉MySQL事务，那来谈谈事务的实现原理吧！
 * 原子性(Atomicity),一致性(Consistency),隔离型(Isolation)以及持久性(Durability)
 *
 * 事务想要做到什么效果？
 * 按我理解，无非是要做到可靠性以及并发处理
 *
 * 实现事务功能的三个技术，分别是日志文件(redo log 和 undo log)，锁技术以及MVCC(多版本并发控制)
 *
 * redo log是用来恢复数据的 用于保障，已提交事务的持久化特性
 * undo log是用来回滚数据的用于保障 未提交事务的原子性
 *
 * 通过读写锁，可以做到读读可以并行，但是不能做到写读，写写并行
 * 事务的隔离性就是根据读写锁来实现的！！！
 *
 * MVCC (MultiVersion Concurrency Control) 叫做多版本并发控制。
 * InnoDB的 MVCC ，是通过在每行记录的后面保存两个隐藏的列来实现的。这两个列， 一个保存了行的创建时间，一个保存了行的过期时间，当然存储的并不是实际的时间值，而是系统版本号。
 * 以上片段摘自《高性能Mysql》这本书对MVCC的定义。他的主要实现思想是通过数据多版本来做到读写分离。从而实现不加锁读进而做到读写并行。
 * MVCC在mysql中的实现依赖的是undo log与read view
 * undo log :undo log 中记录某行数据的多个版本的数据。
 * read view :用来判断当前版本数据的可见性
 *
 * Mysql 隔离级别有以下四种（级别由低到高）：
 * READ UNCOMMITED (未提交读)
 * READ COMMITED (提交读)
 * REPEATABLE READ (可重复读) REPEATABLE READ(Mysql默认隔离级别)
 * SERIALIZABLE (可重复读)
 *
 *
 * 实现事务采取了哪些技术以及思想？
 * 原子性：使用 undo log ，从而达到回滚
 * 持久性：使用 redo log，从而达到故障后恢复
 * 隔离性：使用锁以及MVCC,运用的优化思想有读写分离，读读并行，读写并行
 * 一致性：通过回滚，以及恢复，和在并发环境下的隔离做到一致性。
 *
 */
public class Transaction {
}
