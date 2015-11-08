package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Privilege;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public interface PrivilegeDao {
    int insert(Privilege privilege);

    int delete(String id);

    int update(Privilege privilege);

    Privilege get(String id);

    List<Privilege> list();

    List<Privilege> listByType(String type);

}
