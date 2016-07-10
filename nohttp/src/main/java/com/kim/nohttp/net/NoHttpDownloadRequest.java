package com.kim.nohttp.net;

import com.kim.nohttp.utils.RequestDownloadParams;
import com.kim.nohttp.utils.RequestParams;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.download.DownloadRequest;

/**
 * NoHttp下载类
 */
public class NoHttpDownloadRequest {
    private DownloadQueue queue;

    private static NoHttpDownloadRequest noHttpDownloadRequest = new NoHttpDownloadRequest();

    private NoHttpDownloadRequest() {
        queue = NoHttp.newDownloadQueue();
    }

    public static NoHttpDownloadRequest getInstance() {
        return noHttpDownloadRequest;
    }

    /**
     * 请求下载任务
     *
     * @param requestDownloadParams 请求参数
     */
    public void requestDownload(final RequestDownloadParams requestDownloadParams) {
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest(requestDownloadParams.getRequestUrl(), requestDownloadParams.getRequestMethod() == RequestParams.RequestMethod.GET ? RequestMethod.GET : RequestMethod.POST, requestDownloadParams.getFileFolder(), requestDownloadParams.getFilename(), requestDownloadParams.isRange(), requestDownloadParams.isDeleteOld());
        downloadRequest.add(requestDownloadParams.getParams());
        downloadRequest.addHeader(requestDownloadParams.getHeader());
        downloadRequest.setCancelSign(requestDownloadParams.getRequestUrl());
        queue.add(0, downloadRequest, new DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                if (requestDownloadParams.getDownloadResponse() != null)
                    requestDownloadParams.getDownloadResponse().onDownloadError(exception);
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                if (requestDownloadParams.getDownloadResponse() != null)
                    requestDownloadParams.getDownloadResponse().onStart(isResume, rangeSize, responseHeaders, allCount);
            }

            @Override
            public void onProgress(int what, int progress, long fileCount) {
                if (requestDownloadParams.getDownloadResponse() != null)
                    requestDownloadParams.getDownloadResponse().onProgress(progress, fileCount);
            }

            @Override
            public void onFinish(int what, String filePath) {
                if (requestDownloadParams.getDownloadResponse() != null)
                    requestDownloadParams.getDownloadResponse().onFinish(filePath);
            }

            @Override
            public void onCancel(int what) {
                if (requestDownloadParams.getDownloadResponse() != null)
                    requestDownloadParams.getDownloadResponse().onCancel();
            }
        });
    }

    /**
     * 通过设置的标识取消请求
     *
     * @param url 请求地址
     */
    public void cancelDownload(String url) {
        queue.cancelBySign(url);
    }

    /**
     * 取消所有请求
     */
    public void cancelAll() {
        queue.cancelAll();
    }
}
