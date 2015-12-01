package xyz.dongxiaoxia.hellospring.task_execition_and_scheduling;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by Administrator on 2015/12/1.
 */
public class MyJobExtendQuartzJobBean extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Spring 结合 Quartz 继承QuartzJobBean 实现定时任务。。。");
    }
}
