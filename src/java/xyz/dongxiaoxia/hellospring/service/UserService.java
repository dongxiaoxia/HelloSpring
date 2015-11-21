package xyz.dongxiaoxia.hellospring.service;

import org.springframework.security.access.annotation.Secured;
import xyz.dongxiaoxia.hellospring.ServiceException;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.util.PageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
public interface UserService {

    void add(User user);

    void delete(String id);

    void update(User user);

    User get(String id);

    List<User> list(User user);

    PageView page(PageView pageView, User user);

    @Secured("ROLE_ADMIN")
    int countUser(String userName, String userPassword);

    User querySingleUser(String userName);

    List<Role> findbyUserRole(String userId);

    boolean isExistUsername(String username);
}