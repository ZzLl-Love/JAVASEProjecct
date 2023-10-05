
#目录结构
    |--【javase基于项目项目练习】
        |--[商品维护模块Goods]
            |--pojo包
                |--Goos类
            |--handleGoods包
                |--handleGoods接口
                |--impl包
                    |--handleGoodsImpl类
            |--Test包

        |--[用户管理模块User]
        |--[统计报表模块 statistic]
# 启动程序
> 运行目录下的src文件夹里面ShoppingApplication类的main方法运行商品功能  

>执行src文件下的ThreadGoodCount方法即可实现统计库存功能
 #实现思路：
* **统计报表**
    1. 总体设计
        1. 定义一个工具类获取properties中配置的用户名user和password，编写LoginImpl类去实现Login接口中的方法。登陆方法只去判断用户名和密码是否正确，工具类只去获取配置的用户名和密码。
        2. 将获取到配置文件中的用户名和密码放入到static代码块中，好处在于调用static的获取密码和用户名方法时，自动的去将数据加载到Properties中。
    2. 优化点  
       loginImpl中判断用户名和密码的方式返回的是boolean类型，可以定义一个枚举类来详细的定义判断后返回的信息。
    
***

* **商品维护模块**  
  1. 总体设计
      1. 先开始定义一个操作商品方法的接口(handleGoods接口)，是为了方便扩展定义规范，包括返回的数据类，参数类型。用商品操作类(实现类HandleGoodsImpl)去具体实现定义方法。HandleGoodsImpl只涉及到商品的CRUD方法。
      2. 编写ExposeGoods类使用switch来实现不同变量组装不同的商品类操作方法并暴露给用户使用。
      3. 保证商品编号的唯一性，使用HashSet数据接口通过重写id的hashCode方法和equals方法来实现。  
***     
  
* **统计报表**
    1. 总体设计
        1. 将商品模块中的持久化数据功能+线程来完成的，使用线程打印库存信息之前，利用static代码块的执行顺序将数据读取到商品列表HashSet中，然后在另一个类的中启动线程去打印。



   

      
        
    