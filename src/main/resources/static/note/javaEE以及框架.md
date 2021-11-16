# JAVAWEB

## 1.第一个javaWeb程序

# Spring

### 1.javaSpring-Mevan项目导入Spring

- 概述：**Spring相当于一个been的容器**，Spring所有封装对象包过SpringBoot最底层都是`@Component`，在Mevan项目中导入Spring需要导入两个组件包spring-webmvc和spring-jdbc

- **Spring里面IOC和AOP是重点**，IOC说白了就是将JavaBeen交给Spring管理

~~~xml
   <dependency>  
           <groupId>org.springframework</groupId>  
	            <artifactId>spring-webmvc</artifactId>  
	            <version>5.2.0.RELEASE</version>  
	        </dependency>  
	        <dependency>  
	            <groupId>org.springframework</groupId>  
	            <artifactId>spring-jdbc</artifactId>  
	            <version>5.2.0.RELEASE</version>  
	   </dependency> 
~~~

+ **IOC-SpringXMLC和P的注入依赖**

~~~xml
导入约束 : xmlns:p="http://www.springframework.org/schema/p"  
<!--P(属性: properties)命名空间 , 属性依然要设置set方法-->  
<bean id="user" class="com.kuang.pojo.User" p:name="狂神" p:age="18"/>  
~~~

### 2.spring的第一个程序

~~~java
@Test
public void test(){
    //解析beans.xml文件 , 生成管理相应的Bean对象
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    //getBean : 参数即为spring配置文件中bean的id .
    Hello hello = (Hello) context.getBean("hello");
    hello.show();
}
~~~



### 3.IOC-Spring的自动装配

- **使用xml进行Spring的自动装配**：需要加入`autowire="byName"`属性，其中byName是自动扫描上下文，而使用`autowire="byType"`则去扫描上文相关类。

~~~xml
xmlns:context="http://www.springframework.org/schema/context"  
http://www.springframework.org/schema/context  
http://www.springframework.org/schema/context/spring-context.xsd 
<context:annotation-config/>  

<?xml version="1.0" encoding="UTF-8"?>  
	<beans xmlns="http://www.springframework.org/schema/beans"  
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
	       xmlns:context="http://www.springframework.org/schema/context"  
	       xsi:schemaLocation="http://www.springframework.org/schema/beans  
	        http://www.springframework.org/schema/beans/spring-beans.xsd  
	        http://www.springframework.org/schema/context  
	        http://www.springframework.org/schema/context/spring-context.xsd">  
	</beans>  

<!-- autowire="byName"   -->
<bean id="user" class="com.kuang.pojo.User" autowire="byName">
    <property name="str" value="qinjiang"/>
</bean>

<!-- autowire="byType"   -->
<bean id="dog" class="com.kuang.pojo.Dog"/>
<bean id="cat" class="com.kuang.pojo.Cat"/>
<bean id="cat2" class="com.kuang.pojo.Cat"/>
<bean id="user" class="com.kuang.pojo.User" autowire="byType">
    <property name="str" value="qinjiang"/>
</bean>
~~~

