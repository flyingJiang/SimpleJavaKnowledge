package com.flying.jobQuartz;

/**
 * https://www.cnblogs.com/aspirant/p/9099072.html
 *
 * 一、核心概念
 *
 * Quartz的原理不是很复杂，只要搞明白几个概念，然后知道如何去启动和关闭一个调度程序即可。
 *
 * 1、Job
 * 表示一个工作，要执行的具体内容。此接口中只有一个方法
 * void execute(JobExecutionContext context)
 * 2、JobDetail
 *
 * JobDetail表示一个具体的可执行的调度程序，Job是这个可执行程调度程序所要执行的内容，另外JobDetail还包含了这个任务调度的方案和策略。
 *
 * 3、Trigger代表一个调度参数的配置，什么时候去调。
 *
 * 4、Scheduler代表一个调度容器，一个调度容器中可以注册多个JobDetail和Trigger。当Trigger与JobDetail组合，就可以被Scheduler容器调度了。
 *
 * Cron表达式的格式：秒 分 时 日 月 周 年(可选)。
 * 字段名              允许的值                    允许的特殊字符
 * 秒                     0-59                           , - * /
 * 分                     0-59                           , - * /
 * 小时                  0-23                           , - * /
 * 日                     1-31                            , - * ? / L W C
 * 月                     1-12 or JAN-DEC        , - * /
 * 周几                  1-7 or SUN-SAT         , - * ? / L C #
 * 年(可选字段)     empty                         1970-2099 , - * /
 *
 * 四、总结
 *
 * 1、搞清楚了上Quartz容器执行作业的的原理和过程，以及作业形成的方式，作业注册到容器的方法。就认识明白了Quartz的核心原理。
 *
 * 2、Quartz虽然很庞大，但是一切都围绕这个核心转，为了配置强大时间调度策略，可以研究专门的CronTrigger。要想灵活配置作业和容器属性，可以通过Quartz的properties文件或者XML来实现。
 *
 * 3、要想调度更多的持久化、结构化作业，可以通过数据库读取作业，然后放到容器中执行。
 *
 * 4、所有的一切都围绕这个核心原理转，搞明白这个了，再去研究更高级用法就容易多了。
 *
 * 5、Quartz与Spring的整合也非常简单，Spring提供一组Bean来支持：MethodInvokingJobDetailFactoryBean、SimpleTriggerBean、SchedulerFactoryBean，
 * 看看里面需要注入什么属性即可明白了。Spring会在Spring容器启动时候，启动Quartz容器。
 *
 * 6、Quartz容器的关闭方式也很简单，如果是Spring整合，则有两种方法，一种是关闭Spring容器，一种是获取到SchedulerFactoryBean实例，
 * 然后调用一个shutdown就搞定了。如果是Quartz独立使用，则直接调用scheduler.shutdown(true);
 *
 * 7、Quartz的JobDetail、Trigger都可以在运行时重新设置，并且在下次调用时候起作用。这就为动态作业的实现提供了依据。
 * 你可以将调度时间策略存放到数据库，然后通过数据库数据来设定Trigger，这样就能产生动态的调度。
 */
public class QuartzJob {
}
