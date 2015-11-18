package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.UserLoginList;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
public interface UserLoginListDao {

    int insert(UserLoginList userLoginList);

    int delete(String id);

    int update(UserLoginList userLoginList);

    UserLoginList get(String id);

    List<UserLoginList> list();
}
