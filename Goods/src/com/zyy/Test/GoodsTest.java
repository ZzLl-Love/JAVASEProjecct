package com.zyy.Test;

import com.zyy.handleGoods.HandleGoods;
import com.zyy.handleGoods.impl.HandleGoodsImpl;
import com.zyy.pojo.Goods;

import java.util.Set;

/**
 * 测试商品功能
 */
public class GoodsTest {

    public static void main(String[] args) {

        //拿到商品列表
        Set<Goods> goodList = HandleGoodsImpl.goodsList;
        //拿到商品处理类的对象
        HandleGoods handleGoods = new HandleGoodsImpl();

        /***
         * 测试商品添加功能 ok  同类商品
         */
        System.out.println("==========================商品添加============================");
        handleGoods.addGoods(1,"纸巾",3.5, 2000);
        handleGoods.addGoods(2,"雪碧",3.5, 5000);
        handleGoods.addGoods(3,"可乐",3.5, 200);
        handleGoods.addGoods(4,"手机",6600, 2000);
        handleGoods.showGoods();
        System.out.println("==========================商品添加============================");
        System.out.println();

        /**
         *商品删除
         */
        System.out.println("==========================商品删除============================");
        handleGoods.delGoods(4);
        handleGoods.showGoods();
        System.out.println("==========================商品删除============================");
        System.out.println();

        System.out.println("===========================修改商品价格========================");
        handleGoods.updateGoodsPrice(5, 2500);
        handleGoods.showGoods();
        System.out.println("===========================修改商品价格=========================");
        System.out.println();

        System.out.println("===========================商品入库========================");
        handleGoods.inGoods(3, 600);
        handleGoods.showGoods();
        System.out.println("===========================商品入库========================");
        System.out.println();

        System.out.println("===========================商品全部出库========================");
        handleGoods.outGoods(3,6000);
        handleGoods.showGoods();
        System.out.println("===========================商品全部出库========================");
        System.out.println();

        System.out.println("===========================商品部分出库========================");
        handleGoods.outGoods(2,1000);
        handleGoods.showGoods();
        System.out.println("===========================商品部分出库========================");


    }
}
