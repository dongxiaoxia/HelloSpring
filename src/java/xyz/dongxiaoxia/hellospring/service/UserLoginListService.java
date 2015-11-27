package xyz.dongxiaoxia.hellospring.service;

import xyz.dongxiaoxia.hellospring.core.entity.UserLoginList;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
public interface UserLoginListService {
    void add(UserLoginList userLoginList);

    List<UserLoginList> list();

    Paging<UserLoginList> query(UserLoginList userLoginList, int pageStart, int pageSize);
}
