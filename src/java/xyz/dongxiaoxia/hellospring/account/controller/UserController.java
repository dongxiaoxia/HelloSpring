package xyz.dongxiaoxia.hellospring.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dongxiaoxia.hellospring.BasicController;
import xyz.dongxiaoxia.hellospring.Response;
import xyz.dongxiaoxia.hellospring.account.service.UserService;
import xyz.dongxiaoxia.hellospring.aop.MyLog;
import xyz.dongxiaoxia.hellospring.core.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by chenwendong on 2015/10/29.
 */

@Controller
@RequestMapping(value = "/api/user")
public class UserController extends BasicController {
    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    @MyLog(operationType = "login", operationName = "登录")
    public Object login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password
            , HttpSession session) {
        Response response = new Response();
        try {
            User user = userService.login(username, password);
            session.setAttribute("user", user);
            response.success("登陆成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.failure(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    @MyLog(operationType = "get操作", operationName = "获取用户")
    public Object get(@RequestParam(value = "id") String id, HttpSession session) {
        Response response = new Response();
        try {
            if (session.getAttribute("user") == null) {
                throw new Exception("验证失败，请重新登录");
            }
            User user = userService.get(id);
            response.success(user);
        } catch (Exception e) {
            response.failure(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    @MyLog(operationType = "getCount操作", operationName = "获取用户数量")
    public Object getCount() {
        HttpSession session = getSession();
        Response response;
        int count = userService.getUserCount();
        User user = new User();
        user.setPassword(String.valueOf(count));
        response = new Response();
        response.success(user);
        //  response.success();
        // response.failure("timeOut");
        return response;
    }
}