- **使用注解对javaSpring自动装配**：jdk1.5开始支持注解，spring2.5开始全面支持注解，利用注解的方式注入属性，在spring配置文件中引入context文件头。

  1.@Autowired能进行自动匹配：[@Autowired](https://github.com/Autowired)(required=false) 说明： false，对象可以为null；true，对象必须存对象，不能为null。

~~~xml
xmlns:context="http://www.springframework.org/schema/context"
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context.xsd
<!-- 开启注解支持   -->
<context:annotation-config/>
~~~

​		2.[@Qualifier](https://github.com/Qualifier) ，[@Autowired](https://github.com/Autowired)是根据类型自动装配的，加上[@Qualifier](https://github.com/Qualifier)则可以根据byName的方式自动装配[@Qualifier](https://github.com/Qualifier)不能单独使用，以解决配置文件跟类名不一致的问题

~~~xml
<bean id="dog1" class="com.kuang.pojo.Dog"/>
<bean id="dog2" class="com.kuang.pojo.Dog"/>
<bean id="cat1" class="com.kuang.pojo.Cat"/>
<bean id="cat2" class="com.kuang.pojo.Cat"/>
~~~

~~~java
@Autowired
@Qualifier(value = "cat2")
private Cat cat;
@Autowired
@Qualifier(value = "dog2")
private Dog dog;
~~~



###4.IOC-Spring用纯java的方式写Spring的配置文件

~~~java
@Configuration  //代表这是一个配置类
public class MyConfig {
    @Bean //通过方法注册一个bean，这里的返回值就Bean的类型，方法名就是bean的id！
    public Dog dog(){
        return new Dog();
    }
}
~~~

### 5.Aop1-代理模式

+ **代理模式本质**上是社会分工，同一个工业品交给一个人干，效率是极低的，但把他们拆成一个一个方法，使得彼此之间分工，生产商品的效率就提高了。

+ 代理模式分为**静态代理**和**动态代理**

  - **静态代理**：在原有的基础上再包装一个类，在原有基础上增加一个方法就行了

    ~~~java
    package com.wordtree.test;
    
    public class 静态代理 implements 真实角色{
        private UserBao userBao;
    
        public void setUserBao(UserBao userBao) {
            this.userBao = userBao;
        }
    
        @Override
        public void 增加() {
            userBao.增加();
        }
    
        @Override
        public void 删除() {
            userBao.删除();
        }
    
        @Override
        public void 修改() {
            userBao.修改();
        }
    }
    
    ~~~
  
  - **动态代理**：动态代理的本质通过反射实现方法
  
    ~~~java
    package com.wordtree.test;
    
    import java.lang.reflect.InvocationHandler;
    import java.lang.reflect.Method;
    import java.lang.reflect.Proxy;
    
    public class ProxyInvocationHandler implements InvocationHandler {
        private Object target;
    
        public void setRent(Object target) {
            this.target = target;
        }
    
        public Object getProxy(){
            return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = method.invoke(target, args);
            return result;
        }
    
        public static void main(String[] args) {
            动态代理 动态 = new 动态代理();
            ProxyInvocationHandler proxy = new ProxyInvocationHandler();
            proxy.setRent(动态);
            真实角色 proxy1 = (真实角色)proxy.getProxy();
            proxy1.增加();
        }
    }
    ~~~

+ **SpringAop导入依赖包，以及实现代理**

~~~xml
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.4</version>
</dependency>
~~~

~~~java
public class Log implements MethodBeforeAdvice {
    //method : 要执行的目标对象的方法
    //objects : 被调用的方法的参数
    //Object : 目标对象
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println( o.getClass().getName() + "的" + method.getName() + "方法被执行了");
    }
}

public class AfterLog implements AfterReturningAdvice {
    //returnValue 返回值
    //method被调用的方法
    //args 被调用的方法的对象的参数
    //target 被调用的目标对象
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了" + target.getClass().getName()
        +"的"+method.getName()+"方法,"
        +"返回值："+returnValue);
    }
}
~~~

**xml代码方面**

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--注册bean-->
    <bean id="userService" class="com.kuang.service.UserServiceImpl"/>
    <bean id="log" class="com.kuang.log.Log"/>
    <bean id="afterLog" class="com.kuang.log.AfterLog"/>
    <!--aop的配置-->
    <aop:config>
        <!--切入点  expression:表达式匹配要执行的方法-->
        <aop:pointcut id="pointcut" expression="execution(* com.kuang.service.UserServiceImpl.*(..))"/>
        <!--执行环绕; advice-ref执行方法 . pointcut-ref切入点-->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>
</beans>
~~~

测试方法

~~~java
public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.search();
    }
}
~~~

Aop的重要性 : 很重要 . 一定要理解其中的思路 , 主要是思想的理解这一块 .

