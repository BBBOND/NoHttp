package com.bbbond.nohttp.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bbbond.nohttp.R;
import com.bbbond.nohttp.util.MyUrl;
import com.kim.nohttp.net.NoHttpRequest;
import com.kim.nohttp.utils.NoHttpResponse;
import com.kim.nohttp.utils.RequestImageParams;
import com.kim.nohttp.utils.RequestParams;
import com.yolanda.nohttp.rest.Response;

public class NoHttpImageActivity extends AppCompatActivity {

    private ImageView ivGet;
    private ImageView ivPost;

    private NoHttpRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_http_image);
        request = NoHttpRequest.getInstance();

        initView();
    }

    private void initView() {
        ivGet = (ImageView) findViewById(R.id.iv_get);
        ivPost = (ImageView) findViewById(R.id.iv_post);
    }

    public void imgGet(View view) {
        RequestImageParams params = new RequestImageParams(NoHttpImageActivity.this, MyUrl.URL_NOHTTP_CACHE_IMAGE);
        params.setRequestMethod(RequestParams.RequestMethod.GET);
        params.setResponse(new NoHttpResponse() {
            @Override
            public void onSucceed(Response response) {
                ivGet.setImageBitmap((Bitmap) response.get());
            }

            @Override
            public void onFailed(String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                Toast.makeText(NoHttpImageActivity.this, "GET获取失败!", Toast.LENGTH_SHORT).show();
            }
        });
        request.requestImage(params);
    }

    public void imgPost(View view) {
        RequestImageParams params = new RequestImageParams(NoHttpImageActivity.this, MyUrl.URL_NOHTTP_CACHE_IMAGE);
        params.setRequestMethod(RequestParams.RequestMethod.POST);
        params.setResponse(new NoHttpResponse() {
            @Override
            public void onSucceed(Response response) {
                ivPost.setImageBitmap((Bitmap) response.get());
            }

            @Override
            public void onFailed(String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                Toast.makeText(NoHttpImageActivity.this, "POST获取失败!", Toast.LENGTH_SHORT).show();
            }
        });
        request.requestImage(params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        request = null;
    }
}
