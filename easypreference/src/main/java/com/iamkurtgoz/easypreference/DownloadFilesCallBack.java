package com.iamkurtgoz.easypreference;

@Deprecated
public interface DownloadFilesCallBack {
    void onProgress(int percent);
    void onResultBytes(byte[] bytes);
}
