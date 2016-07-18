package com.kim.nohttp.utils;

import android.content.Context;

import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.RedirectHandler;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;

import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

/**
 * NoHttp请求参数
 */
public class RequestParams {

    public enum RequestMethod {
        POST,
        GET
    }

    //上下文
    private Context context;
    //请求路径
    private String requestUrl;
    //请求响应
    private NoHttpResponse response = null;
    //请求方式
    private RequestMethod requestMethod = RequestMethod.GET;
    //网络请求参数
    private Map<String, String> params;
    //请求头
    private List<HttpCookie> headers;
    //显示进度条
    private boolean showProgress = true;
    //进度条文字
    private String progressTitle;
    // json请求体
    private String requestBodyForJson = "";
    // 缓存模式
    private CacheMode cacheMode;
    // 设置超时时间
    private int connectTimeout = 6000;
    // 发送端（客户端）希望接受的数据类型
    private String accept;
    // Content-type
    private String contentType = "application/json";
    // 重定向操作 默认不重定向
    private RedirectHandler redirectHandler = new RedirectHandler() {
        @Override
        public Request<?> onRedirect(Headers responseHeaders) {
            return null;
        }

        @Override
        public boolean isDisallowedRedirect(Headers responseHeaders) {
            return true;
        }
    };


    public RequestParams(Context context, String requestUrl) {
        this.context = context;
        this.requestUrl = requestUrl;
    }

    public Context getContext() {
        return context;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public NoHttpResponse getResponse() {
        return response;
    }

    public void setResponse(NoHttpResponse response) {
        this.response = response;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public List<HttpCookie> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HttpCookie> headers) {
        this.headers = headers;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public String getProgressTitle() {
        return progressTitle;
    }

    public void setProgressTitle(String progressTitle) {
        this.progressTitle = progressTitle;
    }

    public RedirectHandler getRedirectHandler() {
        return redirectHandler;
    }

    public void setRedirectHandler(RedirectHandler redirectHandler) {
        this.redirectHandler = redirectHandler;
    }

    public String getRequestBodyForJson() {
        return requestBodyForJson;
    }

    public void setRequestBodyForJson(String requestBodyForJson) {
        this.requestBodyForJson = requestBodyForJson;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public CacheMode getCacheMode() {
        return cacheMode;
    }

    public void setCacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
