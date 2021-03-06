package xyz.dongxiaoxia.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dongxiaoxia.hellospring.Response;
import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;
import xyz.dongxiaoxia.hellospring.service.ResourceService;
import xyz.dongxiaoxia.hellospring.service.RoleService;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/19.
 */
@Controller
@RequestMapping(value = "api/role/")
public class RoleController {

    LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Response add(Role role, String resId) {
        Response response = new Response();
        try {
            roleService.add(role);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Response delete(String id) {
        Response response = new Response();
        try {
            roleService.delete(id);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 获取角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "get")
    @ResponseBody
    public Response get(String id) {
        Response response = new Response();
        try {
            response.success(roleService.get(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Response get(Role role) {
        Response response = new Response();
        try {
            roleService.modify(role);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 权限树
     *
     * @return
     */
    @RequestMapping(value = "permissio")
    @ResponseBody
    public Response permissio() {
        Response response = new Response();
        try {
            List<Resource> allRes = resourceService.findAll();
            List<Map> list = new ArrayList<>();
            for (Resource resource : allRes) {
                Map<String, String> dataMap = new HashMap<>();
                dataMap.put("fid", resource.getId());
                dataMap.put("pfid", resource.getParentId());
                dataMap.put("fname", resource.getName());
                list.add(dataMap);
            }
            response.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 分页查询
     *
     * @param role
     * @param pageStart
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "page")
    @ResponseBody
    public Response page(Role role, int pageStart, int pageSize) {
        Response response = new Response();
        try {
            Paging<Role> paging = roleService.page(role, pageStart, pageSize);
            response.success(paging);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }
}
