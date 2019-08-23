package com.iamkurtgoz.easystore;

public interface EasyStoreFilesCallback {
    void onStartDownload();
    void onError(int errorCode);
    void onProgress(int percent);
    void onSuccess(String filePath, String saveKey);
}