Spring的Aop就是将公共的业务 (日志 , 安全等) 和领域业务结合起来 , 当执行领域业务时 , 将会把公共业务加进来 . 实现公共业务的重复利用 . 领域业务更纯粹 , 程序猿专注领域业务 , 其本质还是动态代理 .

#### 第二种方式

**自定义类来实现Aop**

目标业务类不变依旧是userServiceImpl

第一步 : 写我们自己的一个切入类

```java
public class DiyPointcut {
    public void before(){
        System.out.println("---------方法执行前---------");
    }
    public void after(){
        System.out.println("---------方法执行后---------");
    }
}
```

去spring中配置

```xml
<!--第二种方式自定义实现-->
<!--注册bean-->
<bean id="diy" class="com.kuang.config.DiyPointcut"/>
<!--aop的配置-->
<aop:config>
    <!--第二种方式：使用AOP的标签实现-->
    <aop:aspect ref="diy">
        <aop:pointcut id="diyPonitcut" expression="execution(* com.kuang.service.UserServiceImpl.*(..))"/>
        <aop:before pointcut-ref="diyPonitcut" method="before"/>
        <aop:after pointcut-ref="diyPonitcut" method="after"/>
    </aop:aspect>
</aop:config>
```

测试

~~~java
public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}
~~~



#### 第三种方式

**使用注解实现**

第一步：编写一个注解实现的增强类

```java
package com.kuang.config;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
@Aspect
public class AnnotationPointcut {
    @Before("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("---------方法执行前---------");
    }
    @After("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("---------方法执行后---------");
    }
    @Around("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");
        System.out.println("签名:"+jp.getSignature());
        //执行目标方法proceed
        Object proceed = jp.proceed();
        System.out.println("环绕后");
        System.out.println(proceed);
    }
}
```

第二步：在Spring配置文件中，注册bean，并增加支持注解的配置

```xml
<!--第三种方式:注解实现-->
<bean id="annotationPointcut" class="com.kuang.config.AnnotationPointcut"/>
<aop:aspectj-autoproxy/>
```

aop:aspectj-autoproxy：说明

```xml
通过aop命名空间的<aop:aspectj-autoproxy />声明自动为spring容器中那些配置@aspectJ切面的bean创建代理，织入切面。当然，spring 在内部依旧采用AnnotationAwareAspectJAutoProxyCreator进行自动代理的创建工作，但具体实现的细节已经被<aop:aspectj-autoproxy />隐藏起来了 
<aop:aspectj-autoproxy />有一个proxy-target-class属性，默认为false，表示使用jdk动态代理织入增强，当配为<aop:aspectj-autoproxy  poxy-target-class="true"/>时，表示使用CGLib动态代理技术织入增强。不过即使proxy-target-class设置为false，如果目标类没有声明接口，则spring将自动使用CGLib动态代理。。
```

# Mybatis

## 一.基本信息

## 二.在idea中进行环境配置

1. maven导入依赖(java1.8版本)

   ~~~xml
   <dependencies>
           <!-- 导入依赖 -->
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>5.1.46</version>
           </dependency>
           <!--导入mybatis依赖-->
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.6</version>
           </dependency>
           <!--导入测试类-->
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.12</version>
           </dependency>
       </dependencies>
   ~~~

   

## 三：第一个Myba程序

> <img src="https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211112085641029.png" alt="image-20211112085641029" style="zoom:50%;" />

~~~xml
<!--处理用户业务逻辑-->
    <mappers>
        <mapper resource="com/yangteng/study/dao/UserMapper.xml"/>
    </mappers>
~~~

在配置文件里面添加上面的xml说明,下面是Mapper文件

**UserDao实体接口:**

~~~java
package com.yangteng.study.dao;

import com.yangteng.study.pjo.User;

import java.util.List;

