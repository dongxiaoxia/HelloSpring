package xyz.dongxiaoxia.hellospring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.entity.ResourceRole;
import xyz.dongxiaoxia.hellospring.core.repository.ResourceDao;
import xyz.dongxiaoxia.hellospring.service.ResourceService;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
@Component
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public Paging<Resource> query(Resource resource, int pageStart, int pageSize) {
        return resourceDao.page(resource, pageStart, pageSize);
    }

    @Override
    public void add(Resource resource) {
        resourceDao.insert(resource);
    }

    @Override
    public void delete(String id) {
        resourceDao.delete(id);
    }

    @Override
    public void modify(Resource resource) {
        resourceDao.update(resource);
    }

    @Override
    public Resource getById(String id) {
        return resourceDao.get(id);
    }

    @Override
    public List<Resource> findAll() {
        return resourceDao.list(null);
    }

    @Override
    public List<Resource> getUserResources(String userId) {
        return resourceDao.getUserResources(userId);
    }

    @Override
    public List<Resource> getRoleResources(String roleId) {
        return resourceDao.getRoleResources(roleId);
    }

    @Override
    public List<Resource> getResourcesByUserName(String username) {
        return resourceDao.getResourcesByUserName(username);
    }

    @Override
    public void saveRoleResource(String roleId, List<String> list) {
        resourceDao.deleteRoleRescours(roleId);
        ResourceRole resourceRole = new ResourceRole();
        for (String resourceId : list) {
            resourceRole.setRoleId(roleId);
            resourceRole.setRescId(resourceId);
            resourceDao.saveRoleResource(resourceRole);
        }
    }
}
