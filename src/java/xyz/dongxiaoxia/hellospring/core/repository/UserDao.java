package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.User;

import java.util.List;

/**
 * Created by chenwendong on 2015/10/29.
 */
public interface UserDao {

    int insert(User user);

    int delete(String id);

    int update(User user);

    User get(String id);

    List<User> findByUsername(String username);

    List<User> findByUsernameAndPassword(String username, String password);

    List<User> list();


}
