package xyz.dongxiaoxia.hellospring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.ServerInfo;
import xyz.dongxiaoxia.hellospring.core.repository.ServerInfoDao;
import xyz.dongxiaoxia.hellospring.service.ServerInfoService;
import xyz.dongxiaoxia.hellospring.util.PageView;

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
    public PageView query(PageView pageView, ServerInfo serverInfo) {
        List<ServerInfo> list = serverInfoDao.query(pageView, serverInfo);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public List<ServerInfo> queryAll(ServerInfo serverInfo) {
        return serverInfoDao.list();
    }

    @Override
    public void add(ServerInfo serverInfo) {
        serverInfoDao.insert(serverInfo);
    }

    @Override
    public void delete(String id) {
        serverInfoDao.delete(id);
    }

    @Override
    public ServerInfo getById(String id) {
        return serverInfoDao.get(id);
    }

    @Override
    public void modify(ServerInfo serverInfo) {
        serverInfoDao.update(serverInfo);
    }
}
