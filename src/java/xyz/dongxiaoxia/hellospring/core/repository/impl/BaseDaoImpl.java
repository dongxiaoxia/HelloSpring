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
 * <p/>
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
        int result;
        KeyHolder holder = new GeneratedKeyHolder();
        result = jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                return ps;
            }
        }, holder);
        System.out.println(holder.getKey().intValue());
        return holder.getKey().intValue();

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
     * @param ids   主键数组
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
     * 注意点：由于主键id会唯一标识一个数据项，有些人会使用queryForObject获取数据项，若未找到目标数据时，该方法并非返回null，而是抛异常EmptyResultDataAccessException。应使用query方法，并检测返回值数据量
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

    /**
     * 通用实体类批量添加方法
     *
     * @param list  实体类对象集合
     * @param clazz 实体类类型
     */
    @Override
    public void $batchSave(final List<T> list, Class<T> clazz) {
        int[] saveCounts = jdbcTemplate.batchUpdate(Finder.getBatchSave(clazz), Finder.getBatchSaveObject(list));
    }

    /**
     * 通用实体类批量更新方法
     *
     * @param list  实体类对象集合
     * @param clazz 实体类类型
     */
    @Override
    public void $batchUpdate(final List<T> list, Class<T> clazz) {
        int[] updateCounts = jdbcTemplate.batchUpdate(Finder.getBatchUpdate(clazz), Finder.getBatchUpdateObject(list));
    }
}
