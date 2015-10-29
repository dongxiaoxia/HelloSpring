package xyz.dongxiaoxia.hellospring.account.controller;

import xyz.dongxiaoxia.hellospring.account.service.UserService;
import xyz.dongxiaoxia.hellospring.core.entity.User;

/**
 * Created by chenwendong on 2015/10/29.
 */
public class UserController {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = userService.getUser("123");
        System.out.println(user.getName());
    }
}
