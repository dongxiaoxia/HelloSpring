package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Group;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public interface GroupDao extends BaseDao<Group> {

    List<Group> getByParentId(String parentId);

}
