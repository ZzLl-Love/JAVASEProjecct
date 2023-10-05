package com.zyy.login.impl;


import com.zyy.login.Login;
import com.zyy.util.loadProperties;

public class LoginImpl implements Login {

    /**
     * 判断登陆是否合法
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean handleLogin(String username, String password) {


        /**
         * 判断用户名是否正确
         */
        if(! username.equals(loadProperties.getUserName())){
            //throw new RuntimeException(username+ "和配置文件的用户名不相同");
            return false;
        }

        /**
         * 判断密码是否正确
         */
        if(! password.equals(loadProperties.getPassword())){

           //throw new RuntimeException(password + "和配置文件密码不相同");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        LoginImpl login = new LoginImpl();
        boolean b = login.handleLogin("root11", "root");
        System.out.println(b);
    }

}
