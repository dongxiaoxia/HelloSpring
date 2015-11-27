package xyz.dongxiaoxia.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dongxiaoxia.hellospring.Response;
import xyz.dongxiaoxia.hellospring.core.entity.UserLoginList;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;
import xyz.dongxiaoxia.hellospring.service.UserLoginListService;
import xyz.dongxiaoxia.hellospring.util.StringUtils;

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

    /**
     * 查询客户登录信息
     *
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Response query(UserLoginList userLoginList, String pageNow) {
        Response response = new Response();
        try {
           /* PageView pageView = null;
            if (StringUtils.isEmpty(pageNow)) {
                pageView = new PageView(1);
            } else {
                pageView = new PageView(Integer.parseInt(pageNow));
            }
            response.success(userLoginListService.query(pageView, userLoginList));*/
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }
}
