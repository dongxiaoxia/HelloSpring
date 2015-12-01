package xyz.dongxiaoxia.hellospring.task_execition_and_scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2015/12/1.
 */
@Component("taskJob")
public class TaskJobWithAnnotation {
    @Scheduled(cron = "0 * * * * ?", initialDelay = 1000, fixedDelay = 5000)
    public void job() {
        System.out.println("以注解方式实现定时任务。。。");
    }
}
