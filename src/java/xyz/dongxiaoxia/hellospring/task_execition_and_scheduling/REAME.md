Spring实现定时任务
从spring 3开始，它本身就已经自带了一套自主开发的定时任务工具Spring-Task，可以将它看成是一个轻量级的Quartz，而且使用起来十分简单，除spring相关的包外不需要额外的包，支持注解和配置文件两种形式。
第一种：配置文件方式
第一步：编写作业类，它是一个普通的Java类，不需要继承和实现任何类和接口：
public class TaskJobWithXml {
    public void job(){
        System.out.println("以配置文件方式实现定时任务。。。");
    }
}
第二步：在spring配置文件头中添加spring-task的命名空间及描述：
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:task="http://www.springframework.org/schema/task"
    ...
    xsi:schemaLocation="http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task-3.0.xsd">
第三步：spring配置文件中设置具体的任务
<bean id="job" class="xyz.dongxiaoxia.hellospring.task_execition_and_scheduling.TaskJobWithXml">
    </bean>
<!--The 'executor' element-->
    <task:executor id="myExecutor" pool-size="5-25" queue-capacity="100" rejection-policy="CALLER_RUNS"
                   keep-alive="120"/>
    <!--The 'scheduler' element-->
    <task:scheduler id="myScheduler" pool-size="10"/>
    <!--The 'scheduled-tasks' element-->
    <task:scheduled-tasks scheduler="myScheduler">
        <task:scheduled ref="taskExecutorExample" method="printMessages" cron="0 * * * * ?" fixed-delay="5000"/>
        <task:scheduled ref="job" method="job" fixed-delay="1000"/>
    </task:scheduled-tasks>

第二种：使用注解形式
第一步：还是编写我们的任务类，和上面基本一样，只不过方法上添加了@Scheduled注解。
@Component
public class TaskJobWithAnnotation {
    @Scheduled(cron = "0 * * * * ?")
    public void job(){
        System.out.println("以注解方式实现定时任务。。。");
    }
}
第二步：同样需要在spring配置文件头中添加spring-task的命名空间及描述，另外添加扫描spring-task的配置：
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:task="http://www.springframework.org/schema/task"
    ...
    xsi:schemaLocation="http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task-3.0.xsd">
	...
	<!-- 开启这个配置，spring才能识别@Scheduled注解 -->
	<task:annotation-driven/>
	...
</beans>
配置完毕，我们的任务已经可以运行了。当然你也可以把cron参数换成另外的两个，自己尝试一下吧。spring-task还有很多的参数，这里就不一一解释了，具体可以查看官方的文档。





在Spring中使用Quartz有两种方式实现：第一种是任务类继承QuartzJobBean，第二种则是在配置文件里定义任务类和要执行的方法，类和方法可以是普通类。很显然，第二种方式远比第一种方式来的灵活。
一、Quartz的特点

* 按作业类的继承方式来分，主要有以下两种：

1.作业类继承org.springframework.scheduling.quartz.QuartzJobBean类的方式

2.作业类不继承org.springframework.scheduling.quartz.QuartzJobBean类的方式

注：个人比较推崇第二种，因为这种方式下的作业类仍然是POJO。

* 按任务调度的触发时机来分，主要有以下两种：

1.每隔指定时间则触发一次，对应的调度器为org.springframework.scheduling.quartz.SimpleTriggerBean

2.每到指定时间则触发一次，对应的调度器为org.springframework.scheduling.quartz.CronTriggerBean

注：这两种触发方式均可以跟两种作业继承方式相互组合来使用。

Spring+Quartz实现定时任务的配置方法
1.Scheduler的配置
＜!-- 总管理类 如果将lazy-init='false'那么容器启动就会执行调度程序  --＞
    <bean id="myScheduler"  lazy-init="false"
        class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <property name="triggers">
            <list>
                <ref bean="myTriggersA"></ref>
                <ref bean="myTriggersB"></ref>
            </list>
        </property>
        <property name="autoStartup" value="true"></property>
    </bean>
