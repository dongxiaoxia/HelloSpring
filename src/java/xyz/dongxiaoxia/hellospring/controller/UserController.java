package xyz.dongxiaoxia.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dongxiaoxia.hellospring.BasicController;
import xyz.dongxiaoxia.hellospring.Response;
import xyz.dongxiaoxia.hellospring.aop.ControllerLog;
import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.entity.UserLoginList;
import xyz.dongxiaoxia.hellospring.core.entity.UserRole;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;
import xyz.dongxiaoxia.hellospring.service.ResourceService;
import xyz.dongxiaoxia.hellospring.service.RoleService;
import xyz.dongxiaoxia.hellospring.service.UserLoginListService;
import xyz.dongxiaoxia.hellospring.service.UserService;
import xyz.dongxiaoxia.hellospring.util.Common;
import xyz.dongxiaoxia.hellospring.util.PageView;
import xyz.dongxiaoxia.hellospring.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/19.
 */
@Controller
@RequestMapping(value = "api/user/")
public class UserController extends BasicController {

    //日志适配器，把日志框架规范起来
    LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserLoginListService userLoginListService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AuthenticationManager myAuthenticationManager;

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    @ControllerLog(module = "UserController", operationType = "add操作", operationName = "添加用户")
    public Response add(User user) {
        Response response;
        try {
            userService.add(user);
            response = new Response().success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = new Response().failure(e.getMessage());
        }
        return response;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Response delete(String id) {
        Response response = new Response();
        try {
            userService.delete(id);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 修改用户
     *
     * @param user
     * @param userRole
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Response update(User user, UserRole userRole) {
        Response response = new Response();
        try {
            userService.update(user);
            if (userRole.getRoleId() != null) {
                roleService.saveUserRole(userRole);
            }
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "get")
    @ResponseBody
    @ControllerLog(module = "UserController", operationType = "get操作", operationName = "获取用户")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response get(String id) {
        Response response = new Response();
        try {
            if (Common.isEmpty(id)) {
                throw new Exception("id不能为空");
            }
            response.success(userService.get(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 查询用户
     *
     * @param user
     * @param pageNow
     * @return
     */
    @RequestMapping(value = "page")
    @ResponseBody
    @ControllerLog(module = "UserController", operationType = "page操作", operationName = "分页查询")
    public Response query(User user, String pageNow) {
        Response response = new Response();
        try {
            PageView pageView = null;
            if (StringUtils.isEmpty(pageNow)) {
                pageView = new PageView(1);
            } else {
                pageView = new PageView(Integer.parseInt(pageNow));
            }
            response.success(userService.page(pageView, user));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "count")
    @ResponseBody
    @ControllerLog(module = "UserController", operationType = "getCount操作", operationName = "获取用户数量")
    public Object getCount() {
        HttpSession session = getSession();
        Response response;
        int count = userService.countUser(null, null);
        User user = new User();
        user.setPassword(String.valueOf(count));
        response = new Response();
        response.success(user);
        //  response.success();
        // response.failure("timeOut");
        return response;
    }

    /**
     * 保存用户分配角色
     *
     * @param userRole
     * @return
     */
    @RequestMapping(value = "allocation")
    @ResponseBody
    @ControllerLog(module = "UserController", operationType = "allocation操作", operationName = "保存用户分配角色")
    public Response allocation(UserRole userRole) {
        Response response = new Response();
        try {
            roleService.saveUserRole(userRole);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    @ControllerLog(module = "UserController", operationType = "login操作", operationName = "用户登录")
    public Response login(HttpServletRequest request) {
        Response response = new Response();
        try {
            //重新登录时销毁该用户的Session
            Object o = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            if (null != o) {
                request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
            }
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 退出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "logout")
    @ResponseBody
    @ControllerLog(module = "UserController", operationType = "logout操作", operationName = "用户退出")
    public Response logout(HttpServletRequest request) {
        Response response = new Response();
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = request.getUserPrincipal().getName();
            List<Resource> resource = resourceService.getResourcesByUserName(username);
            Map map = new HashMap<>();
            map.put("resource", resource);
            response.success(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 登录检查
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "loginCheck")
    @ResponseBody
    @ControllerLog(module = "UserController", operationType = "loginCheck操作", operationName = "登录检查")
    public Response loginCheck(String username, String password, HttpServletRequest request) {
        Response response = new Response();
        try {
            if (!request.getMethod().equals("POST")) {
                throw new Exception("支持POST方法提交！");
            }
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                throw new Exception("用户名或密码不能为空！");
            }
            // 验证用户账号与密码是否正确
            User users = this.userService.querySingleUser(username);
            if (users == null || !users.getPassword().equals(password)) {
                throw new Exception("用户或密码不正确！");
            }
            Authentication authentication = myAuthenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            // 当验证都通过后，把用户信息放在session里
            request.getSession().setAttribute("userSession", users);
            // 记录登录信息
            UserLoginList userLoginList = new UserLoginList();
            userLoginList.setUserId(users.getUsername());
            userLoginList.setLoginIp(Common.toIpAddr(request));
            userLoginListService.add(userLoginList);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }
}
