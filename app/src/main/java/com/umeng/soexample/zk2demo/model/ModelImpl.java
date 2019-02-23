package com.umeng.soexample.zk2demo.model;

import com.google.gson.Gson;
import com.umeng.soexample.zk2demo.callback.MyCallBack;
import com.umeng.soexample.zk2demo.network.RetrofitUtils;

import java.util.Map;

/**
 * Created by W on 2019/2/23 11:24.
 */

public class ModelImpl implements Model {
    @Override
    public void get(String url, Map<String, String> map, Map<String, String> headmap, final Class kind, final MyCallBack callBack) {
        RetrofitUtils.getInstance().get(url,map,headmap).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object s = gson.fromJson(jsonStr,kind);
                callBack.setSuccess(s);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void put(String url, Map<String, Object> map, Map<String, String> headmap, final Class kind, final MyCallBack callBack) {
        RetrofitUtils.getInstance().put(url,map,headmap).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object s = gson.fromJson(jsonStr,kind);
                callBack.setSuccess(s);
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
