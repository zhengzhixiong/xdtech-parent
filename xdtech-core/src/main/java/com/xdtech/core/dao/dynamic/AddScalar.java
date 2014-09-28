package com.xdtech.core.dao.dynamic;

import java.lang.reflect.Field;  
import java.util.Date;  
import java.util.List;  
import java.util.Map;
 
import org.hibernate.Hibernate;  
import org.hibernate.SQLQuery;  

import com.xdtech.common.utils.NameUtils;
 
/** 
* @author sourcefour 
*/  
public class AddScalar {  
   /** 
    * 将field type 和 Hibernate的类型进行了对应。这里其实不是多余的，如果不进行一定的对应可能会有问题。 
    * 问题有两个： 
    *  1. 在oracle中我们可能把一些字段设为NUMBER(%)，而在Bean中的字段定的是long。那么查询时可能会报： 
    *     java.math.BeigDecimal不能转换成long等错误 
    *  2. 如果不这样写的话，可能Bean中的field就得是大写的，如：name就得写成NAME,userCount就得写成USERCOUNT 
    *     这样是不是很扯(V_V) 
    *  
    * @param <T> 
    * @param sqlQuery 
    *            SQLQuery 
    * @param clazz 
    *            T.class 
    * @param fieldList 
    *            要查询的成员变量名称 
    */  
   public static <T> void addSclar(SQLQuery sqlQuery, Class<T> clazz, Map<String,String> fieldList) {  
       if (clazz == null) {  
           throw new NullPointerException("[clazz] could not be null!");  
       }  
 
//       if ((fieldList != null) && (fieldList.size() > 0)) {  
 
           Field[] fields = clazz.getDeclaredFields();  
 
//           for (String fieldName : fieldList) {  
               for (Field field : fields) {
                   if (fieldList.containsKey(field.getName())||fieldList.isEmpty()) { 
                	   //如果item配置了查询对应的字段就过滤或者没配置查询字段就表示全部查询
                	   //对应sql里的列名,默认的列名师对应属性的驼峰变换下划线_,此功能暂未实现，无法使用，
                	   String columnName = NameUtils.toUnderlineName(field.getName());
                	   
                	   if (fieldList!=null&&fieldList.containsKey(field.getName())) {
                		   //假如用户配置自定义列名，那么以用户配置的为主  modified by max 20140811
                		   columnName = fieldList.get(field.getName());
                	   }else {
                		   columnName = field.getName();
                	   }
                	   
                       if ((field.getType() == long.class) || (field.getType() == Long.class)) {  
                           sqlQuery.addScalar(columnName, Hibernate.LONG);  
                       } else if ((field.getType() == int.class) || (field.getType() == Integer.class)) {  
                           sqlQuery.addScalar(columnName, Hibernate.INTEGER);  
                       } else if ((field.getType() == char.class) || (field.getType() == Character.class)) {  
                           sqlQuery.addScalar(columnName, Hibernate.CHARACTER);  
                       } else if ((field.getType() == short.class) || (field.getType() == Short.class)) {  
                           sqlQuery.addScalar(columnName, Hibernate.SHORT);  
                       } else if ((field.getType() == double.class) || (field.getType() == Double.class)) {  
                           sqlQuery.addScalar(columnName, Hibernate.DOUBLE);  
                       } else if ((field.getType() == float.class) || (field.getType() == Float.class)) {  
                           sqlQuery.addScalar(columnName, Hibernate.FLOAT);  
                       } else if ((field.getType() == boolean.class) || (field.getType() == Boolean.class)) {  
                           sqlQuery.addScalar(columnName, Hibernate.BOOLEAN);  
                       } else if (field.getType() == String.class) {  
                           sqlQuery.addScalar(columnName, Hibernate.STRING);  
                       } else if (field.getType() == Date.class) {  
                           sqlQuery.addScalar(columnName, Hibernate.TIMESTAMP);  
                       }  
                   }  
               }  
//           }  
//       }  
   }  
}  
