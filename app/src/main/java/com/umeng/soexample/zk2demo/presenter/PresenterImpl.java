package com.umeng.soexample.zk2demo.presenter;

import android.view.View;

import com.umeng.soexample.zk2demo.callback.MyCallBack;
import com.umeng.soexample.zk2demo.iview.IView;
import com.umeng.soexample.zk2demo.model.ModelImpl;

import java.util.Map;

/**
 * Created by W on 2019/2/23 11:28.
 */

public class PresenterImpl implements Presenter {
    private IView iView;
    private ModelImpl model;
    public PresenterImpl(IView iView){
        this.iView = iView;
        model = new ModelImpl();
    }
    @Override
    public void onPutRequest(String url, Map<String, Object> map, Map<String, String> headmap, Class kind) {
        model.put(url, map, headmap, kind, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                iView.success(success);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });
    }

    @Override
    public void onGetRequest(String url, Map<String, String> map, Map<String, String> headmap, Class kind) {
        model.get(url, map, headmap, kind, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                iView.success(success);
            }

            @Override
            public void setError(Object error) {

                iView.error(error);
            }
        });
    }
}
