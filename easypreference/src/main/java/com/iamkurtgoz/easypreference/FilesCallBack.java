package com.iamkurtgoz.easypreference;

public interface FilesCallBack {
    void onStartDownload();
    void onError(int errorCode);
    void onProgress(int percent);
    void onSuccess(String filePath, String saveKey);
}