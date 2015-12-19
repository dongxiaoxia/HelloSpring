package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;

import java.util.List;

/**
 * Created by chenwendong on 2015/10/29.
 */
public interface UserDao extends BaseDao<User> {
    List<Role> listUserRoles(String userId);
}
