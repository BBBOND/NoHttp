package com.kim.nohttp.utils;

import android.content.Context;

import java.net.HttpCookie;
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
    private HttpCookie header;
    //显示进度条
    private boolean showProgress = true;
    //进度条文字
    private String progressTitle;

    public RequestParams(Context context, String requestUrl) {
        this(context, requestUrl, null);
    }

    public RequestParams(Context context, String requestUrl, NoHttpResponse response) {
        this(context, requestUrl, response, RequestMethod.GET);
    }

    public RequestParams(Context context, String requestUrl, NoHttpResponse response, RequestMethod requestMethod) {
        this(context, requestUrl, response, requestMethod, null);
    }

    public RequestParams(Context context, String requestUrl, NoHttpResponse response, RequestMethod requestMethod, Map<String, String> params) {
        this(context, requestUrl, response, requestMethod, params, null);
    }

    public RequestParams(Context context, String requestUrl, NoHttpResponse response, RequestMethod requestMethod, Map<String, String> params, HttpCookie header) {
        this(context, requestUrl, response, requestMethod, params, header, true);
    }

    public RequestParams(Context context, String requestUrl, NoHttpResponse response, RequestMethod requestMethod, Map<String, String> params, HttpCookie header, boolean showProgress) {
        this(context, requestUrl, response, requestMethod, params, header, showProgress, null);
    }

    public RequestParams(Context context, String requestUrl, NoHttpResponse response, RequestMethod requestMethod, Map<String, String> params, HttpCookie header, boolean showProgress, String progressTitle) {
        this.context = context;
        this.requestUrl = requestUrl;
        this.response = response;
        this.requestMethod = requestMethod;
        this.params = params;
        this.header = header;
        this.showProgress = showProgress;
        this.progressTitle = progressTitle;
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

    public HttpCookie getHeader() {
        return header;
    }

    public void setHeader(HttpCookie header) {
        this.header = header;
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
}
