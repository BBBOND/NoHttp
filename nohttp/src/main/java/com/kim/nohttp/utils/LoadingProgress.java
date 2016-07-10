package com.kim.nohttp.utils;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * 进度条工具类
 */
public class LoadingProgress {

    private ProgressDialog loadingDialog;

    private Context mContext;

    private static LoadingProgress progress = new LoadingProgress();

    public static LoadingProgress getInstance() {
        return progress;
    }

    private LoadingProgress() {
    }

    public void show(Context Context) {
        this.mContext = Context;
        show(mContext, "正在加载...");
    }

    public void show(Context mContext, String msg) {
        if (null != mContext) {
            if (msg != null) {
                loadingDialog = new ProgressDialog(mContext, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                loadingDialog.setMessage(msg);
                loadingDialog.show();
            } else
                show(mContext);
        }
    }

    public void dismiss() {
        loadingDialog.dismiss();
    }
}