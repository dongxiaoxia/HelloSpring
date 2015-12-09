package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.entity.ResourceRole;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public interface ResourceDao extends BaseDao {
    //<!-- 根据用户Id获取该用户的权限-->
    List<Resource> getUserResources(String userId);

    //<!-- 根据角色Id获取该角色的权限-->
    List<Resource> getRoleResources(String roleId);

    //<!-- 根据用户名获取该用户的权限-->
    List<Resource> getResourcesByUserName(String username);

    void saveRoleResource(ResourceRole resourceRole);

    void deleteRoleRescours(String roleId);
}
