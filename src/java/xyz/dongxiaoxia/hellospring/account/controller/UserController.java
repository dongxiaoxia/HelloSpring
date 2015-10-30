package xyz.dongxiaoxia.hellospring.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dongxiaoxia.hellospring.Response;
import xyz.dongxiaoxia.hellospring.account.service.UserService;
import xyz.dongxiaoxia.hellospring.core.entity.User;

import javax.annotation.Resource;

/**
 * Created by chenwendong on 2015/10/29.
 */

@Controller
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    private UserService userService;

    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = userService.getUser("123");
        System.out.println(user.getName());
    }

    @RequestMapping(value = "/get")
    public void get(@RequestParam(value = "id") String id) {
        User user = userService.getUser(id);
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public Object getCount() {
        Response response;
        int count = userService.getUserCount();
        User user = new User();
        user.setPassword(String.valueOf(count));
        response = new Response();
        //   response.success(user);
        //  response.success();
        response.failure("timeOut");
        return response;
    }
}
