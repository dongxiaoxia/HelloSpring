package xyz.dongxiaoxia.hellospring.service;

import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public interface ResourceService {

    Paging<Resource> query(Resource resources, int pageStart, int pageSize);

    void add(Resource resources);

    void delete(String id);

    void modify(Resource resources);

    Resource getById(String id);

    List<Resource> findAll();

    //<!-- 根据用户Id获取该用户的权限-->
    List<Resource> getUserResources(String userId);

    //<!-- 根据用户Id获取该用户的权限-->
    List<Resource> getRoleResources(String roleId);

    //<!-- 根据用户名获取该用户的权限-->
    List<Resource> getResourcesByUserName(String username);

    void saveRoleResource(String roleId, List<String> list);

}
