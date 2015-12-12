package xyz.dongxiaoxia.hellospring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.ServerInfo;
import xyz.dongxiaoxia.hellospring.core.repository.ServerInfoDao;
import xyz.dongxiaoxia.hellospring.service.ServerInfoService;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
@Component
@Transactional
public class ServerInfoServiceImpl implements ServerInfoService {

    @Autowired
    private ServerInfoDao serverInfoDao;

    @Override
    public Paging<ServerInfo> query(ServerInfo serverInfo, int pageStart, int pageSize) {
        return serverInfoDao.$page(serverInfo, pageStart, pageSize);
    }

    @Override
    public List<ServerInfo> queryAll(ServerInfo serverInfo) {
        return serverInfoDao.$query(null);
    }

    @Override
    public void add(ServerInfo serverInfo) {
        serverInfoDao.$save(serverInfo);
    }

    @Override
    public void delete(String id) {
        serverInfoDao.$delete(id, ServerInfo.class);
    }

    @Override
    public ServerInfo getById(String id) {
        return (ServerInfo) serverInfoDao.$get(id, ServerInfo.class);
    }

    @Override
    public void modify(ServerInfo serverInfo) {
        serverInfoDao.$update(serverInfo);
    }
}
