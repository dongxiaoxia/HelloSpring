package xyz.dongxiaoxia.hellospring.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.dongxiaoxia.hellospring.service.LogService;

import java.util.Arrays;

/**
 * Created by Administrator on 2015/11/6.
 * 日志记录，添加、删除、修改方法AOP
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;//日志记录服务

    /**
     * 添加业务逻辑方法切入点
     */
    @Pointcut("execution(* notInvokexyz.dongxiaoxia.hellospring..*.*(..)))")
    public void pointcutExpression(){
    }

    /**
     * aspectjrt.jar还有aspectjweaver.jar包的版本与jdk要匹配。
     * jdk是1.7的jar包也要是1.7
     * jdk是1.6的jar包也要是1.6
     * 这个问题浪费了我一晚上的时间，::>_<::
     * @param joinPoint
     */
    @Before("pointcutExpression()")
    public void beforeMethod(JoinPoint joinPoint){
        System.out.println("前置通知执行了");
    }

    @After("pointcutExpression()")
    public void afterMethod(JoinPoint joinPoint){
        System.out.println("后置通知执行了，有异常也会执行");
    }

    @AfterReturning(value = "pointcutExpression()",returning = "returnValue")
    public void afterRunningMethod(JoinPoint joinPoint ,Object returnValue){
        System.out.println("返回通知执行，执行结果："+returnValue);
    }

    @AfterThrowing(value = "pointcutExpression()",throwing = "e")
    public void afterThrowingMethod(JoinPoint joinPoint,Exception e){
        System.out.println("通知异常，出现异常："+e);
    }

    @Around("pointcutExpression()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint){
        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        try {
            //前置通知
            System.out.println("The method "+ methodName+" begins with "+ Arrays.asList(proceedingJoinPoint.getArgs()));
            //执行目标方法
            result = proceedingJoinPoint.proceed();
            //返回通知
            System.out.println("The method "+methodName+" ends with "+result);
        } catch (Throwable throwable) {
            //异常通知
            System.out.println("The method "+methodName+" occurs exception:"+throwable);
            throw new RuntimeException(throwable);
        }
        //后置通知
        System.out.println("The method "+methodName+" ends");
        return result;
    }
}
