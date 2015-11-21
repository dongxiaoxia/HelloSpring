package xyz.dongxiaoxia.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dongxiaoxia.hellospring.Response;
import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.entity.UserRole;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;
import xyz.dongxiaoxia.hellospring.service.ResourceService;
import xyz.dongxiaoxia.hellospring.service.UserService;
import xyz.dongxiaoxia.hellospring.util.Common;
import xyz.dongxiaoxia.hellospring.util.PageView;
import xyz.dongxiaoxia.hellospring.util.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2015/11/19.
 */
@Controller
@RequestMapping(value = "api/resource/")
public class ResourcesController {
    LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(ResourcesController.class);

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;

    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Response add(Resource resource) {
        Response response = new Response();
        try {
            resourceService.add(resource);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Response delete(String id) {
        Response response = new Response();
        try {
            resourceService.delete(id);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 修改资源
     *
     * @param resource
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Response update(Resource resource) {
        Response response = new Response();
        try {
            resourceService.modify(resource);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 获取资源
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "get")
    @ResponseBody
    public Response get(String id) {
        Response response = new Response();
        try {
            response.success(resourceService.getById(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 查询资源
     *
     * @param resource
     * @param pageNow
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Response query(Resource resource, String pageNow) {
        Response response = new Response();
        try {
            PageView pageView = null;
            if (StringUtils.isEmpty(pageNow)) {
                pageView = new PageView(1);
            } else {
                pageView = new PageView(Integer.parseInt(pageNow));
            }
            response.success(resourceService.query(pageView, resource));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 某个用户拥有的权限
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "permissioUser")
    @ResponseBody
    public Response permissioUser(String userId) {
        Response response = new Response();
        List<Map> list = new ArrayList<>();
        try {
            List<Resource> resources = resourceService.getUserResources(userId);
            List<Resource> allRes = resourceService.findAll();
            Map<String, String> dataMap = new HashMap<>();
            for (Resource resource : allRes) {
                boolean flag = false;
                for (Resource ur : resources) {//用户拥有的权限
                    if (ur.getId().equals(resource.getId())) {
                        dataMap.put("fid", resource.getId());
                        dataMap.put("pfid", resource.getParentId());
                        dataMap.put("fname", resource.getName());
                        dataMap.put("ischeck", "true");
                        list.add(dataMap);
                        flag = true;
                    }
                }
                if (!flag) {
                    dataMap.put("fid", resource.getName());
                    dataMap.put("pfid", resource.getParentId());
                    dataMap.put("fname", resource.getName());
                }
            }
            List<Role> roles = userService.findbyUserRole(userId);
            Map map = new HashMap();
            if (roles != null) {
                map.put("roles", roles);
            }
            map.put("resources", list);
            response.success(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 某个角色拥有的权限
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "permissioRole")
    @ResponseBody
    public Response permissioRole(String roleId) {
        Response response = new Response();
        List<Map> list = new ArrayList<>();
        try {
            List<Resource> resources = resourceService.getUserResources(roleId);
            List<Resource> allRes = resourceService.findAll();
            Map<String, String> dataMap = new HashMap<>();
            for (Resource resource : allRes) {
                boolean flag = false;
                for (Resource ur : resources) {//用户拥有的权限
                    if (ur.getId().equals(resource.getId())) {
                        dataMap.put("fid", resource.getId());
                        dataMap.put("pfid", resource.getParentId());
                        dataMap.put("fname", resource.getName());
                        dataMap.put("ischeck", "true");
                        list.add(dataMap);
                        flag = true;
                    }
                }
                if (!flag) {
                    dataMap.put("fid", resource.getName());
                    dataMap.put("pfid", resource.getParentId());
                    dataMap.put("fname", resource.getName());
                }
            }
            List<Role> roles = userService.findbyUserRole(roleId);
            Map map = new HashMap();
            if (roles != null) {
                map.put("roles", roles);
            }
            map.put("resources", list);
            response.success(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 保存角色资源
     *
     * @param rescId
     * @param roleId
     * @return
     */
    @RequestMapping(value = "saveRoleRescoure")
    @ResponseBody
    public Response saveRoleRescoure(String roleId, String rescId) {
        Response response = new Response();
        try {
            List<String> list = Common.removeSameItem(Arrays.asList(rescId.split(",")));
            resourceService.saveRoleResource(roleId, list);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }
}
