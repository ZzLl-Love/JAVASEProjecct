package com.zyy.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

 public class loadProperties {

     /**
      * 存储用户名和密码的容器
      */
     private static Properties properties = new Properties();

     /**
      * 配置文件名
      */
     private final static String fileName = "../../../db.properties";


     /**
      * 调用静态方法----》类的加载--执行--》static代码块
      *
      * ==>得出 ==
      * 将获取用户名和密码设置为一个静态方法
      */
    static{
        try {
            InputStream inputStream = loadProperties.class.getResourceAsStream(fileName);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(properties.isEmpty()){
            throw new RuntimeException(fileName + "中的数据为空....");
        }
    }


     /**
      * 返回配置文件fileName中的用户名userName
      * @return
      */
    public static String getUserName(){
        return (String)properties.get("userName");
    }

    public static String getPassword(){
        return (String) properties.get("passWord");
    }


}
