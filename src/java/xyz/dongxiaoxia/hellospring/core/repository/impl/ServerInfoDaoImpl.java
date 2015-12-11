package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.ServerInfo;
import xyz.dongxiaoxia.hellospring.core.repository.ServerInfoDao;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
@Repository
public class ServerInfoDaoImpl extends BaseDaoImpl implements ServerInfoDao {

    private static final String TABLE_NAME = "SYSTEM_SERVERINFO";

    public ServerInfoDaoImpl() {
        super(TABLE_NAME);
    }


    public int insert(ServerInfo serverInfo) {
        return 0;
    }

    public int update(ServerInfo serverInfo) {
        return 0;
    }


    public List<ServerInfo> list(ServerInfo serverInfo) {
        return null;
    }


    public Paging<ServerInfo> page(ServerInfo serverInfo, int pageStart, int pageSize) {
        return null;
    }
}
