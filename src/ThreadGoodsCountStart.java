import com.zyy.ChartGoods;

public class ThreadGoodsCountStart {
    public static void main(String[] args) {
        //创建一个线程对象,来统计商品列表的
        ChartGoods chartGoods = new ChartGoods();
        Thread thread = new Thread(chartGoods);
        thread.start();

    }
}
