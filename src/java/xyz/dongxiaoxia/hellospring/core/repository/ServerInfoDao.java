package xyz.dongxiaoxia.hellospring.core.repository;

import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.ServerInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
public interface ServerInfoDao {
    int insert(ServerInfo serverInfo);

    int delete(String id);

    int update(ServerInfo serverInfo);

    ServerInfo get(String id);

    List<ServerInfo> list();
}
