package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Group;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public interface GroupDao {
    int insert(Group group);

    int delete(String id);

    int update(Group group);

    Group get(String id);

    List<Group> getByParentId(String parentId);

    List<Group> list();
}
