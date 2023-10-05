package com.zyy;
import com.zyy.handleGoods.impl.HandleGoodsImpl;

/**
 * 统计当前库存的商品
 *
 * --当前物品总数：10，
 * --物品总金额23，
 * --物品平均价格2.3
 */
public class ChartGoods implements Runnable {
    int count = 0;
    @Override
    public void run() {
        while(true){
            System.out.println("==================================库存统计"+count+"=================================");
            HandleGoodsImpl.chartGoods();
            System.out.println();
            count++;
            try {
                Thread.sleep(  10000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
