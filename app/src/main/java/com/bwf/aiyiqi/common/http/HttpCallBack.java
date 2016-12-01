package com.bwf.aiyiqi.common.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.bwf.aiyiqi.common.entity.BaseBean;
import com.bwf.aiyiqi.common.util.JsonUtil;
import com.bwf.aiyiqi.common.util.LogUtil;
import com.bwf.aiyiqi.common.util.StringUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Lizhangfeng on 2016/8/16 0016.
 * Description: 网络数据请求结果的回调
 */
public abstract class HttpCallBack<T> extends StringCallback {

    private Class<T> tClass;

    public HttpCallBack() {
        tClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        String errMsg = "";
        if (e == null) {
            errMsg = "network error";
        } else {
            errMsg = e.getMessage();
        }
        onFail(errMsg);
    }

    @Override
    public void onResponse(String response, int id) {

        if (StringUtil.isNotEmpty(response)) {

            LogUtil.e("服务器返回结果: " + response);

            try {

                BaseBean baseBean = JSON.parseObject(response, BaseBean.class);

                if ("0".equals(baseBean.error)) {

                    if (StringUtil.isNotEmpty(baseBean.data)) {
                        if (JsonUtil.getJsonType(baseBean.data) == JsonUtil.JSON_TYPE.JSON_TYPE_ARRAY) {//data为数组
                            onSuccess(null, JSON.parseArray(baseBean.data, tClass), response);
                        } else {
                            onSuccess(JSON.parseObject(baseBean.data, tClass), null, response);
                        }
                    } else {
                        onFail("result is empty");
                    }


                } else {
                    onFail(baseBean.message);
                }
            } catch (JSONException e) {//解析异常
                onFail("" + e.getMessage());
            }


        } else
            onFail("Data Exception !");

    }

    /* 数据为Object类型则result不为null，数据为Array则tList不为null，response为服务器返回结果 */
    public abstract void onSuccess(T result, List<T> tList, String response);

    public abstract void onFail(String errMsg);

}
