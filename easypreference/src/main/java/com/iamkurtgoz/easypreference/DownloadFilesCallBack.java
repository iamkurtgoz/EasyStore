package com.iamkurtgoz.easypreference;

public interface DownloadFilesCallBack {
    void onProgress(int percent);
    void onResultBytes(byte[] bytes);
}
