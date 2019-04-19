package com.jhtsoft.util;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: SqlPreUtil
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
public class SqlPreUtil {
    public SqlPreUtil() {
    }

    public static void setValForQuery(Query query, List<Object> preParameters) {
        if (preParameters != null) {
            int i = 0;

            for(Iterator var3 = preParameters.iterator(); var3.hasNext(); ++i) {
                Object object = var3.next();
                if (object instanceof String) {
                    query.setString(i, (String)object);
                } else if (object instanceof Short) {
                    query.setShort(i, ((Short)object).shortValue());
                } else if (object instanceof Integer) {
                    query.setInteger(i, ((Integer)object).intValue());
                } else if (object instanceof Short) {
                    query.setShort(i, ((Short)object).shortValue());
                } else if (object instanceof Double) {
                    query.setDouble(i, ((Double)object).doubleValue());
                } else if (object instanceof Float) {
                    query.setFloat(i, ((Float)object).floatValue());
                } else if (object instanceof Timestamp) {
                    query.setTimestamp(i, (Timestamp)object);
                } else if (object instanceof Date) {
                    query.setTimestamp(i, (Date)object);
                } else if (object == null) {
                    query.setDate(i, (Date)null);
                } else if (object instanceof BigDecimal) {
                    query.setBigDecimal(i, (BigDecimal)object);
                } else {
                    System.out.println("请补充数据类型");
                }
            }
        }

    }

    public static void setValForSqlQuery(SQLQuery sqlQuery, List<Object> preParameters) {
        if (preParameters != null) {
            int i = 0;

            for(Iterator var3 = preParameters.iterator(); var3.hasNext(); ++i) {
                Object object = var3.next();
                if (object instanceof String) {
                    sqlQuery.setString(i, (String)object);
                } else if (object instanceof Short) {
                    sqlQuery.setShort(i, ((Short)object).shortValue());
                } else if (object instanceof Integer) {
                    sqlQuery.setInteger(i, ((Integer)object).intValue());
                } else if (object instanceof Short) {
                    sqlQuery.setShort(i, ((Short)object).shortValue());
                } else if (object instanceof Double) {
                    sqlQuery.setDouble(i, ((Double)object).doubleValue());
                } else if (object instanceof Float) {
                    sqlQuery.setFloat(i, ((Float)object).floatValue());
                } else if (object instanceof Timestamp) {
                    sqlQuery.setTimestamp(i, (Timestamp)object);
                } else if (object instanceof Date) {
                    sqlQuery.setTimestamp(i, (Date)object);
                } else if (object == null) {
                    sqlQuery.setDate(i, (Date)null);
                } else {
                    System.out.println("请补充数据类型");
                }
            }
        }

    }
}

