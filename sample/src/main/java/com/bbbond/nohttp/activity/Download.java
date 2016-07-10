package com.bbbond.nohttp.activity;

public class Download {
    private String fileName;
    private String url;
    private long currentSize;
    private long totalSize;
    private int progress;
    private String fileFolder;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(long currentSize) {
        this.currentSize = currentSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getFileFolder() {
        return fileFolder;
    }

    public void setFileFolder(String fileFolder) {
        this.fileFolder = fileFolder;
    }

    @Override
    public String toString() {
        return "Download{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", currentSize=" + currentSize +
                ", totalSize=" + totalSize +
                ", progress=" + progress +
                ", fileFolder='" + fileFolder + '\'' +
                '}';
    }
}
