package com.jhtsoft.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.*;

/**
 * @author  hyw
 * @Describe 格式转换工具类
 * @Date 2019/3/29
 */
public final class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {
        throw new UnsupportedOperationException();
    }


    public static String toJson(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }

    /**
     * json字符串转JSONObject
     * @param str
     * @return
     */
    public static JSONObject stringToJSONObject(String str) {
        return JSONObject.fromObject(str);
    }

    /**
     * json字符串转map
     * @param str
     * @return
     */
    public static Map<String, Object> stringToMap(String str) {
        JSONObject  jasonObject = JSONObject.fromObject(str);
        Map<String, Object> map = (Map)jasonObject;
        return map;
    }

    /**
     * map转字符串
     * @param map
     * @return
     */
    public static String mapToString(Map map){
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }

    /**
     * json字符串转JSONObject
     * @param str
     * @return
     */
    public static JSONObject stringToJsonObject(String str){
        return JSONObject.fromObject(str);
    }

    /**
     * json字符串转实体类,结果需要做强制类型转换
     * @param str
     * @param clazz
     * @return
     */
    public static Object stringToEntity(String str, Class clazz){
        return JSON.parseObject(str, clazz);
    }

    /**
     * map转实体类,结果需要做强制类型转换
     * @param map
     * @param clazz
     * @return
     */
    public static Object mapToEntity(Map<Object, Object> map, Class clazz){
        String json = JSON.toJSONString(map);
        return JSON.parseObject(json, clazz);
    }

    /**
     *  Map中参数按照hash顺序
     *  转换成sql的where条件语句（部分）
     * @param parameters Map集合
     * @param dateColumn 时间字段名称。
     *                   dateColumn>beginDate,dateColumn<endDate
     * @return
     */
    public static String mapToSqlwhere(Map parameters, String dateColumn){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)){
                if (null !=dateColumn && !"".equals(dateColumn)){
                    if ("beginDate".equals(k.toString())){
                        sb.append(" AND " + dateColumn + ">'" + v + "'");
                        continue;
                    }
                    if ("endDate".equals(k.toString())){
                        sb.append(" AND " + dateColumn + "<'" + v + "'");
                        continue;
                    }
                }

                sb.append(" AND " + k + "='" + v + "'");
            }
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        Map map = new HashMap();
//        map.put("columnA",15);
//        map.put("columnB","stringB");
//        map.put("columnC","stringC");
//        map.put("beginDate",UtilDate.formatDateTime(new Date()));
//        String str = mapToSqlwhere(map, "auditDate");
//        System.out.println("str:" + str);
//    }
    /**
     *  Map中value值按照hash顺序
     *  转换成List集合
     * @param parameters
     * @return
     */
    public static List mapvalueToList(Map parameters){
        List paramsList = new ArrayList();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)) {
                paramsList.add(v);
            }
        }
        return paramsList;
    }


}
