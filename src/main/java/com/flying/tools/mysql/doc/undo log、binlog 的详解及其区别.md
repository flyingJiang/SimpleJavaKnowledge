# [redo/undo log、binlog 的详解及其区别](https://www.jianshu.com/p/57c510f4ec28)

 划重点：

 1. redo log 通常是 物理 日志，记录的是 数据页 的物理修改，而不是某一行或某几行修改成怎样怎样，它用来恢复提交后的物理数据页(恢复数据页，且只能恢复到最后一次提交的位置)。
 2. undo log 用来回滚行记录到某个版本。undo log 一般是逻辑日志，根据每行记录进行记录。

 Redo 记录某 数据块 被修改 后 的值，可以用来恢复未写入 data file 的已成功事务更新的数据。
 Undo 记录某 数据 被修改 前 的值，可以用来在事务失败时进行 rollback；

 Redo Log 保证事务的持久性
 Undo Log 保证事务的原子性（在 InnoDB 引擎中，还用 Undo Log 来实现 MVCC）

 redo/undo log 和 binlog
 两者区别还是挺多的，大致如下，

 层次不同。redo/undo 是 innodb 引擎层维护的，而 binlog 是 mysql server 层维护的，跟采用何种引擎没有关系，记录的是所有引擎的更新操作的日志记录。
 记录内容不同。redo/undo 记录的是 每个页/每个数据 的修改情况，属于物理日志+逻辑日志结合的方式（redo log 是物理日志，undo log 是逻辑日志）。binlog 记录的都是事务操作内容，binlog 有三种模式：Statement（基于 SQL 语句的复制）、Row（基于行的复制） 以及 Mixed（混合模式）。不管采用的是什么模式，当然格式是二进制的，
 记录时机不同。redo/undo 在 事务执行过程中 会不断的写入，而 binlog 是在 事务最终提交前 写入的。binlog 什么时候刷新到磁盘跟参数 sync_binlog 相关。

 binlog 三种模式对比
 上面提到 binlog 有三种格式，各有优缺点：
 >
 statement：基于SQL语句的模式，某些语句中含有一些函数，例如 UUID NOW 等在复制过程可能导致数据不一致甚至出错。
 row：基于行的模式，记录的是行的变化，很安全。但是 binlog 的磁盘占用会比其他两种模式大很多，在一些大表中清除大量数据时在 binlog 中会生成很多条语句，可能导致从库延迟变大。
 mixed：混合模式，根据语句来选用是 statement 还是 row 模式。
