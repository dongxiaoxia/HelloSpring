package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.repository.BaseDao;
import xyz.dongxiaoxia.hellospring.core.repository.persistence.Finder;
import xyz.dongxiaoxia.hellospring.util.Paging;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by dongxiaoxia on 2015/11/19.
 *
 * 持久层操作基类，所有的dao实现类都应该继承这个类
 */
@Repository
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    private JdbcTemplate jdbcTemplate;
    //private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        //this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("system_user");
        // this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * 通用实体类添加方法
     *
     * @param t 实体类对象
     * @return
     */
    @Override
    public int $save(T t) {
        final String sql = Finder.getInsertSql(t);
        int result = 0;
        KeyHolder holder = new GeneratedKeyHolder();
        result = jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                return ps;
            }
        }, holder);
        System.out.println(holder.getKey().intValue());
        return result;

    }

    /**
     * 通用实体类删除方法
     *
     * @param id    主键
     * @param clazz 实体类
     */

    @Override
    public void $delete(String id, Class<T> clazz) {
        getJdbcTemplate().update(Finder.getDeleteSql(id, clazz));
    }

    /**
     * 通用实体类删除方法
     *
     * @param ids    主键数组
     * @param clazz 实体类
     */

    @Override
    public void $delete(String[] ids, Class<T> clazz) {
        getJdbcTemplate().update(Finder.getDeleteSql(ids, clazz));
    }

    /**
     * 通用实体类更新方法
     *
     * @param t 实体类对象
     */
    @Override
    public void $update(T t) {
        getJdbcTemplate().update(Finder.getUpdateSql(t));
    }

    /**
     * 通用实体类获取方法
     *
     * @param id    实体类对象id
     * @param clazz 实体类类型
     * @return
     */
    @Override
    public T $get(String id, Class<T> clazz) {
        List<T> list = getJdbcTemplate().query(Finder.getSelectByIdentitySql(id, clazz), new BaseRowMapper<>(clazz));
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通用实体类查询方法
     *
     * @param t 实体类对象
     * @return
     */
    @Override
    public List<T> $query(T t) {
        return getJdbcTemplate().query(Finder.getQuerySql(t), new BaseRowMapper<>(t.getClass()));
    }

    /**
     * 获取数据库记录总条数
     *
     * @param t 实体类对象
     * @return
     */
    @Override
    public int $count(T t) {
       /* List<T> list = getJdbcTemplate().query(Finder.getQuerySql(t), new BaseRowMapper<>(t.getClass()));
        if (list!= null && list.size()>0){
            return list.size();
        }else {
            return 0;
        }*/
        int count = getJdbcTemplate().queryForObject(Finder.getCountSql(t), Integer.class);
        return count;
    }

    /**
     * 通用实体类分页查询方法
     *
     * @param t         实体类对象
     * @param pageStart 分页起始页
     * @param pageSize  分页每页大小
     * @return
     */
    @Override
    public Paging $page(T t, int pageStart, int pageSize) {
        List<T> list = getJdbcTemplate().query(Finder.getPageSql(t, pageStart, pageSize), new BaseRowMapper(t.getClass()));
        int total = $count(t);
        Paging paging = new Paging(pageStart, pageSize);
        paging.setTotalRecord(total);
        paging.setData(list);
        return paging;
    }

    @Override
    public void $batchSave(List<T> list) {

    }

    @Override
    public void $batchUpdate(List<T> list){

    }
}
