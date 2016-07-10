package com.kim.nohttp.net;

import com.kim.nohttp.utils.LoadingProgress;
import com.kim.nohttp.utils.RequestImageParams;
import com.kim.nohttp.utils.RequestParams;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

/**
 * NoHttp普通请求类
 */
public class NoHttpRequest {

    private RequestQueue queue;
    private LoadingProgress progress;

    private static NoHttpRequest noHttpRequest = new NoHttpRequest();

    private NoHttpRequest() {
        queue = NoHttp.newRequestQueue();
    }

    public static NoHttpRequest getInstance() {
        return noHttpRequest;
    }

    /**
     * 请求文本
     *
     * @param requestParams 请求参数
     */
    public void requestString(final RequestParams requestParams) {
        progress = LoadingProgress.getInstance();
        final Request request = NoHttp.createStringRequest(requestParams.getRequestUrl(), requestParams.getRequestMethod() == RequestParams.RequestMethod.GET ? RequestMethod.GET : RequestMethod.POST);
        request.add(requestParams.getParams());
        request.setHeader(requestParams.getHeader());
        request.setCancelSign(requestParams.getRequestUrl());
        queue.add(0, request, new OnResponseListener() {
            @Override
            public void onStart(int what) {
                if (requestParams.isShowProgress())
                    progress.show(requestParams.getContext(), requestParams.getProgressTitle());
            }

            @Override
            public void onSucceed(int what, Response response) {
                if (requestParams.getResponse() != null)
                    requestParams.getResponse().onSucceed(response);
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                if (requestParams.getResponse() != null)
                    requestParams.getResponse().onFailed(url, tag, exception, responseCode, networkMillis);
            }

            @Override
            public void onFinish(int what) {
                if (requestParams.isShowProgress())
                    progress.dismiss();
                progress = null;
            }
        });
    }

    /**
     * 请求Json对象
     *
     * @param requestParams 请求参数
     */
    public void requestJsonObject(final RequestParams requestParams) {
        progress = LoadingProgress.getInstance();
        Request request = NoHttp.createJsonObjectRequest(requestParams.getRequestUrl(), requestParams.getRequestMethod() == RequestParams.RequestMethod.GET ? RequestMethod.GET : RequestMethod.POST);
        request.add(requestParams.getParams());
        request.setHeader(requestParams.getHeader());
        request.setCancelSign(requestParams.getRequestUrl());
        queue.add(1, request, new OnResponseListener() {
            @Override
            public void onStart(int what) {
                if (requestParams.isShowProgress())
                    progress.show(requestParams.getContext(), requestParams.getProgressTitle());
            }

            @Override
            public void onSucceed(int what, Response response) {
                if (requestParams.getResponse() != null)
                    requestParams.getResponse().onSucceed(response);
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                if (requestParams.getResponse() != null)
                    requestParams.getResponse().onFailed(url, tag, exception, responseCode, networkMillis);
            }

            @Override
            public void onFinish(int what) {
                if (requestParams.isShowProgress())
                    progress.dismiss();
                progress = null;
            }
        });
    }

    /**
     * 请求Json数组
     *
     * @param requestParams 请求参数
     */
    public void requestJsonArray(final RequestParams requestParams) {
        progress = LoadingProgress.getInstance();
        Request request = NoHttp.createJsonArrayRequest(requestParams.getRequestUrl(), requestParams.getRequestMethod() == RequestParams.RequestMethod.GET ? RequestMethod.GET : RequestMethod.POST);
        request.add(requestParams.getParams());
        request.addHeader(requestParams.getHeader());
        request.setCancelSign(requestParams.getRequestUrl());
        queue.add(2, request, new OnResponseListener() {
            @Override
            public void onStart(int what) {
                if (requestParams.isShowProgress())
                    progress.show(requestParams.getContext(), requestParams.getProgressTitle());
            }

            @Override
            public void onSucceed(int what, Response response) {
                if (requestParams.getResponse() != null)
                    requestParams.getResponse().onSucceed(response);
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                if (requestParams.getResponse() != null)
                    requestParams.getResponse().onFailed(url, tag, exception, responseCode, networkMillis);
            }

            @Override
            public void onFinish(int what) {
                if (requestParams.isShowProgress())
                    progress.dismiss();
                progress = null;
            }
        });
    }

    /**
     * 请求图资源
     *
     * @param requestImageParams 请求参数
     */
    public void requestImage(final RequestImageParams requestImageParams) {
        progress = LoadingProgress.getInstance();
        final Request request = NoHttp.createImageRequest(requestImageParams.getRequestUrl(), requestImageParams.getRequestMethod() == RequestParams.RequestMethod.GET ? RequestMethod.GET : RequestMethod.POST, requestImageParams.getMaxWidth(), requestImageParams.getMaxHeight(), requestImageParams.getConfig(), requestImageParams.getScaleType());
        request.add(requestImageParams.getParams());
        request.addHeader(requestImageParams.getHeader());
        request.setCancelSign(requestImageParams.getRequestUrl());
        queue.add(3, request, new OnResponseListener() {
            @Override
            public void onStart(int what) {
                if (requestImageParams.isShowProgress())
                    progress.show(requestImageParams.getContext(), requestImageParams.getProgressTitle());
            }

            @Override
            public void onSucceed(int what, Response response) {
                if (requestImageParams.getResponse() != null)
                    requestImageParams.getResponse().onSucceed(response);
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                if (requestImageParams.getResponse() != null)
                    requestImageParams.getResponse().onFailed(url, tag, exception, responseCode, networkMillis);
            }

            @Override
            public void onFinish(int what) {
                if (requestImageParams.isShowProgress())
                    progress.dismiss();
                progress = null;
            }
        });
    }

    /**
     * 通过设置的标识取消请求
     *
     * @param url 请求地址
     */
    public void cancelRequest(String url) {
        queue.cancelBySign(url);
    }

    /**
     * 取消所有请求
     */
    public void cancelAll() {
        queue.cancelAll();
    }
}
