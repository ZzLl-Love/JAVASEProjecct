package com.zyy.handleGoods.impl;

import com.zyy.handleGoods.HandleGoods;
import com.zyy.pojo.Goods;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public  class HandleGoodsImpl implements HandleGoods {

    /**
     * 商品列表 ==确保==>商品编号唯一==》重写id属性的hashCode和equals方法
     */
    public static Set<Goods> goodsList;


    private static final String saveFile = "D:\\十指波教育\\zyyCode\\javase基础项目练习\\Goods\\src\\com\\zyy\\saveGoods.txt";

    //关闭系统后自动读取上次存放的数据
    static{
        goodsList =  loadData();
    }

    /**
     *
     * @param gId 商品编码
     * @param gName 名称
     * @param gPrice 价格
     * @param gCount 数量
     */

    public  void addGoods(int gId, String gName, double gPrice, int gCount) {

        //创建商品
        Goods newGoods = new Goods(gId, gName, gPrice, gCount);

        //商品列表为null || 没有找到同类型的商品 则直接添加
        if (goodsList.isEmpty() || !(goodsList.contains(newGoods))) {
            //添加商品
            goodsList.add(newGoods);
            return;
        }

        //说明同类型的商品存在,增加数量
        for (Goods currentGoods : goodsList) {
            //获取当前商品的数量+新增加数量
            int addCount = currentGoods.getCount() + gCount;
            //更新商品的数量
            currentGoods.setCount(addCount);
        }

    }

    /**
     *商品删除
     * @param gId
     */
    @Override
    public void delGoods(int gId) {

         boolean flag = false;
        //没有商品为空直接退出
        if(goodsList.isEmpty() ){
            System.out.println("商品列表为空~");
            return;
        }

        //删除指定的商品id
        Iterator<Goods> iterator = goodsList.iterator();
        while(iterator.hasNext()){
            //拿到当前商品
            Goods currentGoods = iterator.next();
            if(gId == currentGoods.getId()){
                //goodList.remove(currentGoods);
                iterator.remove();
                flag =true;
            }
        }
        System.out.println(flag == true ? "商品删除成功" : "列表中没有对应的商品信息");

    }

    /**
     *
     * @param gId
     */
    @Override
    public void updateGoodsPrice(int gId, double newPrice) {

        Goods goods = new Goods(gId);
        //没有商品 || 没有指定商品 为空直接退出
        if(goodsList.isEmpty() || !(goodsList.contains(goods)) ){
            return;
        }
        //遍历商品列表
        for (Goods currentGoods : goodsList) {
            if(gId == currentGoods.getId()){
                //修改商品价格
                currentGoods.setPrice(newPrice);
            }
        }
    }

    /**
     *展示商品列表
     */
    @Override
    public void showGoods() {

        //判断商品列表是否为空
        if(goodsList.isEmpty()){
            return;
        }

        //遍历商品列表
        for (Goods currentGoods : goodsList) {
            System.out.println("showGoods:" + currentGoods);
        }
    }

    /**
     * 商品入库
     * @param gId 商品编号
     * @param inCount 入库数量
     */
    @Override
    public void inGoods(int gId, int inCount) {

        //入库成功标志，默认入库不成功
        boolean flag = false;

        //判断入库数量是否合法
        if(inCount <= 0 ){
            return;
        }

        //判断商品列表是否为空
        if(goodsList.isEmpty()){
            System.out.println("商品列表为空~");
            return;
        }
        //准备入库
        for (Goods currentGoods : goodsList) {
            if(gId == currentGoods.getId()){
                //获取当前商品的数量
                int currentCount = currentGoods.getCount();
                currentGoods.setCount(currentCount+inCount);
                //入库成功
                flag =true;
            }
        }
        System.out.println(flag == true ? "入库成功~" : gId +"商品编号对应的商品不存在~");
    }

    /**
     * 商品出库
     * @param gId 商品编号
     * @param outCount 出库数量
     */
    @Override
    public void outGoods(int gId, int outCount) {

        //出库成功标志,默认出库失败
        boolean outFlag = false;

        //判断出库数据是否合法
        if(outCount<0){
            System.out.println("出库数据: " + outCount + "不合法");
            return;
        }

        //判断商品列表是否为空
        if(goodsList.isEmpty()){
            System.out.println("商品列表为空~");
            return;
        }

        //准备出库
        Iterator<Goods> iterator = goodsList.iterator();
        while(iterator.hasNext()){
            //获取到当前商品
            Goods currentGoods = iterator.next();
            if(gId == currentGoods.getId()){
                //获取当前商品的数量
                int currentCount = currentGoods.getCount();
                //如果要出库的数量  > 商品本身数量,则默认全部出库
                if(outCount > currentCount){
                    currentGoods.setCount(0);
                    outFlag = true;
                    return;
                }
                //出库数量 < 商品本身数量
                currentGoods.setCount(currentCount-outCount);
                outFlag = true;
                return;
            }
        }

        System.out.println(outFlag == true ? "出库成功~" : gId+ "商品编号对应的商品不存在...");
    }


    /**
     * 打印当前物品总数  物品总金额  物品平均价格
     */
    public synchronized static void chartGoods(){
        //读取持久化的的商品信息
        Set<Goods> readGoods = loadData();
            //商品总数
            int  totalCount = 0;
            //商品总金额
            double totalPrice = 0;
            for (Goods goods : readGoods) {
                totalCount += goods.getCount();
                //计算总金额
                totalPrice += goods.getPrice() * goods.getCount();
            }
            System.out.println(" --[当前物品总数: " + totalCount + "]  " +"["+"物品总金额: "
                    + totalPrice +"]  " + "[物品平均价格: " + (totalPrice/totalCount)+"]  " );
    }

    /**
     * 持久化goodList中的数据到saveFile中
     * @param
     */
    public static void saveGoodData(Set<Goods> goodsSet)  {
        ObjectOutputStream objectOutputStream = null;
        //创建一个对象输出流
        try {
             objectOutputStream = new ObjectOutputStream(new FileOutputStream(saveFile));
             objectOutputStream.writeObject(goodsSet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 将保存的goodList中的数据读取到程序中
     * @return
     */
    public static Set<Goods> loadData() {
        ObjectInput objectInput = null;
        try {
             objectInput = new ObjectInputStream(new FileInputStream(saveFile));
            return (Set<Goods>)objectInput.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                objectInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new HashSet<>();
    }
}


