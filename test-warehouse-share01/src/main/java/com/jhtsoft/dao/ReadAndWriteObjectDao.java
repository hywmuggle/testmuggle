package com.jhtsoft.dao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ReadAndWriteObjectDao
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
public interface ReadAndWriteObjectDao extends ReadObjectDao {
    void updateObject(Object var1);

    int updateByHql(String var1, List<Object> var2);

    int updateBySql(String var1, List<Object> var2);

    void addObject(Object var1);

    void delObject(Object var1);

    void delObjectById(Class<?> var1, Integer var2);

    void updateObjectByMap(Object var1, Map<String, Object> var2) throws Exception;
}
