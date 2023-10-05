package com.zyy;

import com.zyy.login.impl.LoginImpl;
import com.zyy.util.loadProperties;

/**
 * 用户测试类，是否能拿到配置文件中的信息，看登陆是否正确
 */
public class LoginTest {

    public static void main(String[] args) {

        /**
         * 测试是否能够拿到配置文件中的用户名和密码
         *
         */
        String userName = loadProperties.getUserName();
        System.out.println("用户名: " + userName);

        String password = loadProperties.getPassword();
        System.out.println("密码：" + password);


        /**
         * 模拟登陆成功
         */
        LoginImpl login = new LoginImpl();
        boolean b = login.handleLogin(userName, password);
        System.out.println(b==true? "登陆成功" : "登陆失败");

        /**
         * 模拟登陆失败
         */
        boolean login1 = login.handleLogin(userName + "1", password);
        System.out.println(login1==true? "登陆成功" : "登陆失败");

    }
}