public interface UserDao {
    //查询所有用户数据
    List<User> getAllUsers();
    //根据id查询用户数据
    User getUser(int id);
}
~~~

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yangteng.study.dao.UserDao">
    <select id="getAllUsers" resultType="com.yangteng.study.pjo.User">
        select * from user_table
    </select>
    <select id="getUser" resultType="com.yangteng.study.pjo.User" parameterType="int">
        select * from user_table where id = #{id}
    </select>
</mapper>
~~~

**解决Maven过载问题:**

~~~xml
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
~~~

## 四.增删改查

**注意**:增删改操作要写提交语句

javaDao：

~~~java
package com.yangteng.study.dao;

import com.yangteng.study.pjo.User;

import java.util.List;

public interface UserMapper {
    //查询所有用户数据
    List<User> getAllUsers();
    //根据id查询用户数据
    User getUser(int id);
    /**
     * 添加一个用户
     */
    int addUser(User user);
    /**
     * 删除一个用户
     */
    int delUser(int id);
    /**
     * 修改一个用户
     */
    int upDateUser(User user);
}

~~~

Mapper.xml层：

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yangteng.study.dao.UserMapper">
    <!--实际开发中往往遇到数据库名字与java中类的名字不一致的问题，除了可以在sql语句上修改之外，MyBatis还提供了一种特别的方案-->
    <resultMap id="aoteman" type="user">
        <result column="id" property="id"/>
        <result column="name" property="name"/>
        <result column="pwd" property="password"/>
    </resultMap>
    <!--查询所有数据-->
    <select id="getAllUsers" resultType="user" resultMap="aoteman">
        select * from user_table
    </select>
    <!--查询一条数据-->
    <select id="getUser" resultType="user" parameterType="int">
        select * from user_table where id = #{id}
    </select>
    <!--添加一个用户-->
    <insert id="addUser" parameterType="user">
        insert into user_table(id,name,password) value (#{id},#{name},#{password})
    </insert>
    <!--修改一个用户-->
    <update id="upDateUser" parameterType="user">
        update user_table
        set name = #{name},password = #{password}
        where id = #{id};
    </update>
    <!--删除一个用户-->
    <delete id="delUser" parameterType="int">
        delete
        from user_table
        where id = #{id};
    </delete>
</mapper>
~~~

Myba默认配置文件：

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--使用外部资源绑定数据库-->
    <properties resource="Jdbc.properties">
        <!--这个的意思是在配置文件中添加下面的属性,当然一切以配置文件优先-->
        <property name="username" value="root"/>
    </properties>
    <settings>
        <setting name="logImpl" value="LOG4J"/>
    </settings>
    <!--添加类的别名-->
    <typeAliases>
    <!--这个是添加一个包下所有的别名，默认为改单词首字母小写-->
    <!--<package name="com.yangteng.study.pjo"/>-->
        <!--指定添加位置-->
        <typeAlias type="com.yangteng.study.pjo.User" alias="user"/>
    </typeAliases>
    <!--环境配置，连接的数据库，这里使用的是MySQL-->
    <environments default="mysql">
        <environment id="mysql">
            <!--指定事务管理的类型，这里简单使用Java的JDBC的提交和回滚设置-->
            <transactionManager type="JDBC"/>
            <!--dataSource 指连接源配置，POOLED是JDBC连接对象的数据源连接池的实现-->
            <dataSource type="POOLED">
                <property name="driver" value="${driverClassName}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <!--处理用户业务逻辑-->
    <mappers>
<!--        <mapper resource="com/yangteng/study/dao/UserMapper.xml"/>-->
        <package name="com.yangteng.study.dao"/>
    </mappers>

</configuration>
~~~

添加类的别名（有添加类和包两种方式）

~~~xml
    <!--添加类的别名-->
    <typeAliases>
    <!--这个是添加一个包下所有的别名，默认为改单词首字母小写-->
    <!--<package name="com.yangteng.study.pjo"/>-->
        <!--指定添加位置-->
        <typeAlias type="com.yangteng.study.pjo.User" alias="user"/>
    </typeAliases>
~~~

添加对于包的映射（注意这个要使dao接口与mapper.xml同名）：

~~~xml
    <!--处理用户业务逻辑-->
    <mappers>
<!--        <mapper resource="com/yangteng/study/dao/UserMapper.xml"/>-->
        <package name="com.yangteng.study.dao"/>
    </mappers>
~~~



## 五. log4j

使用log4j:(Myba配置文件)

~~~xml
    <settings>
        <setting name="logImpl" value="LOG4J"/>
    </settings>
~~~

log4j的资源文件:

~~~properties
# priority  :debug<info<warn<error
#you cannot specify every priority with different file for log4j
log4j.rootLogger=debug,stdout,info,debug,warn,error 

#console
log4j.appender.stdout=org.apache.log4j.ConsoleAppender 
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout 
log4j.appender.stdout.layout.ConversionPattern= [%d{yyyy-MM-dd HH:mm:ss a}]:%p %l%m%n
#info log
log4j.logger.info=info
log4j.appender.info=org.apache.log4j.DailyRollingFileAppender 
log4j.appender.info.DatePattern='_'yyyy-MM-dd'.log'
log4j.appender.info.File=./src/com/hp/log/info.log
log4j.appender.info.Append=true
log4j.appender.info.Threshold=INFO
log4j.appender.info.layout=org.apache.log4j.PatternLayout 
log4j.appender.info.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss a} [Thread: %t][ Class:%c >> Method: %l ]%n%p:%m%n
#debug log
log4j.logger.debug=debug
log4j.appender.debug=org.apache.log4j.DailyRollingFileAppender 
log4j.appender.debug.DatePattern='_'yyyy-MM-dd'.log'
log4j.appender.debug.File=./src/com/hp/log/debug.log
log4j.appender.debug.Append=true
log4j.appender.debug.Threshold=DEBUG
log4j.appender.debug.layout=org.apache.log4j.PatternLayout 
log4j.appender.debug.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss a} [Thread: %t][ Class:%c >> Method: %l ]%n%p:%m%n
#warn log
log4j.logger.warn=warn
log4j.appender.warn=org.apache.log4j.DailyRollingFileAppender 
log4j.appender.warn.DatePattern='_'yyyy-MM-dd'.log'
log4j.appender.warn.File=./src/com/hp/log/warn.log
log4j.appender.warn.Append=true
log4j.appender.warn.Threshold=WARN
log4j.appender.warn.layout=org.apache.log4j.PatternLayout 
log4j.appender.warn.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss a} [Thread: %t][ Class:%c >> Method: %l ]%n%p:%m%n
#error
log4j.logger.error=error
log4j.appender.error = org.apache.log4j.DailyRollingFileAppender
log4j.appender.error.DatePattern='_'yyyy-MM-dd'.log'
log4j.appender.error.File = ./src/com/hp/log/error.log 
log4j.appender.error.Append = true
log4j.appender.error.Threshold = ERROR 
log4j.appender.error.layout = org.apache.log4j.PatternLayout
log4j.appender.error.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss a} [Thread: %t][ Class:%c >> Method: %l ]%n%p:%m%n
~~~

