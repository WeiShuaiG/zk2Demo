package com.umeng.soexample.zk2demo.presenter;

import java.util.Map;

/**
 * Created by W on 2019/2/23 11:26.
 */

public interface Presenter {
    void onPutRequest(String url, Map<String,Object> map, Map<String,String> headmap, Class kind);
    void onGetRequest(String url,Map<String,String> map,Map<String,String> headmap,Class kind);
}
