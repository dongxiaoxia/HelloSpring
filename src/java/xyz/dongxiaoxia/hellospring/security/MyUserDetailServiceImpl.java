package xyz.dongxiaoxia.hellospring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.repository.ResourceDao;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/11/17.
 * User userdetail该类实现 UserDetails 接口，该类在验证成功后会被保存在当前回话的principal对象中
 * <p/>
 * 获得对象的方式：
 * WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * <p/>
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 * <p/>
 * 如果需要包括用户的其他属性，可以实现 UserDetails 接口中增加相应属性即可
 * 权限验证类
 */
@Component
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ResourceDao resourceDao;

    // 登录验证
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.err.println("-----------MyUserDetailServiceImpl loadUserByUsername ----------- ");

        //取得用户的权限
        xyz.dongxiaoxia.hellospring.core.entity.User users = userDao.findByUsername(username);
        if (users == null)
            throw new UsernameNotFoundException(username + " not exist!");
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users);
        // 封装成spring security的user
        User userdetail = new User(
                users.getName(),
                users.getPassword(),
                true,
                true,
                true,
                true,
                grantedAuths    //用户的权限
        );
        return userdetail;
    }

    // 取得用户的权限
    private Set<GrantedAuthority> obtionGrantedAuthorities(xyz.dongxiaoxia.hellospring.core.entity.User user) {
        List<Resource> resources = resourceDao.getUserResources(String.valueOf(user.getId()));
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        for (Resource res : resources) {
            // TODO:ZZQ 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
            // 关联代码：applicationContext-security.xml
            // 关联代码：com.huaxin.security.MySecurityMetadataSource#loadResourceDefine
            authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getResKey()));
        }
        return authSet;
    }

}
