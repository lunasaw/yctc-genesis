package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public interface IFaceCallBack<T extends BaseResponse> {

    /**
     * 成功执行的操作
     * 
     * @param response
     */
    void onSuccess(T response);

    /**
     * 失败执行的操作
     * 
     * @param error
     */
    void onFailed(String error);
}
