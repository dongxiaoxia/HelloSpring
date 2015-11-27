package xyz.dongxiaoxia.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dongxiaoxia.hellospring.Response;
import xyz.dongxiaoxia.hellospring.core.entity.ServerInfo;
import xyz.dongxiaoxia.hellospring.core.entity.ServerStatus;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;
import xyz.dongxiaoxia.hellospring.service.ServerInfoService;
import xyz.dongxiaoxia.hellospring.util.Common;
import xyz.dongxiaoxia.hellospring.util.PropertiesUtils;
import xyz.dongxiaoxia.hellospring.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/19.
 *
 *  * 服务器监控的处理
 * 可以收集的信息包括： 1， CPU信息，包括基本信息（vendor、model、mhz、cacheSize）和统计信息（user、sys、idle
 * 、nice、wait） 2， 文件系统信息，包括Filesystem、Size、Used、Avail、Use%、Type 3，
 * 事件信息，类似Service Control Manager 4， 内存信息，物理内存和交换内存的总数、使用数、剩余数；RAM的大小 5，
 * 网络信息，包括网络接口信息和网络路由信息 6， 进程信息，包括每个进程的内存、CPU占用数、状态、参数、句柄 7， IO信息，包括IO的状态，读写大小等
 * 8， 服务状态信息 9， 系统信息，包括操作系统版本，系统资源限制情况，系统运行时间以及负载，JAVA的版本信息等.
 */
@Controller
@RequestMapping(value = "api/serverInfo/")
public class ServerInfoController {

    LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(ServerInfoController.class);

    @Autowired
    private ServerInfoService serverInfoService;

    /**
     * 查询用户
     *
     * @param serverInfo
     * @param pageNow
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Response query(ServerInfo serverInfo, int pageStart, int pageSize) {
        Response response = new Response();
        try {
            if (pageStart < 0) {
                throw new IllegalArgumentException("参数pageStart非法");
            }
            if (pageSize < 1) {
                throw new IllegalArgumentException("参数pageSize非法");
            }
            response.success(serverInfoService.query(serverInfo, pageStart, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 获取服务器基本信息
     *
     * @return
     */
    @RequestMapping(value = "info")
    @ResponseBody
    public Response serverBaseInfo() {
        Response response = new Response();
        try {
//            Map<String, Object> dataMap = new HashMap<>();
//            ServerStatus serverStatus = Common.getServerStatus();
//            dataMap.put("data", serverStatus);
//            response.success(dataMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 预警监控信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "warnInfo")
    @ResponseBody
    public Response warnInfo(HttpServletRequest request) {
        Response response = new Response();
        try {
//            ServerStatus status = Common.getServerStatus();
            ServerStatus status = null;
            Map<String, Object> dataMap = new HashMap<String, Object>();

            String cpuUsage = status.getCpuUsage();
            long FreeMem = status.getFreeMem();
            long useMem = status.getUsedMem();
            long TotalMem = status.getTotalMem();
            String serverUsage = Common.fromUsage(useMem, TotalMem);
            dataMap.put("cpuUsage", cpuUsage);
            dataMap.put("FreeMem", FreeMem);
            dataMap.put("TotalMem", TotalMem);
            dataMap.put("serverUsage", serverUsage);
            long JvmFreeMem = status.getJvmFreeMem();
            long JvmTotalMem = status.getJvmTotalMem();
            String JvmUsage = Common.fromUsage(JvmTotalMem - JvmFreeMem, JvmTotalMem);
            dataMap.put("JvmFreeMem", JvmFreeMem);
            dataMap.put("JvmTotalMem", JvmTotalMem);
            dataMap.put("JvmUsage", JvmUsage);
            dataMap.put("cpu", PropertiesUtils.findPropertiesKey("cpu"));
            dataMap.put("jvm", PropertiesUtils.findPropertiesKey("jvm"));
            dataMap.put("ram", PropertiesUtils.findPropertiesKey("ram"));
            dataMap.put("toEmail", PropertiesUtils.findPropertiesKey("toEmail"));
            // dataMap.put("diskInfos", status.getDiskInfos());
            response.success(dataMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }

    /**
     * 获取服务器基本信息
     *
     * @return
     */
    @RequestMapping(value = "modifySer")
    @ResponseBody
    public Response modifySer(HttpServletRequest request, String key, String value) {
        Response response = new Response();
        Map<String, Object> dataMap = new HashMap<>();
        try {
            // 从输入流中读取属性列表（键和元素对）
            PropertiesUtils.modifyProperties(key, value);
            dataMap.put("flag", true);
            response.success(dataMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.failure(e.getMessage());
        }
        return response;
    }
}