接下来获取类输出就行了。





# SpringMVC

## 1.准备开发环境（这里直接使用注解开发）

1. 导入springmvc依赖（使用maven进行导入）

~~~xml
        <!--这是springmvc的依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.0.RELEASE</version>
        </dependency>
        <!--不能直接使用springmvc因为跟Tomcat里面的一个东西有冲突，需要导入下面这个包-->
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-api</artifactId>
            <version>7.0</version>
            <scope>provided</scope>
        </dependency>
~~~

2. 撰写web.xml的配置

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--1.注册servlet-->
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--通过初始化参数指定SpringMVC配置文件的位置，进行关联-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <!-- 启动顺序，数字越小，启动越早 -->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <!--所有请求都会被springmvc拦截 -->
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
~~~

3. 按照约定我们来写springmvc-servlet.xml的配置在资源目录下

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        https://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <!-- 自动扫描包，让指定包下的注解生效,由IOC容器统一管理 -->
    <context:component-scan base-package="com.yangteng.controller"/>
    <!-- 让Spring MVC不处理静态资源 -->
    <mvc:default-servlet-handler />
    <!--
    支持mvc注解驱动
        在spring中一般采用@RequestMapping注解来完成映射关系
        要想使@RequestMapping注解生效
        必须向上下文中注册DefaultAnnotationHandlerMapping
        和一个AnnotationMethodHandlerAdapter实例
        这两个实例分别在类级别和方法级别处理。
        而annotation-driven配置帮助我们自动完成上述两个实例的注入。
     -->
    <mvc:annotation-driven />
    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/win/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>
