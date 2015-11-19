package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.ServerInfo;
import xyz.dongxiaoxia.hellospring.core.repository.ServerInfoDao;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
@Repository
public class ServerInfoDaoImpl implements ServerInfoDao {

    @Override
    public int insert(ServerInfo serverInfo) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int update(ServerInfo serverInfo) {
        return 0;
    }

    @Override
    public ServerInfo get(String id) {
        return null;
    }

    @Override
    public List<ServerInfo> list() {
        return null;
    }
}
