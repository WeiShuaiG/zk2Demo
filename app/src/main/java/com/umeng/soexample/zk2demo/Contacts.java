package com.umeng.soexample.zk2demo;

/**
 * Created by W on 2019/2/16 9:23.
 */

public class Contacts {

    public static final String BASE_URL = "http://mobile.bwstudent.com/small/";

    public static final String SHOU_GJZ = "commodity/v1/findCommodityByKeyword";

    public static final String XIANGQING = "commodity/v1/findCommodityDetailsById";

    //查询购物车
    public static final String GWC_QUERY = "order/verify/v1/findShoppingCart";

    //同步购物车
    public static final String GWC_ADD = "order/verify/v1/syncShoppingC" +
            "art";

}
