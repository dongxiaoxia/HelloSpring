package xyz.dongxiaoxia.hellospring.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dongxiaoxia.hellospring.BasicController;
import xyz.dongxiaoxia.hellospring.Response;
import xyz.dongxiaoxia.hellospring.logging.ControllerLog;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;

import java.util.*;

/**
 * Created by Administrator on 2015/12/19.
 * <p/>
 * 主页控制器
 */
@Controller
@RequestMapping(value = "api/home/")
public class HomeController extends BasicController {

    LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(HomeController.class);

    /**
     * 获取主页信息
     *
     * @return
     */
    @RequestMapping(value = "info")
    @ResponseBody
    @ControllerLog(module = "HomeController", operationType = "getInfo操作", operationName = "获取主页信息")
    public Response getInfo() {
        Response response = new Response();
        try {
//            Object o = getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//            Object o1 = getRequest().getUserPrincipal();
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            Iterator iterator = userDetails.getAuthorities().iterator();
            List<String> authorities = new ArrayList<>();
            while (iterator.hasNext()) {
                authorities.add(iterator.next().toString());
            }
            Map map = new HashMap<>();
            map.put("username", username);
            map.put("anthorities", authorities);
            response.success(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

}
