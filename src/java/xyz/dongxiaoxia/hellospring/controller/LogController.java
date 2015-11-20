package xyz.dongxiaoxia.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dongxiaoxia.hellospring.Response;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;
import xyz.dongxiaoxia.hellospring.service.LogService;
import xyz.dongxiaoxia.hellospring.util.PageView;
import xyz.dongxiaoxia.hellospring.util.StringUtils;

/**
 * Created by Administrator on 2015/11/19.
 */
@Controller
@RequestMapping(value = "api/log/")
public class LogController {
    LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(LogController.class);
    @Autowired
    private LogService logService;

    /**
     * 查询客户登陆信息
     *
     * @param log
     * @param pageNow
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Response query(Log log, String pageNow) {
        Response response = new Response();
        try {
            PageView pageView = null;
            if (StringUtils.isEmpty(pageNow)) {
                pageView = new PageView(1);
            } else {
                pageView = new PageView(Integer.parseInt(pageNow));
            }
            response.success(logService.query(pageView, log));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

}
