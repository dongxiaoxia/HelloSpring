package xyz.dongxiaoxia.hellospring.service;

import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.util.PageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
public interface UserService {
    PageView query(PageView pageView, User user);

    void add(User user);

    void delete(String id);

    void modify(User user);

    User getById(String id);

    int countUser(String userName, String userPassword);

    User querySingleUser(String userName);

    List<Role> findbyUserRole(String userId);
}
