package com.jhtsoft.dao.impl;

import com.jhtsoft.bean.PageBean;
import com.jhtsoft.dao.ReadObjectDao;
import com.jhtsoft.util.SqlPreUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: AbstractReadObjectDao
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
@Transactional(
        propagation = Propagation.REQUIRED
)
public abstract class AbstractReadObjectDao implements ReadObjectDao {
    public AbstractReadObjectDao() {
    }

    public List<Object> getObjectsByHql(String hql) {
        return this.getObjectsByHql(hql, (Integer)null, (Integer)null, (List)null);
    }

    public <T> List<T> getObjectsByHql(String hql, Integer start, Integer size, List<Object> preParameters) {
        Query query = this.getCurrentSession().createQuery(hql);
        SqlPreUtil.setValForQuery(query, preParameters);
        if (start != null) {
            query.setFirstResult(start.intValue()).setMaxResults(size.intValue());
        }

        return query.list();
    }

    public <T> List<T> getObjectsBySql(String sql, Class<?> classObj) {
        return this.getObjectsBySql(sql, classObj, (Integer)null, (Integer)null, (List)null);
    }

    public <T> List<T> getObjectsBySql(String sql, Class<?> classObj, Integer start, Integer size, List<Object> preParameters) {
        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        sqlQuery.addEntity(classObj);
        SqlPreUtil.setValForSqlQuery(sqlQuery, preParameters);
        if (start != null) {
            sqlQuery.setFirstResult(start.intValue()).setMaxResults(size.intValue());
        }

        return sqlQuery.list();
    }

    public List<Map<String, Object>> getMapOfObjectsBySql(String sql) {
        return this.getMapOfObjectsBySql(sql, (Integer)null, (Integer)null, (List)null);
    }

    public List<Map<String, Object>> getMapOfObjectsBySql(String sql, List<Object> preParameters) {
        return this.getMapOfObjectsBySql(sql, (Integer)null, (Integer)null, preParameters);
    }

    public Map<String, Object> getMapOfObjectBySql(String sql) {
        List<Map<String, Object>> olist = this.getMapOfObjectsBySql(sql);
        return olist != null && !olist.isEmpty() ? (Map)olist.get(0) : null;
    }

    public Map<String, Object> getMapOfObjectBySql(String sql, List<Object> preParameters) {
        List<Map<String, Object>> olist = this.getMapOfObjectsBySql(sql, (Integer)null, (Integer)null, preParameters);
        return olist != null && !olist.isEmpty() ? (Map)olist.get(0) : null;
    }

    public List<Map<String, Object>> getMapOfObjectsBySql(String sql, Integer start, Integer size, List<Object> preParameters) {
        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        SqlPreUtil.setValForSqlQuery(sqlQuery, preParameters);
        if (start != null) {
            sqlQuery.setFirstResult(start.intValue()).setMaxResults(size.intValue());
        }

        return sqlQuery.list();
    }

    public Object getObjectByHql(String hql) {
        Query query = this.getCurrentSession().createQuery(hql);
        query.setFirstResult(0).setMaxResults(1);
        return query.uniqueResult();
    }

//    public <T> T getObjectById(Class<T> objclass, Integer id) {
//        Object object = this.getCurrentSession().get(objclass, id);
//        return object == null ? null : object;
//    }
//
//    public <T> T getObjectBySql(String sql, Class<T> classObj) {
//        return this.getObjectBySql(sql, classObj, (List)null);
//    }
//
//    public <T> T getObjectBySql(String sql, Class<T> classObj, List<Object> preParameters) {
//        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
//        SqlPreUtil.setValForQuery(sqlQuery, preParameters);
//        sqlQuery.addEntity(classObj);
//        sqlQuery.setFirstResult(0).setMaxResults(1);
//        return sqlQuery.uniqueResult();
//    }
    public <T> T getObjectById(Class<T> objclass, Integer id) {
        Object object = this.getCurrentSession().get(objclass, id);
        return object == null ? null : (T) object;
    }

    public <T> T getObjectBySql(String sql, Class<T> classObj) {
        return this.getObjectBySql(sql, (Class<T>) classObj, (List<Object>) null);
    }

    public <T> T getObjectBySql(String sql, Class<T> classObj, List<Object> preParameters) {
        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        SqlPreUtil.setValForQuery(sqlQuery, preParameters);
        sqlQuery.addEntity(classObj);
        sqlQuery.setFirstResult(0).setMaxResults(1);
        return (T) sqlQuery.uniqueResult();
    }

    public int countNumByHql(String hql, List<Object> preParameters) {
        Query query = this.getCurrentSession().createQuery(hql);
        SqlPreUtil.setValForQuery(query, preParameters);
        List<Long> num = query.list();
        return num != null && !num.isEmpty() ? (num.get(0) == null ? 0 : ((Long)num.get(0)).intValue()) : 0;
    }

    public int countNumBySql(String sql, List<Object> preParameters) {
        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        SqlPreUtil.setValForQuery(sqlQuery, preParameters);
        return ((BigInteger)sqlQuery.uniqueResult()).intValue();
    }

    public List<String> getStringListByHql(String hql) {
        return this.getStringListByHql(hql, (Integer)null, (Integer)null);
    }

    public List<String> getStringListByHql(String hql, Integer start, Integer size) {
        Query query = this.getCurrentSession().createQuery(hql);
        if (start != null) {
            query.setFirstResult(start.intValue()).setMaxResults(size.intValue());
        }

        return query.list();
    }

    public List<String> getStringListBySql(String sql) {
        return this.getStringListBySql(sql, (Integer)null, (Integer)null);
    }

