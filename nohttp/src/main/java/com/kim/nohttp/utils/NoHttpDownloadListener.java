package com.kim.nohttp.utils;

import com.yolanda.nohttp.Headers;

/**
 * 下载监听
 */
public interface NoHttpDownloadListener {

    public void onDownloadError(Exception exception);

    public void onStart(boolean isResume, long rangeSize, Headers responseHeaders, long allCount);

    public void onProgress(int progress, long fileCount);

    public void onFinish(String filePath);

    public void onCancel();
}
