package com.zyy.start;

import com.zyy.ExposeGoods;
import com.zyy.login.impl.LoginImpl;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 组装不同模块的功能，完成javaSE系统练习
 * 【用户管理模块】
 * 【商品维护模块】
 * 【统计报表模块】
 *
 */
public class ShoppingApplication {

    //获取到LoginImpl登陆对象
    LoginImpl login = new LoginImpl();
    //获取到暴露出来的商品处理对象ExposeGoods
    ExposeGoods exposeGoods = new ExposeGoods();

    static Scanner scanner = new Scanner(System.in);

    //用户登陆态，默认为未登录.....
    boolean inSystem  = false;

    public void buildApplication(){
        //默认第一次没有进入到系统
        while(!inSystem){
            System.out.println("==================登陆商品系统=======================");
            //拿到用户输入的用户名和密码
            HashMap<String, String> userScanner = getUserScanner();
            String username = userScanner.get("u");
            String password = userScanner.get("p");
            //判断登陆
            boolean login = this.login.handleLogin(username, password);
            if(login){
                //登陆成功
                System.out.println("================success进入到商品系统success=========");
                //将用户的登陆状态设置为已登录
                inSystem = true;
                //成功后展示给用户使用的商品功能
                exposeGoods.selGoodsMethod();
            }else{
                System.out.println("==================error登陆失败error=================");
                System.out.println();
            }
        }

    }

    /**
     * 获取用户输入的用户名和密码
     */
    public static HashMap<String, String> getUserScanner(){
        //定义存储用户名密码
        HashMap<String, String> hashMap = new HashMap<>();
        System.out.print("请输入你的用户名:");
        String username = scanner.nextLine();
        System.out.print("请输入你的密码：");
        String password = scanner.nextLine();
        hashMap.put("u", username);
        hashMap.put("p",password);
        return hashMap;
    }

}
