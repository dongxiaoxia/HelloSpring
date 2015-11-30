package xyz.dongxiaoxia.hellospring.task_execition_and_scheduling;

import org.springframework.core.task.TaskExecutor;

/**
 * Created by Administrator on 2015/11/30.
 */
public class TaskExecutorExample {
    private TaskExecutor taskExecutor;

    public TaskExecutorExample(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void printMessages() {
        for (int i = 0; i < 25; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
    }

    private class MessagePrinterTask implements Runnable {
        private String message;

        public MessagePrinterTask(String message) {
            this.message = message;
        }

        public void run() {
            System.out.println(message);
        }
    }
}
/*
Spring Task可以很方便的实现定时任务功能，使用起来比Spring+QuartZ方便很多
Spring Task最主要的就是xml配置，当然也可以通过注解实现：
        <bean id="taskTest" class="TaskTest" />
        <task:scheduled-tasks>
            task:scheduled ref="taskTest" method="test" cron="0 * * * * ?" />
        </task:scheduled-tasks>
需要添加相应的schema
        xmlns:task="
        xsi:schemaLocation="
        http://www.springframework.org/schema/task
        http://www.springframework.org/schema/task/spring-task-3.0.xsd"
对应的Java bean：
        public class TaskTest {
            public void test() {
                System.out.println("Test");
            }
        }
cron express配置和QuartZ一样：
        至少6个（也可能7个）有空格分隔的时间元素
        *    *     *     *    *     *   *   从左到右依次是：秒、分、小时、日、月、周、年

*/
