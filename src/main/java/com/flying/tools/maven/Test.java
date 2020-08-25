package com.flying.tools.maven;

/**
 * 打开 Terminal 控制台，在当前项目（pom文件）路径下输入命令：
 *
 * mvn dependency:tree
 *
 * 举例
 * A依赖于B及C，而B又依赖于X、Y，而C依赖于X、M，则A除引B及C的依赖包下，还会引入X，Y，M的依赖包（一般情况下了，Maven可通过<scope>等若干种方式控制传递依赖）。
 * 这里有一个需要特别注意的，即B和C同时依赖于X，假设B依赖于X的1.0版本，而C依赖于X的2.0版本，A究竟依赖于X的1.0还是2.0版本呢？
 * 这就看Classloader的加载顺序了，假设Classloader先加载X_1.0，而它就不会再加载X_2.0了，如果A恰恰希望使用X_2.0呢，血案就这样不期而遇了。
 *
 * 三大技巧
 * 第一板斧:找到传递依赖的鬼出在哪里？
 *
 * dependency:tree是把照妖照，pom.xml用它照照，所有传递性依赖都将无处遁形，并且会以层级树方式展现，非常直观。
 *
 以下就是执行dependency:tree后的一个输出：
 引用

 [INFO] --- maven-dependency-plugin:2.1:tree (default-cli) @ euler-foundation ---
 [INFO] com.hsit:euler-foundation:jar:0.9.0.1-SNAPSHOT
 [INFO] +- com.rop:rop:jar:1.0.1:compile
 [INFO] |  +- org.slf4j:slf4j-api:jar:1.7.5:compile
 [INFO] |  +- org.slf4j:slf4j-log4j12:jar:1.7.5:compile
 [INFO] |  +- log4j:log4j:jar:1.2.16:compile
 [INFO] |  +- commons-lang:commons-lang:jar:2.6:compile
 [INFO] |  +- commons-codec:commons-codec:jar:1.6:compile
 [INFO] |  +- javax.validation:validation-api:jar:1.0.0.GA:compile
 [INFO] |  +- org.hibernate:hibernate-validator:jar:4.2.0.Final:compile
 [INFO] |  +- org.codehaus.jackson:jackson-core-asl:jar:1.9.5:compile
 [INFO] |  +- org.codehaus.jackson:jackson-mapper-asl:jar:1.9.5:compile
 [INFO] |  +- org.codehaus.jackson:jackson-jaxrs:jar:1.9.5:compile
 [INFO] |  +- org.codehaus.jackson:jackson-xc:jar:1.9.5:compile
 [INFO] |  \- com.fasterxml.jackson.dataformat:jackson-dataformat-xml:jar:2.2.3:compile
 [INFO] |     +- com.fasterxml.jackson.core:jackson-core:jar:2.2.3:compile
 [INFO] |     +- com.fasterxml.jackson.core:jackson-annotations:jar:2.2.3:compile
 [INFO] |     +- com.fasterxml.jackson.core:jackson-databind:jar:2.2.3:compile
 [INFO] |     +- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.2.3:compile
 [INFO] |     \- org.codehaus.woodstox:stax2-api:jar:3.1.1:compile
 [INFO] |        \- javax.xml.stream:stax-api:jar:1.0-2:compile


 刚才吹嘘dependency:tree时，我用到了“无处遁形”，其实有时你会发现简单地用dependency:tree往往并不能查看到所有的传递依赖。不过如果你真的想要看所有的，必须得加一个-Dverbose参数，这时就必定是最全的了。
 全是全了，但显示出来的东西太多，头晕目眩，有没有好法呢？当然有了，加上Dincludes或者Dexcludes说出你喜欢或讨厌，dependency:tree就会帮你过滤出来：
 引用
 Dincludes=org.springframework:spring-tx
 过滤串使用groupId:artifactId:version的方式进行过滤，可以不写全啦，如：

 mvn dependency:tree -Dverbose -Dincludes=asm:asm

 就会出来asm依赖包的分析信息：

 [INFO] --- maven-dependency-plugin:2.1:tree (default-cli) @ ridge-test ---
 [INFO] com.ridge:ridge-test:jar:1.0.2-SNAPSHOT
 [INFO] +- asm:asm:jar:3.2:compile
 [INFO] \- org.unitils:unitils-dbmaintainer:jar:3.3:compile
 [INFO]    \- org.hibernate:hibernate:jar:3.2.5.ga:compile
 [INFO]       +- cglib:cglib:jar:2.1_3:compile
 [INFO]       |  \- (asm:asm:jar:1.5.3:compile - omitted for conflict with 3.2)
 [INFO]       \- (asm:asm:jar:1.5.3:compile - omitted for conflict with 3.2)
 [INFO] ------------------------------------------------------------------------

 对asm有依赖有一个直接的依赖(asm:asm:jar:3.2)还有一个传递进入的依赖(asm:asm:jar:1.5.3)

 第二板斧:将不想要的传递依赖剪除掉

 承上，假设我们不希望asm:asm:jar:1.5.3出现，根据分析，我们知道它是经由org.unitils:unitils-dbmaintainer:jar:3.3引入的，那么在pom.xml中找到这个依赖，做其它的调整：

 <dependency>
 <groupId>org.unitils</groupId>
 <artifactId>unitils-dbmaintainer</artifactId>
 <version>${unitils.version}</version>
 <exclusions>
 <exclusion>
 <artifactId>dbunit</artifactId>
 <groupId>org.dbunit</groupId>
 </exclusion>
 <!-- 这个就是我们要加的片断 -->
 <exclusion>
 <artifactId>asm</artifactId>
 <groupId>asm</groupId>
 </exclusion>
 </exclusions>
 </dependency>


 再分析一下，你可以看到传递依赖没有了：


 [INFO]
 [INFO] --- maven-dependency-plugin:2.1:tree (default-cli) @ ridge-test ---
 [INFO] com.ridge:ridge-test:jar:1.0.2-SNAPSHOT
 [INFO] \- asm:asm:jar:3.2:compile
 [INFO] ------------------------------------------------------------------------
 [INFO] BUILD SUCCESS



 第三板斧:查看运行期类来源的JAR包

 *
 *
 */
public class Test {
}
