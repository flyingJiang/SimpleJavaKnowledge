package com.flying.spring;

/**
 * Spring事务传播机制
 * ----------------------------------------------
 * PROPAGATION_REQUIRED -- 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。
 * PROPAGATION_SUPPORTS -- 支持当前事务，如果当前没有事务，就以非事务方式执行。
 * PROPAGATION_MANDATORY -- 支持当前事务，如果当前没有事务，就抛出异常。
 * PROPAGATION_REQUIRES_NEW -- 新建事务，如果当前存在事务，把当前事务挂起。
 * PROPAGATION_NOT_SUPPORTED -- 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
 * PROPAGATION_NEVER -- 以非事务方式执行，如果当前存在事务，则抛出异常。
 * PROPAGATION_NESTED -- 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作。
 *
 * public interface transactiondefinition {
 *     int propagation_required = 0;
 *     int propagation_supports = 1;
 *     int propagation_mandatory = 2;
 *     int propagation_requires_new = 3;
 *     int propagation_not_supported = 4;
 *     int propagation_never = 5;
 *     int propagation_nested = 6;
 * }
 *
 * ----------------------------------
 * https://blog.csdn.net/m0_38027656/article/details/84190949?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
 * 在一个Service内部，事务方法之间的嵌套调用，普通方法和事务方法之间的嵌套调用，都不会开启新的事务.
 * 是因为spring采用动态代理机制来实现事务控制，而动态代理最终都是要调用原始对象的，而原始对象在去调用方法时，是不会再触发代理了！
 */
public class Spring {
}
