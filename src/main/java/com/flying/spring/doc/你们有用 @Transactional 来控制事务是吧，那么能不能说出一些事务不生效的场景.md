# [@Transactional 注解不生效原因](https://www.cnblogs.com/codingmengmeng/p/12111392.html)


> 1、检查你的方法是不是public的。@Transactional注解只能应用到public可见度的方法上，如果应用在protected、private或者package可见度的方法上，也不会报错，不过事务设置不会起作用。

  2、检查你的异常类型是不是unchecked异常。默认情况下，Spring会对unchecked异常进行事务回滚，如果是checked异常则不回滚。如空指针异常、算术异常等，会被回滚；文件读写、网络出问题，spring就没法回滚了。如果你想check异常也回滚怎么办，注解上面写明异常类型即可：

  @Transactional(rollbackFor = Exception.class)
  类型的还有norollbackFor，自定义不回滚的异常。

  3、是否在service中进行了try...catch的操作，由于已经被捕获异常，故事务也不会回滚。如果非要在service中try...catch异常，又想要事务回滚，可在catch块中抛出运行时异常：

  try{
      ....
  }catch(Exception e){
      logger.error("",e);
      throw new RuntimeException;
  }
  这种方法有个不足之处，就是不能在catch块中存在return子句，若想捕获异常时回滚事务，同时返回提示信息，可以使用手动回滚：

  复制代码
  try{
      ...
  }catch(Exception e){
      logger.error("",e);
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      return ERROR_MESSAGE;
  }
  复制代码
  PS:另外说明一下，在controller层捕获了service层的异常，事务还会回滚吗？答案是会的，只要你service层抛出了异常，并且你加的事务可以处理这个异常，也就是rollbackFor = Exception.class这个符合你抛出的异常，不管外面有没有捕获都可以回滚。

  4、是否开启了对注解的解析：

  <bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager"
            id="transactionManager">
          <property name="dataSource" ref="dataSource"/>
  </bean>
  <tx:annotation-driven transaction-manager="transactionManager" proxy-target-class="true"/>
  5、数据库引擎要支持事务，如果是mysql，注意表要使用支持事务的引擎，比如innodb，如果是myisam，事务是不起作用的。

  6、spring是否扫描到你这个包，如下是扫描到org.test下面的包：

  <context:component-scan base-package="org.test" ></context:component-scan>
  7、检查是不是同一个类中的方法调用（如A方法无@Transactional注解，调用了一个有@Transactional注解的方法），这样事务也是不生效的。原因可参照如下文章：

  　　https://blog.csdn.net/levae1024/article/details/82998386

  　　https://blog.csdn.net/gx_hxl/article/details/80808088

  　　https://blog.csdn.net/m0_38027656/article/details/84190949
