package xyz.dongxiaoxia.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dongxiaoxia.hellospring.Response;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;
import xyz.dongxiaoxia.hellospring.service.UserLoginListService;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */

@Controller
@RequestMapping(value = "api/userLoginList/")
public class UserLoginListController {

    LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(UserLoginListController.class);
    @Autowired
    private UserLoginListService userLoginListService;

    /**
     * 查询所有用户的登录日志
     *
     * @return
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Response list() {
        Response response = new Response();
        try {
            response.success(userLoginListService.list());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }
}
