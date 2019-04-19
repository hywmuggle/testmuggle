package com.jhtsoft.dao;
import java.util.List;

import com.jhtsoft.bean.PageBean;
import org.hibernate.Session;
import java.sql.Connection;
import java.util.Map;

/**
 * @ClassName: ReadObjectDao
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
public interface ReadObjectDao {
    <T> List<T> getObjectsByHql(String var1);

    <T> List<T> getObjectsByHql(String var1, Integer var2, Integer var3, List<Object> var4);

    <T> List<T> getObjectsBySql(String var1, Class<?> var2);

    <T> List<T> getObjectsBySql(String var1, Class<?> var2, Integer var3, Integer var4, List<Object> var5);

    List<Map<String, Object>> getMapOfObjectsBySql(String var1);

    List<Map<String, Object>> getMapOfObjectsBySql(String var1, List<Object> var2);

    Map<String, Object> getMapOfObjectBySql(String var1, List<Object> var2);

    Map<String, Object> getMapOfObjectBySql(String var1);

    List<Map<String, Object>> getMapOfObjectsBySql(String var1, Integer var2, Integer var3, List<Object> var4);

    Object getObjectByHql(String var1);

    <T> T getObjectBySql(String var1, Class<T> var2);

    <T> T getObjectById(Class<T> var1, Integer var2);

    <T> T getObjectBySql(String var1, Class<T> var2, List<Object> var3);

    int countNumByHql(String var1, List<Object> var2);

    int countNumBySql(String var1, List<Object> var2);

    List<String> getStringListByHql(String var1);

    List<String> getStringListByHql(String var1, Integer var2, Integer var3);

    List<String> getStringListBySql(String var1);

    List<String> getStringListBySql(String var1, Integer var2, Integer var3);

    List<Object[]> getObjectArrayListByHql(String var1);

    List<Object[]> getObjectArrayListByHql(String var1, Integer var2, Integer var3, List<Object> var4);

    Connection getConn();

    <T> List<T> getPageBeanBySql(String var1, String var2, String var3, Class<?> var4, PageBean var5);

    <T> List<T> getPageBeanBySql(String var1, String var2, String var3, Class<?> var4, PageBean var5, List<Object> var6);

    List<Map<String, Object>> getPageBeanBySql(String var1, String var2, String var3, PageBean var4);

    List<Map<String, Object>> getPageBeanBySql(String var1, String var2, String var3, PageBean var4, List<Object> var5);

    Session getCurrentSession();
}