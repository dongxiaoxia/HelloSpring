package xyz.dongxiaoxia.hellospring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.repository.ResourceDao;
import xyz.dongxiaoxia.hellospring.core.repository.RoleDao;
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

    @Autowired
    private RoleDao roleDao;

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
            resourceMap = new HashMap<>();
            //查询resource资源表获取url
            List<Resource> resources = this.resourceDao.$query(new Resource());
            //通过资源角色关联表获取角色
            for (Resource resource : resources) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<>();
                List<Role> roles = roleDao.listRoleByResourceId(resource.getId());
                if (roles != null && roles.size() > 0) {
                    for (Role role : roles) {
                        // TODO:ZZQ 通过资源名称来表示具体的权限 注意：必须"ROLE_"开头
                        // 关联代码：security.xml
                        // 关联代码：MyUserDetailServiceImpl#obtionGrantedAuthorities
                        //获取角色名称装配为权限
                        ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + role.getName());
                        configAttributes.add(configAttribute);
                    }
                }
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
