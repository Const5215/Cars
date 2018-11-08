package edu.ncsu.csc.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultSetUtils {

    /**
     * @param resultSet
     * @param object Class
     * @return ResultSet to object
     * @throws Exception
     */
    public static Object convertToObject(ResultSet resultSet, Class<?> object) throws Exception {
        if (resultSet.next()) {
            try {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                Object newInstance = object.newInstance();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i).toLowerCase();
                    String fieldName = convertToFieldName(columnName);
                    Class<?> type = object.getDeclaredField(fieldName).getType();
                    Method method = object.getMethod(
                            "set" + changeFirstCharacterCase(fieldName, true), type);
                    if (type.isAssignableFrom(String.class)) {
                        method.invoke(newInstance, resultSet.getString(i));
                    } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
                        method.invoke(newInstance, resultSet.getInt(i));
                    } else if (type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class)) {
                        method.invoke(newInstance, resultSet.getBoolean(i));
                    } else if (type.isAssignableFrom(Date.class)) {
                        method.invoke(newInstance, resultSet.getDate(i));
                    } else if (type.isAssignableFrom(Double.class) || type.isAssignableFrom(double.class)) {
                        method.invoke(newInstance, resultSet.getDouble(i));
                    } else if (type.isAssignableFrom(Long.class) || type.isAssignableFrom(long.class)) {
                        method.invoke(newInstance, resultSet.getLong(i));
                    } else if (type.isAssignableFrom(Timestamp.class)) {
                        method.invoke(newInstance, resultSet.getTimestamp(i));
                    }
                }
                return newInstance;
            } catch (InstantiationException | InvocationTargetException | SQLException |
                    NoSuchMethodException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                throw new Exception(e);
            }
        } else {
            return null;
        }
    }

    /**
     * @param resultSet
     * @param object Class
     * @return resultSet to List
     * @throws Exception
     */
    public static List convertToList(ResultSet resultSet, Class<?> object) throws Exception {

        List<Object> list = new ArrayList<>();
        Object ret;
        while ((ret = convertToObject(resultSet, object)) != null) {
            list.add(ret);
        }
        return list.isEmpty()? null : list;
    }

    /**
     * 将数据库列名映射为java驼峰式命名
     * @param columnName 待转换字符串 (xxxx_yyyy_zzzz 数据库列名形式)
     * @return 转换成POJO的字段名 (java驼峰式命名xxxxYyyyZzzz)
     */
    public static String convertToFieldName(String columnName) {
        String[] split = columnName.split("_");
        StringBuilder stringBuilder = new StringBuilder(split[0]);

        if (split.length > 1) {
            for (int i = 1; i < split.length; i++) {
                stringBuilder.append(changeFirstCharacterCase(split[i], true));
            }
        }

        return stringBuilder.toString();
    }

    /**
     * copy from package org.springframework.util.StringUtils
     * @param str 待转换首字母字符串
     * @param capitalize true:首字母转换为大写, false:首字母转换为小写
     * @return 转换首字母后字符串
     */
    public static String changeFirstCharacterCase(String str, boolean capitalize) {
        if(str.isEmpty()) {
            return str;
        } else {
            char baseChar = str.charAt(0);
            char updatedChar;
            if(capitalize) {
                updatedChar = Character.toUpperCase(baseChar);
            } else {
                updatedChar = Character.toLowerCase(baseChar);
            }

            if(baseChar == updatedChar) {
                return str;
            } else {
                char[] chars = str.toCharArray();
                chars[0] = updatedChar;
                return new String(chars, 0, chars.length);
            }
        }
    }

}
