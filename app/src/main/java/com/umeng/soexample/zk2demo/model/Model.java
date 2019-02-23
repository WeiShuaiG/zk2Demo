package com.umeng.soexample.zk2demo.model;

import com.umeng.soexample.zk2demo.callback.MyCallBack;

import java.util.Map;

/**
 * Created by W on 2019/2/23 11:22.
 */

public interface Model {
    void get(String url, Map<String,String> map, Map<String,String> headmap, Class kind, MyCallBack callBack);

    void put(String url, Map<String,Object> map,Map<String,String> headmap,Class kind, MyCallBack callBack);
}
