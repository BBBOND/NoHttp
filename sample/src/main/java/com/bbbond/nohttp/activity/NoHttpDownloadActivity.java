package com.bbbond.nohttp.activity;

import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bbbond.nohttp.R;
import com.bbbond.nohttp.util.MyUrl;
import com.kim.nohttp.net.NoHttpDownloadRequest;
import com.kim.nohttp.utils.NoHttpDownloadListener;
import com.kim.nohttp.utils.RequestDownloadParams;
import com.yolanda.nohttp.Headers;

import java.util.ArrayList;
import java.util.List;

public class NoHttpDownloadActivity extends AppCompatActivity {

    private static final String TAG = "NoHttpDownloadActivity";
    private ListView lvDownload;

    private List<Download> downloadList = new ArrayList<>();
    private DownloadAdapter adapter;
    private NoHttpDownloadRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_http_download);

        request = NoHttpDownloadRequest.getInstance();

//        initData();

        initView();
    }

    private void initData() {
        for (int i = 0; i < MyUrl.URL_NOHTTP_DOWNLOAD.length; i++) {
            Download download = new Download();
            int j = MyUrl.URL_NOHTTP_DOWNLOAD[i].lastIndexOf('/');
            download.setFileName(MyUrl.URL_NOHTTP_DOWNLOAD[i].substring(j + 1, MyUrl.URL_NOHTTP_DOWNLOAD[i].length()));
            download.setFileFolder(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Environment.DIRECTORY_DOWNLOADS);
            download.setProgress(0);
            download.setTotalSize(0);
            download.setUrl(MyUrl.URL_NOHTTP_DOWNLOAD[i]);
            downloadList.add(download);
        }
        Log.d(TAG, "initData: " + downloadList.toString());
    }

    private void initView() {
        lvDownload = (ListView) findViewById(R.id.lv_download);
        adapter = new DownloadAdapter(NoHttpDownloadActivity.this, R.layout.download_item, downloadList);
        lvDownload.setAdapter(adapter);
        lvDownload.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NoHttpDownloadActivity.this);
                builder.setTitle("温馨提示");
                builder.setMessage(downloadList.get(i).getFileName() + " 将会被删除！");
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        request.cancelDownload(downloadList.get(i).getUrl());
                        downloadList.remove(i);
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

    public void startAll(View view) {
        downloadList.clear();
        for (String url : MyUrl.URL_NOHTTP_DOWNLOAD) {
            final Download download = new Download();
            int j = url.lastIndexOf('/');
            String fileName = url.substring(j + 1, url.length());
            String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Environment.DIRECTORY_DOWNLOADS;
            Log.d(TAG, "folder" + folder);
            download.setUrl(url);
            download.setFileName(fileName);
            download.setFileFolder(folder);

            RequestDownloadParams params = new RequestDownloadParams(NoHttpDownloadActivity.this, url, folder, fileName);
            params.setShowProgress(false);
            params.setRange(false);
            params.setDeleteOld(true);
            params.setListener(new NoHttpDownloadListener() {
                @Override
                public void onDownloadError(Exception exception) {
                    exception.printStackTrace();
                    Toast.makeText(NoHttpDownloadActivity.this, "文件 " + download.getFileName() + " 下载失败", Toast.LENGTH_SHORT).show();
                    downloadList.remove(download);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onStart(boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                    download.setProgress(0);
                    download.setCurrentSize(rangeSize);
                    download.setTotalSize(allCount);
                    downloadList.add(download);
                }

                @Override
                public void onProgress(int progress, long fileCount) {
                    download.setProgress(progress);
                    download.setCurrentSize(fileCount);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFinish(String filePath) {
                    Toast.makeText(NoHttpDownloadActivity.this, "文件已保存至" + filePath, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancel() {
                    downloadList.remove(download);
                }
            });
            request.requestDownload(params);
        }
    }

    public void stopAll(View view) {
        request.cancelAll();
        downloadList.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        request = null;
    }
}