</beans>
~~~

## 2.第一个程序

1. 准备好开发环境,我们就可以写第一个程序了

2. web层:使用了两个注解Controller这个代表注册了控制器,RequestMapping这个指定路径,`return "hello"`表示要前往的文件名。

~~~java
package com.yangteng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
    @RequestMapping("/hello")
    public String helloWorld(Model model){
        model.addAttribute("msg","Hello World!");
        return "hello";
    }
}
~~~

## 3.常用注解

### 1.通过不同的注解限制请求

Spring MVC 的 [@RequestMapping](https://github.com/RequestMapping) 注解能够处理 HTTP 请求的方法, 比如 GET, PUT, POST, DELETE 以及 PATCH。

+ 我们可以通过不同的注解，限制服务器的请求

~~~java
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@PatchMapping
~~~

### 2.请求转发与重定向

#### + 第一种是视图解释器

~~~xml
<!-- 视图解析器 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
      id="internalResourceViewResolver">
    <!-- 前缀 -->
    <property name="prefix" value="/WEB-INF/jsp/" />
    <!-- 后缀 -->
    <property name="suffix" value=".jsp" />
</bean>
~~~

#### + 第二种是直接在控制器里面写返回值

`forward`这个参数为视图转发，`redirect`这个为请求重定向

~~~java
@Controller
public class ResultSpringMVC {
    @RequestMapping("/rsm/t1")
    public String test1(){
        //转发
        return "/index.jsp";
    }
    @RequestMapping("/rsm/t2")
    public String test2(){
        //转发二
        return "forward:/index.jsp";
    }
    @RequestMapping("/rsm/t3")
    public String test3(){
        //重定向
        return "redirect:/index.jsp";
    }
}
~~~

# 整合SSM框架



# SpringBoot

## 1.第一个程序

## 2.yaml文件的语法，与向javaBean中赋值

## 3.303校验

~~~java
@Validated //这个表示开启数据校验
~~~

在对应的属性位置加注释就行了其中，还可以这样写：message会告诉你报错的信息

~~~java
@Email(message = "邮箱不正确") //开启邮箱验证
~~~

下面是一些基本操作，注意最后一个，为正则表达式

![img](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/3145530-8ae74d19e6c65b4c)

## 4.多环境配置

1. 在多环境配置配置中采用yaml进行,在核心yaml中可以切换不同的yaml文件
2. 代码:切换文件代码

~~~yaml
#设置端口号为8082
server:
  port: 8082

#这个可以切换默认的配置文件
spring:
  profiles:
    active: dev #直接写文件名就行了,比如这样application-x.yaml,写x就行了
#你还可以这样写https://www.webjars.org/
#spring.profiles.active=y
#设置一个叫做person的对象
person:
  name:
  age:
#使用分割线可以重新划分不同的配置
---
~~~

3. 注意给yaml对象赋值的时候,千万:后面要加空格,yaml对空格有严格的要求

## 5.配置开发环境

1. 引入jar包需要到webJars上面去找

> [WebJars - Web Libraries in Jars](https://www.webjars.org/)

2. 在springBoot,我们可以使用以下方式处理静态资源,这些目录下的文件可以直接访问

+ `webjars`
+ `public,static,/**/resource`

如果在同一级目录下的同名文件

resource>static>public
