package xyz.dongxiaoxia.hellospring.core.repository.impl;

import xyz.dongxiaoxia.hellospring.core.repository.BaseDao;
import xyz.dongxiaoxia.hellospring.core.repository.aaa;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/12/9.
 */
public class aaaImpl extends BaseDaoImpl implements aaa {


    public aaaImpl(String tableName) {
        super(tableName);
    }

    public static void main(String[] args) {
        aaa a = new aaaImpl("asdf");

    }
}
