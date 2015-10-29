package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.User;

/**
 * Created by chenwendong on 2015/10/29.
 */
public interface UserDao {

    User get(String id);
}
