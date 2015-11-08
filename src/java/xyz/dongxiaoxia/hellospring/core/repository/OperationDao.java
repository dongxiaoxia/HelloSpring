package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Operation;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public interface OperationDao {
    int insert(Operation operation);

    int delete(String id);

    int update(Operation operation);

    Operation get(String id);

    List<Operation> listByParentId(String parentId);

    List<Operation> listByPrefixUrl(String prefixUrl);

    List<Operation> list();
}
