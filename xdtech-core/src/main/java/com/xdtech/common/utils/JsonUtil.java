package com.xdtech.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class JsonUtil {
	 /** 
	  * 从一个JSON数组得到一个java对象集合 
	  * @param object 
	  * @param clazz 
	  * @return 
	  */  
	 @SuppressWarnings("rawtypes")
	public static List getDTOList(String jsonString, Class clazz){  
	  setDataFormat2JAVA();  
	  JSONArray array = JSONArray.fromObject(jsonString);  
	  List list = new ArrayList();  
	  for(Iterator iter = array.iterator(); iter.hasNext();){  
	   JSONObject jsonObject = (JSONObject)iter.next();  
	   list.add(JSONObject.toBean(jsonObject, clazz));  
	  }  
	  return list;  
	 }  
	 /**   
     * 从一个JSON数组得到一个java对象集合，其中对象中包含有集合属性   
     * @param object   
     * @param clazz   
     * @param map 集合属性的类型   
     * @return   
     */    
    public static List getDTOList(String jsonString, Class clazz, Map map){     
        setDataFormat2JAVA();     
        JSONArray array = JSONArray.fromObject(jsonString);     
        List list = new ArrayList();     
        for(Iterator iter = array.iterator(); iter.hasNext();){     
            JSONObject jsonObject = (JSONObject)iter.next();     
            list.add(JSONObject.toBean(jsonObject, clazz, map));     
        }     
        return list;     
    }   
    /** 
     * 从json串转换成实体对象 
     * @param jsonObjStr e.g. {'name':'get','dateAttr':'2009-11-12'} 
     * @param clazz Person.class 
     * @return 
     */  
     public static Object getDtoFromJsonObjStr(String jsonObjStr, Class clazz) {  
         return JSONObject.toBean(JSONObject.fromObject(jsonObjStr), clazz);  
     }  
    private static void setDataFormat2JAVA(){  
	  //设定日期转换格式  
	  JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));  
	 } 
    /** 
     * 把一个json数组串转换成集合，且集合里的对象的属性含有另外实例Bean 
     * @param jsonArrStr e.g. [{'data':[{'name':'get'}]},{'data':[{'name':'set'}]}] 
     * @param clazz e.g. MyBean.class 
     * @param classMap e.g. classMap.put("data", Person.class) 
     * @return List 
     */  
     public static List getListFromJsonArrStr(String jsonArrStr, Class clazz, Map classMap) {  
         JSONArray jsonArr = JSONArray.fromObject(jsonArrStr);  
         List list = new ArrayList();  
         for (int i = 0; i < jsonArr.size(); i++) {  
             list.add(JSONObject.toBean(jsonArr.getJSONObject(i), clazz, classMap));  
         }  
         return list;  
     }  
    /** 
     * 把json对象串转换成map对象 
     * @param jsonObjStr e.g. {'name':'get','int':1,'double',1.1,'null':null} 
     * @return Map 
     */  
     public static Map getMapFromJsonObjStr(String jsonObjStr) {  
         JSONObject jsonObject = JSONObject.fromObject(jsonObjStr);  
   
         Map map = new HashMap();  
         for (Iterator iter = jsonObject.keys(); iter.hasNext();) {  
             String key = (String) iter.next();  
             map.put(key, jsonObject.get(key));  
         }  
         return map;  
     }  
   
     /** 
      * 把json对象串转换成map对象，且map对象里存放List
      * @param jsonObjStr e.g. {"inserted":[],"deleted":[],"updated":[{"type":1,"description":"新增","iconName":"icon-add","iconUrl":null,"labelName":"新增33","operationCode":"role_add"}]} 
      * @param clazz e.g. Person.class 
      * @return Map 
      */  
      public static Map getMapWithListFromJson(String jsonObjStr, Class clazz) {  
          JSONObject jsonObject = JSONObject.fromObject(jsonObjStr);  
    
          Map map = new HashMap();  
          for (Iterator iter = jsonObject.keys(); iter.hasNext();) {  
              String key = (String) iter.next(); 
//              System.out.println(jsonObject.get(key));
              map.put(key, getDTOList(jsonObject.get(key).toString(), clazz));  
          }  
          return map;  
      }  
     
     /** 
     * 把json对象串转换成map对象，且map对象里存放的为其他实体Bean 
     * @param jsonObjStr e.g. {'data1':{'name':'get'},'data2':{'name':'set'}} 
     * @param clazz e.g. Person.class 
     * @return Map 
     */  
     public static Map getMapFromJsonObjStr(String jsonObjStr, Class clazz) {  
         JSONObject jsonObject = JSONObject.fromObject(jsonObjStr);  
   
         Map map = new HashMap();  
         for (Iterator iter = jsonObject.keys(); iter.hasNext();) {  
             String key = (String) iter.next();  
             map.put(key, JSONObject.toBean(jsonObject.getJSONObject(key), clazz));  
         }  
         return map;  
     }  
   
     /** 
      * 把json对象串转换成map对象，且map对象里存放的其他实体Bean还含有另外实体Bean 
      * @param jsonObjStr e.g. {'mybean':{'data':[{'name':'get'}]}} 
      * @param clazz e.g. MyBean.class 
      * @param classMap  e.g. classMap.put("data", Person.class) 
      * @return Map 
      */  
     public static Map getMapFromJsonObjStr(String jsonObjStr, Class clazz, Map classMap) {  
         JSONObject jsonObject = JSONObject.fromObject(jsonObjStr);  
   
         Map map = new HashMap();  
         for (Iterator iter = jsonObject.keys(); iter.hasNext();) {  
             String key = (String) iter.next();  
             map.put(key, JSONObject  
                     .toBean(jsonObject.getJSONObject(key), clazz, classMap));  
         }  
         return map;  
     }  
   
     /** 
      * 把实体Bean、Map对象、数组、列表集合转换成Json串 
      * @param obj  
      * @return 
      * @throws Exception String 
      */  
//     public static String getJsonStr(Object obj) {  
//         String jsonStr = null;  
//         //Json配置      
//         JsonConfig jsonCfg = new JsonConfig();  
//   
//         //注册日期处理器  
//         jsonCfg.registerJsonValueProcessor(java.util.Date.class,  
//                 new JsonDateValueProcessor(Util.YYYY_MM_DD_HH_MM_ss));  
//         if (obj == null) {  
//             return "{}";  
//         }  
//   
//         if (obj instanceof Collection || obj instanceof Object[]) {  
//             jsonStr = JSONArray.fromObject(obj, jsonCfg).toString();  
//         } else {  
//             jsonStr = JSONObject.fromObject(obj, jsonCfg).toString();  
//         }  
//   
//         return jsonStr;  
//     }  
}
