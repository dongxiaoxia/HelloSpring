package xyz.dongxiaoxia.hellospring.task_execition_and_scheduling;

/**
 * Created by Administrator on 2015/12/1.
 */
public class MyJobNotQuartzJobBean {

    public void work() {
        System.out.println("Spring 结合 Quartz 不继承QuartzJobBean 实现定时任务。。。");
    }
}
