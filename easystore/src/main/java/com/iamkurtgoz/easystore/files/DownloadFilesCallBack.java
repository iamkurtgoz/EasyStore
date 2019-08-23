package com.iamkurtgoz.easystore.files;

public interface DownloadFilesCallBack {
    void onProgress(int percent);
    void onResultBytes(byte[] bytes);
}
