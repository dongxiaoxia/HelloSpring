package xyz.dongxiaoxia.hellospring.service;

import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
public interface UserService {

    String add(User user);

    void delete(String id);

    void update(User user);

    User get(String id);

    List<User> list(User user);

    Paging<User> page(User user, int pageStart, int pageSize);

    User querySingleUser(String userName);

    List<Role> findbyUserRole(String userId);

    boolean isExistUsername(String username);
}
