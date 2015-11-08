package xyz.dongxiaoxia.hellospring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.log.service.LogService;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;
import xyz.dongxiaoxia.hellospring.logging.LoggerJavaAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2015/11/7.
 */
@Aspect
@Component
public class SystemLogAspect {

    private static final LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(LoggerJavaAdapter.class);
    @Autowired
    private LogService logService;

    //Controlle层切点
    @Pointcut("execution (* xyz.dongxiaoxia.hellospring.account.controller.*.*(..))&&@annotation(MyLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知，用于拦截Controller层记录用户的操作
     *
     * @param joinPoint
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("===============执行controller前置通知================");
        logger.info("===============before " + joinPoint + "================");
    }

    /**
     * 配置controller环绕通知，使用在方法aspect()上注册的切入点
     *
     * @param joinPoint
     */
    @Around("controllerAspect()")
    public Object around(JoinPoint joinPoint) {
        Object returnValue = null;
        logger.info("===============执行controller前置通知================");
        long start = System.currentTimeMillis();
        try {
            returnValue = ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            logger.info("===============around " + joinPoint + "\tUse time:" + (end - start) + " ms!================");
            logger.info("===============结束执行controller环绕通知================");
        } catch (Throwable throwable) {
            long end = System.currentTimeMillis();
            logger.error("===============around " + joinPoint + "\tUse time:" + (end - start) + " ms with exception:" + throwable.getMessage() + "================", throwable);
            throw new RuntimeException(throwable);
        }
        return returnValue;
    }

    /**
     * 后置通知，用于拦截Controller层记录用户的操作
     *
     * @param joinPoint
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        /*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String ip = request.getRemoteAddr();*/
        User user = new User();
        user.setId("123");
        user.setName("东小侠");
        String ip = "127.0.0.1";

        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        operationType = method.getAnnotation(MyLog.class).operationType();
                        operationName = method.getAnnotation(MyLog.class).operationName();
                        break;
                    }

                }
            }
            //*========控制台输出=========*//
            logger.info("===============controller后置通知开始================");
            logger.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "." + operationType);
            logger.info("方法描述:" + operationName);
            logger.info("请求人:" + user.getName());
            logger.info("请求IP:" + ip);
            //*========数据库日志=========*//
            Log log = new Log();
            log.setId(UUID.randomUUID().toString());
            log.setDescription(operationName);
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "." + operationType);
            log.setLogType((long) 0);
            log.setRequestIp(ip);
            log.setExceptionCode(null);
            log.setExceptionDetail(null);
            log.setParams(null);
            log.setCreateBy(user.getName());
            log.setCreateDate(new Date());
            //保存数据库
            logService.save(log);
            logger.info("===============controller后置通知结束================");

        } catch (Exception e) {
            logger.error("===============后置通知异常================");
            logger.error("===============异常信息：" + e.getMessage() + "================", e);


        }
    }

    /**
     * 配置后置返回通知，使用在方法aspect()上注册的切入点
     *
     * @param joinPoint
     */
    @AfterReturning("controllerAspect()")
    public void afterReturn(JoinPoint joinPoint) {
        logger.info("===============执行controller后置返回通知================");
    }

    /**
     * 异常通知 用于拦截记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        /*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String ip = request.getRemoteAddr();*/
        //获取用户请求方法的参数并序列化为JSON格式字符串

        User user = new User();
        user.setId("123");
        user.setName("东小侠");
        String ip = "127.0.0.1";

        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                //  params += JsonUtil.getJsonStr(joinPoint.getArgs()[i]) + ";";
            }
        }
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";

            for (Method method : methods) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    operationType = method.getAnnotation(MyLog.class).operationType();
                    operationName = method.getAnnotation(MyLog.class).operationName();
                    break;
                }
            }
            //*========控制台输出=========*//
            logger.info("===============异常通知开始================");
            logger.info("异常代码:" + e.getClass().getName());
            logger.info("异常信息:" + e.getMessage());
            logger.info("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "." + operationType);
            logger.info("方法描述:" + operationName);
            logger.info("请求人:" + user.getName());
            logger.info("请求IP:" + ip);
            logger.info("请求参数:" + params);
            //*========数据库日志=========*//
            Log log = new Log();
            log.setId(UUID.randomUUID().toString());
            log.setDescription(operationName);
            log.setExceptionCode(e.getClass().getName());
            log.setLogType((long) 1);
            log.setExceptionDetail(e.getMessage());
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setParams(params);
            log.setCreateBy(user.getName());
            log.setCreateDate(new Date());
            log.setRequestIp(ip);
            //保存数据库
            logService.save(log);
            logger.info("===============异常通知结束================");
        } catch (ClassNotFoundException ex) {
            //记录本地异常记录
            logger.error("===============异常通知异常================");
            logger.error("异常信息：" + ex.getMessage(), ex);
        }
        /*==========记录本地异常日志==========*/
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);
    }


}
