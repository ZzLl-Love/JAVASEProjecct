package com.zyy;

import com.zyy.handleGoods.impl.HandleGoodsImpl;
import java.util.Scanner;
public class ExposeGoods {

    //定义操作商品的标志
    private final static  int ADD_FLAG = 0;
    private final static  int DEL_FLAG = 1;
    private final static  int UPDATE_FLAG = 2;
    private final static  int SHOW_FLAG = 3;
    private final static  int IN_FLAG = 4;
    private final static  int OUT_FLAG = 5;
    private final static  int EXIT_FLAG = 9;

    //定义是否继续操作的标志
    private boolean  exitFlag = true;

    //拿到商品处理类方法
    private HandleGoodsImpl handleGoods = new HandleGoodsImpl();

    Scanner scanner = new Scanner(System.in);

    /**
     * 通过不同的变量获取到不同的值
     * @param
     */
    public void selGoodsMethod(){
        // 用户第一次选择功能   展示商品功能 并获取 到用户初次选择的编号 userSelSno
        int userSelSno = showList();
        while(exitFlag){
            switch (userSelSno){
                case ADD_FLAG:
                    System.out.print("--请输入商品编号: ");
                    int id = scanner.nextInt();
                    // 清除输入缓冲区中的换行符
                    scanner.nextLine();
                    System.out.print("--请输入商品名称: " );
                    String name = scanner.nextLine();
                    System.out.print("--请输入商品价格: " );
                    double price = scanner.nextDouble();
                    System.out.print("--请输入商品数量: " );
                    int count = scanner.nextInt();
                    handleGoods.addGoods(id, name, price, count);
                    break;

                case DEL_FLAG:
                    System.out.print("请输入商品编号: ");
                    int id2 = scanner.nextInt();
                    handleGoods.delGoods(id2);
                    break;

                case UPDATE_FLAG:
                    System.out.print("请输入商品编号: ");
                    int id3 = scanner.nextInt();
                    System.out.print("请输入商品的新价格：");
                    double newPrice = scanner.nextDouble();
                    handleGoods.updateGoodsPrice(id3, newPrice);
                    break;

                case SHOW_FLAG:
                    handleGoods.showGoods();
                    break;

                case IN_FLAG:
                    System.out.print("请输入商品编号: " );
                    int id4 = scanner.nextInt();
                    System.out.print("请输入入库数量: " );
                    int inCount = scanner.nextInt();
                    handleGoods.inGoods(id4, inCount);
                    break;

                case OUT_FLAG:
                    System.out.print("请输入商品编号: " );
                    int id5 = scanner.nextInt();
                    System.out.print("请输入出库数量");
                    int outCount = scanner.nextInt();
                    handleGoods.outGoods(id5,outCount);
                    break;

                case EXIT_FLAG:
                    exitFlag = false;
                    //退出之前展示最后的商品情况
                    System.out.println("系统退出成功~");
                    break;
                default:
                    System.out.println("--error输入的编号有误，请重新选择编号error--");

            }

            //将操作后的数据保存到磁盘上
            //System.out.println("++" + handleGoods.goodsList);
            HandleGoodsImpl.saveGoodData(handleGoods.goodsList);
            //System.out.println("从磁盘读取到的数据");
            //Set<Goods> goods = HandleGoodsImpl.loadData();
            //System.out.println(goods);

            //如果是展示方法，则无须重复展示
            if(!(userSelSno == SHOW_FLAG)){
                handleGoods.showGoods();
            }

            //如果是退出系统，则没必要展示下一次的商品信息让用户选择
            if(!(userSelSno == EXIT_FLAG)){
                //提供给非退出用户选择菜单，并获取选择的编号
                int newUserSel = showList();
                userSelSno = newUserSel;
            }

        }

    }


    /**
     * 展示商品功能
     * @param
     */
    public int showList(){
        System.out.println("");
        System.out.println("******************商品系统功能***********************");
        System.out.println("**[0 - 添加商品]**");
        System.out.println("**[1 - 删除商品]**");
        System.out.println("**[2 - 修改商品]**");
        System.out.println("**[3 - 列出商品]**");
        System.out.println("**[4 - 商品入库]**");
        System.out.println("**[5 - 商品出库]**");
        System.out.println("**[9 - 退出系统]**");
        System.out.println("******************商品系统功能***********************");
        System.out.print("用户选择的功能编号是: ");
        int userSel = scanner.nextInt();
        return userSel;
    }

    public static void main(String[] args) {
        ExposeGoods exposeGoods = new ExposeGoods();
        exposeGoods.selGoodsMethod();
    }

}
