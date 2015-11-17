package xyz.dongxiaoxia.hellospring.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.RoleDao;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/11/12.
 */
@Transactional
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户" + username + "不存在！！！");
        }
        List<Role> roleList = roleDao.findRoleByUsername(user.getName());
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new UserDetailsVO(user.getName(), user.getPassword(), authorities);
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


    }
}
