package edu.yctc.face.util;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author by licheng on 2018/7/3.
 */

public class TransCallBack<T extends BaseResponse> implements Callback {

    private Class<T> clazz;

    private IFaceCallBack<T> facePPCallBack;

    public TransCallBack(IFaceCallBack<T> facePPCallBack, Class<T> clazz) {
        this.facePPCallBack = facePPCallBack;
        this.clazz = clazz;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        if (null != facePPCallBack) {
            facePPCallBack.onFailed(e.getMessage());
        }
        System.out.println(e.getMessage());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (null == facePPCallBack) {
            return;
        }
        ResponseBody responseBody = response.body();
        try {
            T result = JSONObject.parseObject(responseBody.string(), clazz);
            String error = result.getErrorMessage();
            if (null != error && !"".equals(error)) {
                facePPCallBack.onFailed(error);
            } else {
                facePPCallBack.onSuccess(result);
            }
        } catch (Exception e) {
            if (null != facePPCallBack) {
                facePPCallBack.onFailed(e.getMessage());
            }
        }
    }
}
