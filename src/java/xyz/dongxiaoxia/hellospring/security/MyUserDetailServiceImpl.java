package xyz.dongxiaoxia.hellospring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.service.RoleService;
import xyz.dongxiaoxia.hellospring.service.UserService;

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
    private UserService userService;
    @Autowired
    private RoleService roleService;

    // 登录验证
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.err.println("-----------MyUserDetailServiceImpl loadUserByUsername ----------- ");

        //取得用户的权限
        xyz.dongxiaoxia.hellospring.core.entity.User users = userService.querySingleUser(username);
        if (users == null)
            throw new UsernameNotFoundException(username + " not exist!");
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users);
        // 封装成spring security的user
        User userdetail = new User(
                users.getUsername(),
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
        Set<GrantedAuthority> authSet = new HashSet<>();
        List<Role> roles = roleService.listRoleByUserId(user.getId());
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
                // TODO:ZZQ 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
                // 关联代码：applicationContext-security.xml
                // 关联代码：com.huaxin.security.MySecurityMetadataSource#loadResourceDefine
                authSet.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            }
        }
        return authSet;
    }









/*=================================================之前版本====================================================*/
/*
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        xyz.dongxiaoxia.hellospring.core.entity.User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户" + username + "不存在！！！");
        }
        List<Role> roleList = roleDao.findRoleByUsername(user.getUsername());
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new UserDetailsVO(user.getUsername(), user.getPassword(), authorities);
    }

    //修饰符一定要用public 要不加盐的时候报错。。
    public class UserDetailsVO implements UserDetails {
        private String username;
        private String password;
        private Set<GrantedAuthority> grantedAuthorities;
        //添加盐值加密需要
        // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //private String hashedPassword = passwordEncoder.encode(password);

        public UserDetailsVO(String username, String password, Set<GrantedAuthority> grantedAuthorities) {
            this.username = username;
            this.password = password;
            // this.password = passwordEncoder.encode(password);
            this.grantedAuthorities = grantedAuthorities;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return grantedAuthorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }*/
    /*=====================================================================================================*/



















}
