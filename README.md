# NoHttp
对NoHttp的封装，主要包括字符串、Json、JsonArray的GET和POST请求,以及下载方法的简单封装
NoHttp:http://www.nohttp.net/                                                                                                     

## 权限
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

## 使用方式
Gradle添加如下
```xml
compile 'com.kim.nohttp:nohttp:0.0.1'
```

## 请求
### String、Json、JsonArray、Image请求
```java
    // 获取请求对象
    NoHttpRequest request = NoHttpRequest.getInstance()
    // 获取请求配置
    RequestParams requestParams = new RequestParams(context, url);
    // 设置请求方式
    requestParams.setRequestMethod(RequestParams.RequestMethod.GET);
    // 设置请求头
    requestParams.setHeader(header);
    // 设置请求参数数据
    requestParams.setParams(params);
    // 设置是否显示进度条
    requestParams.setShowProgress(true);
    // 设置显示进度条标题
    requestParams.setProgressTitle(title);
    // 设置请求响应
    requestParams.setResponse(noHttpResponse);
    
    // 发出String请求
    request.requestString(requestParams);
    // 发出Json请求
    request.requestJsonObject(requestParams);
    // 发出JsonArray请求
    request.requestJsonArray(requestParams);
    // 发出Image请求，RequestImageParams继承自RequestParams
    request.requestImage(requestImageParams);
```
```java
    // 请求响应
    NoHttpResponse noHttpResponse = new NoHttpResponse() {
        @Override
        public void onSucceed(Response response) {
            // 成功时调用
            ...
        }
        
        @Override
        public void onFailed(String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            // 失败时调用
            ...
        }
    }
```

### 下载请求
```java
    // 获取下载请求对象
    NoHttpDownloadRequest request = NoHttpDownloadRequest.getInstance();
    // 获取请求配置
    RequestDownloadParams requestDownloadParams = new RequestDownloadParams(context, url, folder, fileName);
    // 设置不显示默认进度条
    requestDownloadParams.setShowProgress(false);
    // 设置断点下载
    requestDownloadParams.setRange(false);
    // 设置是否删除旧文件，或者直接使用旧文件
    requestDownloadParams.setDeleteOld(true);
    // 设置下载监听
    requestDownloadParams.setListener(noHttpDownloadListener);
    // 发出下载请求
    request.requestDownload(requestDownloadParams);
```
```java
    // 下载监听
    NoHttpDownloadListener noHttpDownloadListener = new NoHttpDownloadListener(){
        @Override
        public void onDownloadError(Exception exception) {
            // 下载出错时调用
            ...
        }
        
        @Override
        public void onStart(boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
            // 下载开始时调用
            ...
        }
        
        @Override
        public void onProgress(int progress, long fileCount) {
            // 下载进度更新
            ...
        }
        
        @Override
        public void onFinish(String filePath) {
            // 下载结束时调用
            ...
        }
        
        @Override
        public void onCancel() {
            // 下载取消时调用
            ...
        }
    }
```
