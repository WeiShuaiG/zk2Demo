package com.umeng.soexample.zk2demo.callback;

/**
 * Created by W on 2019/2/23 11:11.
 */

public interface MyCallBack<T> {
    void setSuccess(T success);
    void setError(T error);
}
