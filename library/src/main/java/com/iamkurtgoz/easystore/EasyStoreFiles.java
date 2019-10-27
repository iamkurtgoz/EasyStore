package com.iamkurtgoz.easystore;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.iamkurtgoz.easystore.architectures.EasyStoreFilesCallback;
import com.iamkurtgoz.easystore.files.BitmapConventer;
import com.iamkurtgoz.easystore.files.ByteCallBack;
import com.iamkurtgoz.easystore.files.DownloadByte;
import com.iamkurtgoz.easystore.files.DownloadFilesCallBack;
import com.iamkurtgoz.easystore.files.EasyStoreError;
import com.iamkurtgoz.easystore.files.FileConventer;
import com.iamkurtgoz.easystore.files.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EasyStoreFiles extends ContextWrapper {

    private static SharedPreferences sharedPreferences;
    private static String savePath;
    private static String saveExtension;
    private static Boolean isCache;
    private static EasyStoreFilesCallback easyStoreFilesCallback;

    private File savedFile;
    private String key;

    public static EasyStoreFiles from(EasyStore easyStore, Context context, String savePath, String saveExtension, Boolean isCache, EasyStoreFilesCallback easyStoreFilesCallback){
        return new EasyStoreFiles(easyStore, context, savePath, saveExtension, isCache, easyStoreFilesCallback);
    }

    private EasyStoreFiles(EasyStore easyStore, Context context, String getSavePath, String getSaveExtension, Boolean getIsCache, EasyStoreFilesCallback getEasyStoreFilesCallback){
        super(context);
        sharedPreferences = easyStore.getSharedPreferences();
        savePath = getSavePath;
        saveExtension = getSaveExtension;
        isCache = getIsCache;
        easyStoreFilesCallback = getEasyStoreFilesCallback;

        if (isCache){
            savePath = getExternalCacheDir().getAbsolutePath();
        }
        savePath += "/" + getSimpleDateForFileName() + "." + saveExtension;
        savedFile = new File(savePath);
    }

    private SharedPreferences.Editor getEditor() {
        if (sharedPreferences == null) {
            throw new NullPointerException("EasyStoreError, Preference is a null.");
        }
        return sharedPreferences.edit();
    }

    public void saveFile(Bitmap bitmap){
        if (CheckPermission.checkStoragePermission(this)){
            writeBitmap(bitmap, easyStoreFilesCallback);
        } else {
            onPermissionError(easyStoreFilesCallback);
        }
    }

    public void saveFile(byte[] bytes){
        if (CheckPermission.checkStoragePermission(this)){
            writeBytes(bytes, easyStoreFilesCallback);
        } else {
            onPermissionError(easyStoreFilesCallback);
        }
    }

    public void saveFile(File file){
        if (CheckPermission.checkStoragePermission(this)){
            writeFile(file, easyStoreFilesCallback);
        } else {
            onPermissionError(easyStoreFilesCallback);
        }
    }

    public void saveFile(String url){
        if (CheckPermission.checkStoragePermission(this)){
            writeDownloadFile(url, easyStoreFilesCallback);
        } else {
            onPermissionError(easyStoreFilesCallback);
        }
    }

    /**
     * SAVED TASKS
     */

    private void writeBitmap(Bitmap bitmap, final EasyStoreFilesCallback filesCallBack){
        onStartProgress(filesCallBack);
        new BitmapConventer(bitmap, new ByteCallBack() {
            @Override
            public void onResultBytes(byte[] bytes) {
                if (bytes == null || bytes.length == 0){
                    onErrorFileRead(filesCallBack);
                } else {
                    try {
                        writeToFile(bytes, savedFile);
                        key = Utils.md5(savedFile.getAbsolutePath());
                        getEditor().putString(EasyStore.LAST_FILE_SAVE_PATH, savedFile.getAbsolutePath()).commit();
                        getEditor().putString(key, savedFile.getAbsolutePath()).commit();
                        onSaveFile(savedFile.getAbsolutePath(), filesCallBack);
                    } catch (IOException e) {
                        e.printStackTrace();
                        onErrorFileWrite(filesCallBack);
                    }
                }
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void writeBytes(byte[] bytes, EasyStoreFilesCallback filesCallBack){
        onStartProgress(filesCallBack);
        if (bytes == null || bytes.length == 0){
            onErrorFileRead(filesCallBack);
        } else {
            try {
                writeToFile(bytes, savedFile);
                key = Utils.md5(savedFile.getAbsolutePath());
                getEditor().putString(EasyStore.LAST_FILE_SAVE_PATH, savedFile.getAbsolutePath()).commit();
                getEditor().putString(key, savedFile.getAbsolutePath()).commit();
                onSaveFile(savedFile.getAbsolutePath(),filesCallBack);
            } catch (IOException e) {
                e.printStackTrace();
                onErrorFileWrite(filesCallBack);
            }
        }
    }

    private void writeFile(File file, final EasyStoreFilesCallback filesCallBack){
        onStartProgress(filesCallBack);
        new FileConventer(file, new ByteCallBack() {
            @Override
            public void onResultBytes(byte[] bytes) {
                if (bytes == null || bytes.length == 0){
                    onErrorFileRead(filesCallBack);
                } else {
                    try {
                        writeToFile(bytes, savedFile);
                        key = Utils.md5(savedFile.getAbsolutePath());
                        getEditor().putString(EasyStore.LAST_FILE_SAVE_PATH, savedFile.getAbsolutePath()).commit();
                        getEditor().putString(key, savedFile.getAbsolutePath()).commit();
                        onSaveFile(savedFile.getAbsolutePath(),filesCallBack);
                    } catch (IOException e) {
                        e.printStackTrace();
                        onErrorFileWrite(filesCallBack);
                    }
                }
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void writeDownloadFile(String fileUrl, final EasyStoreFilesCallback filesCallBack){
        onStartProgress(filesCallBack);
        new DownloadByte(fileUrl, new DownloadFilesCallBack() {
            @Override
            public void onProgress(int percent) {
                onProgressRemaining(percent, filesCallBack);
            }

            @Override
            public void onResultBytes(byte[] bytes) {
                if (bytes == null || bytes.length == 0){
                    onErrorFileRead(filesCallBack);
                } else {
                    try {
                        writeToFile(bytes, savedFile);
                        key = Utils.md5(savedFile.getAbsolutePath());
                        getEditor().putString(EasyStore.LAST_FILE_SAVE_PATH, savedFile.getAbsolutePath()).commit();
                        getEditor().putString(key, savedFile.getAbsolutePath()).commit();
                        onSaveFile(savedFile.getAbsolutePath(), filesCallBack);
                    } catch (IOException e) {
                        e.printStackTrace();
                        onErrorFileWrite(filesCallBack);
                    }
                }
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void writeToFile(byte[] data, File file) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        out.write(data);
        out.close();
    }


    /**
     * ON RESULT
     */

    private void onStartProgress(EasyStoreFilesCallback filesCallBack){
        filesCallBack.onStartDownload();
    }

    private void onPermissionError(EasyStoreFilesCallback filesCallBack){
        filesCallBack.onError(EasyStoreError.ERROR_PERMISSION);
    }

    private void onErrorFileRead(EasyStoreFilesCallback filesCallBack){
        filesCallBack.onError(EasyStoreError.ERROR_READ);
    }

    private void onErrorFileWrite(EasyStoreFilesCallback filesCallBack){
        filesCallBack.onError(EasyStoreError.ERROR_WRITE);
    }

    private void onProgressRemaining(int percent, EasyStoreFilesCallback filesCallBack){
        filesCallBack.onProgress(percent);
    }

    private void onSaveFile(String saveFilePath, EasyStoreFilesCallback filesCallBack){
        filesCallBack.onSuccess(saveFilePath, key);
    }

    public static String getSimpleDateForFileName(){
        return new SimpleDateFormat("yyyyMMdd_HHmmss" , Locale.getDefault()).format(new Date());
    }
}
