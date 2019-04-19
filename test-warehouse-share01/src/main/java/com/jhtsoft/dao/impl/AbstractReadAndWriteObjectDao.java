package com.jhtsoft.dao.impl;

import com.jhtsoft.dao.ReadAndWriteObjectDao;
import com.jhtsoft.util.SqlPreUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName: AbstractReadAndWriteObjectDao
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
public abstract class AbstractReadAndWriteObjectDao extends AbstractReadObjectDao implements ReadAndWriteObjectDao {
    public AbstractReadAndWriteObjectDao() {
    }

    public void updateObject(Object object) {
        this.getCurrentSession().update(object);
    }

    public int updateByHql(String hql, List<Object> preParameters) {
        Query query = this.getCurrentSession().createQuery(hql);
        SqlPreUtil.setValForQuery(query, preParameters);
        return query.executeUpdate();
    }

    public int updateBySql(String sql, List<Object> preParameters) {
        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        SqlPreUtil.setValForSqlQuery(sqlQuery, preParameters);
        return sqlQuery.executeUpdate();
    }

    public void addObject(Object object) {
        this.getCurrentSession().save(object);
    }

    public void delObject(Object object) {
        this.getCurrentSession().delete(object);
    }

    public void delObjectById(Class<?> objclass, Integer id) {
        Object object = this.getCurrentSession().get(objclass, id);
        this.getCurrentSession().delete(object);
    }

    public void updateObjectByMap(Object Object, Map<String, Object> updateMap) throws Exception {
        Class<?> classObject = Object.getClass();
        Set<Map.Entry<String, Object>> paramEntrys = updateMap.entrySet();
        Field field = null;
        String fieldType = null;
        String setMetName = null;
        Method fieldSetMet = null;
        String fieldName = null;
        Iterator var10 = paramEntrys.iterator();

        while(true) {
            while(true) {
                while(true) {
                    while(true) {
                        while(true) {
                            while(true) {
                                while(true) {
                                    while(true) {
                                        Map.Entry entry;
                                        do {
                                            if (!var10.hasNext()) {
                                                return;
                                            }

                                            entry = (Map.Entry)var10.next();
                                            fieldName = (String)entry.getKey();
                                        } while("id".equals(fieldName));

                                        field = classObject.getDeclaredField(fieldName);
                                        fieldType = field.getType().getSimpleName();
                                        setMetName = "set" + StringUtils.capitalize(fieldName);
                                        fieldSetMet = classObject.getMethod(setMetName, field.getType());
                                        if (!"int".equals(fieldType) && !"Integer".equals(fieldType)) {
                                            if (!"short".equals(fieldType) && !"Short".equals(fieldType)) {
                                                if (!"byte".equals(fieldType) && !"Byte".equals(fieldType)) {
                                                    if (!"double".equals(fieldType) && !"Double".equals(fieldType)) {
                                                        if (!"float".equals(fieldType) && !"Float".equals(fieldType)) {
                                                            if (!"long".equals(fieldType) && !"Long".equals(fieldType)) {
                                                                if (!"boolean".equals(fieldType) && !"Boolean".equals(fieldType)) {
                                                                    if ("Timestamp".equals(fieldType)) {
                                                                        if (entry.getValue() instanceof Timestamp) {
                                                                            fieldSetMet.invoke(Object, (Timestamp)entry.getValue());
                                                                        } else {
                                                                            fieldSetMet.invoke(Object, new Timestamp(((Long)entry.getValue()).longValue()));
                                                                        }
                                                                    } else if ("Date".equals(fieldType)) {
                                                                        if (entry.getValue() instanceof Date) {
                                                                            fieldSetMet.invoke(Object, (Date)entry.getValue());
                                                                        } else {
                                                                            fieldSetMet.invoke(Object, new Date(((Long)entry.getValue()).longValue()));
                                                                        }
                                                                    } else if ("String".equals(fieldType)) {
                                                                        fieldSetMet.invoke(Object, (String)entry.getValue());
                                                                    } else if ("BigDecimal".equals(fieldType)) {
                                                                        fieldSetMet.invoke(Object, new BigDecimal(entry.getValue().toString()));
                                                                    } else {
                                                                        System.out.println("请补充数据类型");
                                                                    }
                                                                } else {
                                                                    fieldSetMet.invoke(Object, (Boolean)entry.getValue());
                                                                }
                                                            } else {
                                                                fieldSetMet.invoke(Object, (Long)entry.getValue());
                                                            }
                                                        } else {
                                                            fieldSetMet.invoke(Object, (Float)entry.getValue());
                                                        }
                                                    } else {
                                                        fieldSetMet.invoke(Object, (Double)entry.getValue());
                                                    }
                                                } else {
                                                    fieldSetMet.invoke(Object, (Byte)entry.getValue());
                                                }
                                            } else if (entry.getValue() instanceof Integer) {
                                                fieldSetMet.invoke(Object, ((Integer)entry.getValue()).shortValue());
                                            } else {
                                                fieldSetMet.invoke(Object, (Short)entry.getValue());
                                            }
                                        } else {
                                            fieldSetMet.invoke(Object, (Integer)entry.getValue());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

