package xyz.dongxiaoxia.hellospring.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Administrator on 2015/11/17.
 * <p/>
 * 决策类 当请求访问时，判断用户是否具有访问所需的所有权限
 * 自己实现的过滤用户请求类，也可以直接使用 FilterSecurityInterceptor
 * <p/>
 * AbstractSecurityInterceptor有三个派生类：
 * FilterSecurityInterceptor，负责处理FilterInvocation，实现对URL资源的拦截。
 * MethodSecurityInterceptor，负责处理MethodInvocation，实现对方法调用的拦截。
 * AspectJSecurityInterceptor，负责处理JoinPoint，主要是用于对切面方法(AOP)调用的拦截。
 * <p/>
 * 还可以直接使用注解对Action方法进行拦截，例如在方法上加：
 *
 * @PreAuthorize("hasRole('ROLE_SUPER')") <!-- 用户是否拥有所请求资源的权限 -->
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(MyAccessDecisionManager.class);


    /**
     * authentication用户认证后 存有用户的所有权限
     * configAttributes访问所需要的权限
     * 若无权则抛出异常
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        System.err.println(" ---------------  MyAccessDecisionManager --------------- ");
        if (configAttributes == null) {
            return;
        }
        //所请求的资源拥有的权限(一个资源对多个权限)
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            //访问所请求资源所需要的权限
            String needPermission = configAttribute.getAttribute();
            //System.out.println("needPermission is " + needPermission);
            //用户所拥有的权限authentication
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needPermission.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        //没有权限
        throw new AccessDeniedException(" 没有权限访问！ ");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
