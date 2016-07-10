package com.kim.nohttp.utils;

import android.content.Context;

/**
 * 下载请求的参数
 */
public class RequestDownloadParams extends RequestParams {

    //文件路径
    private String fileFolder;
    //文件名
    private String filename;
    //是否断点继续
    private boolean isRange = false;
    //删除旧文件，或者直接使用旧文件，不访问网络
    private boolean isDeleteOld = false;
    //下载监听
    private NoHttpDownloadListener listener = null;

    public RequestDownloadParams(Context context, String requestUrl, String fileFolder, String filename) {
        this(context, requestUrl, fileFolder, filename, null);
    }

    public RequestDownloadParams(Context context, String requestUrl, String fileFolder, String filename, NoHttpDownloadListener listener) {
        super(context, requestUrl);
        this.fileFolder = fileFolder;
        this.filename = filename;
        this.listener = listener;
    }


    public String getFileFolder() {
        return fileFolder;
    }

    public void setFileFolder(String fileFolder) {
        this.fileFolder = fileFolder;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isRange() {
        return isRange;
    }

    public void setRange(boolean range) {
        isRange = range;
    }

    public boolean isDeleteOld() {
        return isDeleteOld;
    }

    public void setDeleteOld(boolean deleteOld) {
        isDeleteOld = deleteOld;
    }

    public NoHttpDownloadListener getDownloadResponse() {
        return listener;
    }

    public void setListener(NoHttpDownloadListener listener) {
        this.listener = listener;
    }
}
