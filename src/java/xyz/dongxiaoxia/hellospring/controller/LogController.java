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
     * @param pageStart
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Response query(Log log, int pageStart, int pageSize) {
        Response response = new Response();
        try {
            if (pageStart < 0) {
                throw new IllegalArgumentException("参数pageStart非法");
            }
            if (pageSize < 1) {
                throw new IllegalArgumentException("参数pageSize非法");
            }
            response.success(logService.query(log, pageStart, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

}