    public List<String> getStringListBySql(String sql, Integer start, Integer size) {
        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        if (start != null) {
            sqlQuery.setFirstResult(start.intValue()).setMaxResults(size.intValue());
        }

        return sqlQuery.list();
    }

    public List<Object[]> getObjectArrayListByHql(String hql) {
        return this.getObjectArrayListByHql(hql, (Integer)null, (Integer)null, (List)null);
    }

    public List<Object[]> getObjectArrayListByHql(String hql, Integer start, Integer size, List<Object> preParameters) {
        Query query = this.getCurrentSession().createQuery(hql);
        SqlPreUtil.setValForQuery(query, preParameters);
        if (start != null) {
            query.setFirstResult(start.intValue()).setMaxResults(size.intValue());
        }

        return query.list();
    }

    public <T> List<T> getPageBeanBySql(String query, String condition, String sort, Class<?> classObj, PageBean pagebean) {
        StringBuilder sql = new StringBuilder();
        sql.append(query).append(" ").append(condition).append(" ");
        if (sort != null) {
            sql.append(sort);
        } else if (pagebean != null) {
            sql.append("ORDER BY ").append(pagebean.getSortfeild());
            switch(pagebean.getSorttype()) {
                case 0:
                    sql.append(" DESC");
                    break;
                case 1:
                    sql.append(" ASC");
            }
        } else {
            sql.append("ORDER BY id DESC");
        }

        Integer start = null;
        Integer size = null;
        if (pagebean != null) {
            this.updatePageBean(condition, pagebean);
            start = (pagebean.getCurpage() - 1) * pagebean.getPagesize();
            size = pagebean.getPagesize();
        }

        List<T> list = this.getObjectsBySql(sql.toString(), classObj, start, size, (List)null);
        return list;
    }

    public <T> List<T> getPageBeanBySql(String query, String condition, String sort, Class<?> classObj, PageBean pagebean, List<Object> params) {
        StringBuilder sql = new StringBuilder();
        sql.append(query).append(" ").append(condition).append(" ");
        if (sort != null) {
            sql.append(sort);
        } else if (pagebean != null) {
            sql.append("ORDER BY ").append(pagebean.getSortfeild());
            switch(pagebean.getSorttype()) {
                case 0:
                    sql.append(" DESC");
                    break;
                case 1:
                    sql.append(" ASC");
            }
        } else {
            sql.append("ORDER BY id DESC");
        }

        Integer start = null;
        Integer size = null;
        if (pagebean != null) {
            this.updatePageBean(condition, pagebean, params);
            start = (pagebean.getCurpage() - 1) * pagebean.getPagesize();
            size = pagebean.getPagesize();
        }

        List<T> list = this.getObjectsBySql(sql.toString(), classObj, start, size, params);
        return list;
    }

    public List<Map<String, Object>> getPageBeanBySql(String query, String condition, String sort, PageBean pagebean) {
        StringBuilder sql = new StringBuilder();
        sql.append(query).append(" ").append(condition).append(" ");
        if (sort != null) {
            sql.append(sort);
        } else if (pagebean != null) {
            sql.append("ORDER BY ").append(pagebean.getSortfeild());
            switch(pagebean.getSorttype()) {
                case 0:
                    sql.append(" DESC");
                    break;
                case 1:
                    sql.append(" ASC");
            }
        } else {
            sql.append("ORDER BY id DESC");
        }

        Integer start = null;
        Integer size = null;
        if (pagebean != null) {
            this.updatePageBean(condition, pagebean);
            start = (pagebean.getCurpage() - 1) * pagebean.getPagesize();
            size = pagebean.getPagesize();
        }

        List<Map<String, Object>> list = this.getMapOfObjectsBySql(sql.toString(), start, size, (List)null);
        return list;
    }

    public List<Map<String, Object>> getPageBeanBySql(String query, String condition, String sort, PageBean pagebean, List<Object> params) {
        StringBuilder sql = new StringBuilder();
        sql.append(query).append(" ").append(condition).append(" ");
        if (sort != null) {
            sql.append(sort);
        } else if (pagebean != null) {
            sql.append("ORDER BY ").append(pagebean.getSortfeild());
            switch(pagebean.getSorttype()) {
                case 0:
                    sql.append(" DESC");
                    break;
                case 1:
                    sql.append(" ASC");
            }
        } else {
            sql.append("ORDER BY id DESC");
        }

        Integer start = null;
        Integer size = null;
        if (pagebean != null) {
            this.updatePageBean(condition, pagebean, params);
            start = (pagebean.getCurpage() - 1) * pagebean.getPagesize();
            size = pagebean.getPagesize();
        }

        List<Map<String, Object>> list = this.getMapOfObjectsBySql(sql.toString(), start, size, params);
        return list;
    }

    protected void updatePageBean(String condition, PageBean pagebean) {
        if (pagebean.getIsnewcomp() == 1) {
            StringBuilder count = new StringBuilder();
            count.append("select count(*)").append(" ").append(condition);
            int totalRows = this.countNumBySql(count.toString(), (List)null);
            pagebean.setTotalNum(totalRows);
        }

        pagebean.updatePage();
    }

    protected void updatePageBean(String condition, PageBean pagebean, List<Object> params) {
        if (pagebean.getIsnewcomp() == 1) {
            StringBuilder count = new StringBuilder();
            count.append("select count(*)").append(" ").append(condition);
            int totalRows = this.countNumBySql(count.toString(), params);
            pagebean.setTotalNum(totalRows);
        }

        pagebean.updatePage();
    }
}