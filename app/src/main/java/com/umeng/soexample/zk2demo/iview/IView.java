package com.umeng.soexample.zk2demo.iview;

/**
 * Created by W on 2019/2/23 11:12.
 */

public interface IView<T> {
    void success(T success);
    void error(T error);
}
