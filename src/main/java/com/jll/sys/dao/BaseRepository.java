package com.jll.sys.dao;

import com.jll.sys.annotation.NoColumn;
import com.jll.sys.page.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daffy on 2019/7/11.
 */
public class BaseRepository<T> {
    @Autowired
    protected JdbcTemplate jdbcTemplate;



    public BaseRepository() {
        super();
    }

    /**
     * 获取分页信息，包括当前页码（从0开始），页面大小，总页数，记录总数
     * @param offset
     * @param step
     * @param sql
     * @param params
     * @return
     */
    protected Paging findPaging(Integer offset, Integer step, String sql, ArrayList<Object> params) {
        ResultSetExtractor<Paging> rse = (ResultSet rs) -> {
            if (rs.next()) {
                int totalRecordCount = rs.getInt("totalRecordCount");
                // 无记录，直接返回。
                if (totalRecordCount == 0) {
                    return new Paging(offset, step, 0, 0);
                }
                int pageCount = 0;
                if (totalRecordCount % step == 0) {
                    pageCount = totalRecordCount / step;
                } else {
                    pageCount = (totalRecordCount / step) + 1;
                }
                Paging paging2 = new Paging(offset, step, pageCount, totalRecordCount);
                return paging2;
            } else {
                return new Paging(offset, step, 0, 0);
            }
        };
        if (params.isEmpty()) {
            return jdbcTemplate.query(sql, rse);
        } else {
            return jdbcTemplate.query(sql,
                    params.toArray(), rse);
        }
    }

    /**
     * 根据分页信息，为sql字符串加上limit子句。
     * @param sql
     * @param offset
     * @param step
     * @param params
     * @return
     */
    protected String addLimit(String sql, Integer offset, Integer step, ArrayList<Object> params) {
        StringBuilder sbSql = new StringBuilder(sql);
        if (offset > 0) {
            sbSql.append(" limit ?,? ");
            params.add(offset * step);
            params.add(step);
        } else {
            sbSql.append(" limit ? ");
            params.add(step);
        }
        return sbSql.toString();
    }

    /**
     * 根据Id查找对象。封装了实体对象的操作，简化了用户自定义Repository的代码。
     * @param sql
     * @param id
     * @param c 实体类的Class
     * @return 实体类的对象
     */
    protected T queryById(String sql, String id, Class<T> c) {
        return jdbcTemplate.query(sql, new Object[]{id}, (ResultSet rs) -> {
            if (rs.next()) {
                T t = null;
                try {
                    t = c.newInstance();
                    Field[] fields = c.getFields();
                    for (Field f : fields) {
                        NoColumn[] ncArr = f.getAnnotationsByType(NoColumn.class);
                        if (ncArr == null || ncArr.length == 0) {
                            String fieldName = f.getName();
                            f.set(t, rs.getObject(fieldName));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                return t;
            } else {
                return null;
            }
        });
    }

    protected List<T> queryList(String sql, Class<T> c) {
        return queryList(sql, null, c);
    }

    /**
     * 查询实体对象的列表
     * @param sql
     * @param params sql用到的参数。
     * @param c 实体类的Class
     * @return 一个包含实体类对象的List
     */
    protected List<T> queryList(String sql, ArrayList<Object> params, Class<T> c) {
        ResultSetExtractor<List<T>> rse2 = (ResultSet rs) -> {
            List<T> result = new ArrayList<T>();
            while (rs.next()) {
                T t = null;
                try {
                    t = c.newInstance();
                    Field[] fields = c.getFields();
                    for (Field f : fields) {
                        NoColumn[] ncArr = f.getAnnotationsByType(NoColumn.class);
                        if (ncArr == null || ncArr.length == 0) {
                            String fieldName = f.getName();
                            f.set(t, rs.getObject(fieldName));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                if (t != null) {
                    result.add(t);
                }
            }
            return result;
        };
        if (params == null || params.isEmpty()) {
            return jdbcTemplate.query(sql.toString(), rse2);
        } else {
            return jdbcTemplate.query(sql.toString(), params.toArray(), rse2);
        }
    }
}