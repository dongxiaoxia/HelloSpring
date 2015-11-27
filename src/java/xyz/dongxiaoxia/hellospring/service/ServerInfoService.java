package xyz.dongxiaoxia.hellospring.service;

import xyz.dongxiaoxia.hellospring.core.entity.ServerInfo;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
public interface ServerInfoService {

    Paging<ServerInfo> query(ServerInfo serverInfo, int pageStart, int pageSize);

    List<ServerInfo> queryAll(ServerInfo serverInfo);

    void add(ServerInfo serverInfo);

    void delete(String id);

    ServerInfo getById(String id);

    void modify(ServerInfo serverInfo);
}
