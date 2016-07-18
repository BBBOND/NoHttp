package com.kim.nohttp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.net.HttpCookie;
import java.util.Map;

/**
 * 图片请求参数
 */
public class RequestImageParams extends RequestParams {

    //图片最大宽度，默认1000
    private int maxWidth = 1000;
    //图片最大高度，默认1000
    private int maxHeight = 1000;
    //图片编码格式
    private Bitmap.Config config = Bitmap.Config.ARGB_8888;
    //图片缩放类型
    private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_INSIDE;

    public RequestImageParams(Context context, String requestUrl) {
        super(context, requestUrl);
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Bitmap.Config getConfig() {
        return config;
    }

    public void setConfig(Bitmap.Config config) {
        this.config = config;
    }

    public ImageView.ScaleType getScaleType() {
        return scaleType;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
    }
}
