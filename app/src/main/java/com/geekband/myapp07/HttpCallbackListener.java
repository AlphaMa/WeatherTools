package com.geekband.myapp07;

/**
 * Created by SUN on 2016/6/27.
 */
public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);
}
