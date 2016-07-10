package com.bbbond.nohttp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bbbond.nohttp.R;
import com.bbbond.nohttp.util.MyUrl;
import com.kim.nohttp.net.NoHttpRequest;
import com.kim.nohttp.utils.NoHttpResponse;
import com.kim.nohttp.utils.RequestParams;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class NoHttpActivity extends AppCompatActivity {

    private static final String TAG = "NoHttpActivity";
    private TextView tvStrGet;
    private TextView tvStrPost;
    private TextView tvJsonGet;
    private TextView tvJsonPost;

    private NoHttpRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_http);

        request = NoHttpRequest.getInstance();

        initView();
    }

    private void initView() {
        tvStrGet = (TextView) findViewById(R.id.tv_str_get);
        tvStrPost = (TextView) findViewById(R.id.tv_str_post);
        tvJsonGet = (TextView) findViewById(R.id.tv_json_get);
        tvJsonPost = (TextView) findViewById(R.id.tv_json_post);
    }

    public void strGet(View view) {
        RequestParams params = new RequestParams(NoHttpActivity.this, MyUrl.URL_NOHTTP_METHOD);
        params.setShowProgress(true);
        params.setResponse(new NoHttpResponse() {
            @Override
            public void onSucceed(Response response) {
                tvStrGet.setText((String) response.get());
            }

            @Override
            public void onFailed(String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                tvStrGet.setText("String Get失败！");
            }
        });
        request.requestString(params);
    }

    public void strPost(View view) {
        RequestParams params = new RequestParams(NoHttpActivity.this, MyUrl.URL_NOHTTP_METHOD);
        params.setShowProgress(true);
        params.setRequestMethod(RequestParams.RequestMethod.POST);
        params.setResponse(new NoHttpResponse() {
            @Override
            public void onSucceed(Response response) {
                tvStrPost.setText((String) response.get());
            }

            @Override
            public void onFailed(String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                tvStrPost.setText("String Post失败！");
            }
        });
        request.requestString(params);
    }

    public void jsonGet(View view) {
        RequestParams params = new RequestParams(NoHttpActivity.this, MyUrl.URL_NOHTTP_METHOD);
        params.setShowProgress(true);
        params.setRequestMethod(RequestParams.RequestMethod.GET);
        params.setResponse(new NoHttpResponse() {
            @Override
            public void onSucceed(Response response) {
                String result = "";
                try {
                    JSONObject jsonObject = (JSONObject) response.get();
                    result += "error : " + jsonObject.getString("error");
                    result += "\nnohttp : " + jsonObject.getJSONObject("data").getString("nohttp");
                    result += "\nkim : " + jsonObject.getJSONObject("data").getString("yolanda");
                    result += "\nmethod : " + jsonObject.getString("method");
                    result += "\nurl : " + jsonObject.getString("url");
                } catch (JSONException e) {
                    e.printStackTrace();
                    result = "转换失败!";
                }
                tvJsonGet.setText(result);
            }

            @Override
            public void onFailed(String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                tvJsonGet.setText("JSON Get失败！");
            }
        });
        request.requestJsonObject(params);
    }

    public void jsonPost(View view) {
        RequestParams params = new RequestParams(NoHttpActivity.this, MyUrl.URL_NOHTTP_METHOD);
        params.setShowProgress(true);
        params.setRequestMethod(RequestParams.RequestMethod.POST);
        params.setResponse(new NoHttpResponse() {
            @Override
            public void onSucceed(Response response) {
                String result = "";
                try {
                    JSONObject jsonObject = (JSONObject) response.get();
                    result += "error : " + jsonObject.getString("error");
                    result += "\nnohttp : " + jsonObject.getJSONObject("data").getString("nohttp");
                    result += "\nkim : " + jsonObject.getJSONObject("data").getString("yolanda");
                    result += "\nmethod : " + jsonObject.getString("method");
                    result += "\nurl : " + jsonObject.getString("url");
                } catch (JSONException e) {
                    e.printStackTrace();
                    result = "转换失败!";
                }
                tvJsonPost.setText(result);
            }

            @Override
            public void onFailed(String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                tvJsonPost.setText("JSON Post失败！");
            }
        });
        request.requestJsonObject(params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        request = null;
    }
}
