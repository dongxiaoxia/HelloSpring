package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Operation;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public interface OperationDao extends BaseDao {
    List<Operation> listByParentId(String parentId);

    List<Operation> listByPrefixUrl(String prefixUrl);

}
