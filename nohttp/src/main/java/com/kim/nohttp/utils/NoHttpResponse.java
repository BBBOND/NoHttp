package com.kim.nohttp.utils;

import com.yolanda.nohttp.rest.Response;

/**
 * 请求响应
 */
public interface NoHttpResponse {
    void onSucceed(Response response);

    void onFailed(String url, Object tag, Exception exception, int responseCode, long networkMillis);
}
