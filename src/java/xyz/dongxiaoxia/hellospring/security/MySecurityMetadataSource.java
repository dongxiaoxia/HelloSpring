package xyz.dongxiaoxia.hellospring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.repository.ResourceDao;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Administrator on 2015/11/17.
 * 加载资源与权限的对应关系
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static final LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(MySecurityMetadataSource.class);

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    @Autowired
    private ResourceDao resourceDao;

    //返回所请求资源所需要的权限
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        logger.info("-----------MySecurityMetadataSource getAttributes ----------- ");
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //	System.out.println("requestUrl is " + requestUrl);
        if (resourceMap == null) {
            loadResourceDefine();
        }
        //System.err.println("resourceMap.get(requestUrl); "+resourceMap.get(requestUrl));
        if (requestUrl.indexOf("?") > -1) {
            requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
        }
        Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
        //		if(configAttributes == null){
//			Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>();
//			 returnCollection.add(new SecurityConfig("ROLE_NO_USER"));
//			return returnCollection;
//		}
        return configAttributes;
    }

    /**
     * @PostConstruct是Java EE 5引入的注解，
     * Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
     * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作， 加载所有资源与权限的关系
     */
    @PostConstruct
    private void loadResourceDefine() {
        logger.info("-----------MySecurityMetadataSource loadResourceDefine ----------- ");
        if (resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<Resource> resources = this.resourceDao.$query(new Resource());
            for (Resource resource : resources) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                // TODO:ZZQ 通过资源名称来表示具体的权限 注意：必须"ROLE_"开头
                // 关联代码：security.xml
                // 关联代码：MyUserDetailServiceImpl#obtionGrantedAuthorities
                ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + resource.getResKey());
                configAttributes.add(configAttribute);
                resourceMap.put(resource.getResUrl(), configAttributes);
            }
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
}