2.Trigger的配置
 ＜!-- 定义触发时间 --＞
    <bean id="myTriggersA"
        class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <property name="jobDetail" ref="myJobDetailA">
        </property>
           ＜!-- cron表达式 --＞
        <property name="cronExpression">
            <value>0/1 * * * * ?</value>
        </property>
    </bean>
3.JobDetail的配置
＜!-- 定义调用对象和调用对象的方法 --＞
    <bean id="myJobDetailA"
        class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
          ＜!-- 调用的类 --＞
        <property name="targetObject" ref="myJobA">
        </property>
        ＜!-- 调用类中的方法 --＞
        <property name="targetMethod" value="work"></property>
        <property name="concurrent" value="false" />
        <!-- 是否允许任务并发执行。当值为false时，表示必须等到前一个线程处理完毕后才再启一个新的线程 -->
    </bean>
4.业务类的配置
      ＜!-- 要调用的工作类 --＞
    <bean id="myJobA" class="com.quartz.MyJobA"></bean>　
5.业务类代码
import java.util.Date;

public class MyJobA {

    public void work() {
        System.out.println("date: " + new Date().getTime());
    }
}
说明：业务类不需要继承任何父类，也不需要实现任何接口，只是一个普通的java类。

说明：
             1）Cron表达式的格式：秒 分 时 日 月 周 年(可选)。

                   字段名                 允许的值                        允许的特殊字符

                   秒                         0-59                               , - * /

                   分                         0-59                               , - * /

                   小时                   0-23                               , - * /

                   日                         1-31                               , - * ? / L W C

                   月                         1-12 or JAN-DEC          , - * /

                   周几                     1-7 or SUN-SAT            , - * ? / L C #

                   年 (可选字段)     empty, 1970-2099      , - * /

                   “?”字符：表示不确定的值

                   “,”字符：指定数个值

                   “-”字符：指定一个值的范围

                   “/”字符：指定一个值的增加幅度。n/m表示从n开始，每次增加m

                   “L”字符：用在日表示一个月中的最后一天，用在周表示该月最后一个星期X

                   “W”字符：指定离给定日期最近的工作日(周一到周五)

                   “#”字符：表示该月第几个周X。6#3表示该月第3个周五

             2）Cron表达式范例：

                     每隔5秒执行一次：*/5 * * * * ?

                     每隔1分钟执行一次：0 */1 * * * ?

                     每天23点执行一次：0 0 23 * * ?

                     每天凌晨1点执行一次：0 0 1 * * ?

                     每月1号凌晨1点执行一次：0 0 1 1 * ?

                     每月最后一天23点执行一次：0 0 23 L * ?

                     每周星期天凌晨1点实行一次：0 0 1 ? * L

                     在26分、29分、33分执行一次：0 26,29,33 * * * ?

                     每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?

                     "0 0 12 * * ?" 每天中午12点触发

                     "0 15 10 ? * *" 每天上午10:15触发

                     "0 15 10 * * ?" 每天上午10:15触发

                     "0 15 10 * * ? *" 每天上午10:15触发

                     "0 15 10 * * ? 2005" 2005年的每天上午10:15触发

                     "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发

                     "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发

                     "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发

                     "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发

                     "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发

                     "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发

                     "0 15 10 15 * ?" 每月15日上午10:15触发

                     "0 15 10 L * ?" 每月最后一日的上午10:15触发

                     "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发

                     "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发

                     "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发

                     每天早上6点 0 6 * * *

                     每两个小时  0 */2 * * *

                     晚上11点到早上8点之间每两个小时，早上八点 0 23-7/2，8 * * *

                     每个月的4号和每个礼拜的礼拜一到礼拜三的早上11点 0 11 4 * 1-3

                     1月1日早上4点 0 4 1 1 *