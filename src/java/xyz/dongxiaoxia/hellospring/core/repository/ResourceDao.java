package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Resource;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public interface ResourceDao {
    int insert(Resource resource);

    int delete(String id);

    int update(Resource resource);

    Resource get(String id);

    List<Resource> list();
}
