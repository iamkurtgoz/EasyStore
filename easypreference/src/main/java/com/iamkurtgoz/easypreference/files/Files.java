package com.iamkurtgoz.easypreference.files;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.iamkurtgoz.easypreference.Builder;
import com.iamkurtgoz.easypreference.CheckPermission;
import com.iamkurtgoz.easypreference.DownloadFilesCallBack;
import com.iamkurtgoz.easypreference.FilesCallBack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Deprecated
public class Files {

    private Builder builder;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String key;
    private File savedFile;

    public Files(Builder builder, SharedPreferences sharedPreferences, SharedPreferences.Editor editor, String savePath, String saveExtension, boolean isCache){
        this.builder = builder;
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
        //
        if (isCache){
            savePath = builder.getContext().getExternalCacheDir().getAbsolutePath();
        }
        savePath += "/" + getSimpleDateForFileName() + "." + saveExtension;
        savedFile = new File(savePath);
    }

    public void saveFile(String key, Bitmap bitmap, FilesCallBack filesCallBack){
        if (CheckPermission.checkStoragePermission(builder.getContext())){
            this.key = Utils.md5(key);
            writeBitmap(bitmap, filesCallBack);
        } else {
            onPermissionError(filesCallBack);
        }
    }

    public void saveFile(String key, byte[] bytes, FilesCallBack filesCallBack){
        if (CheckPermission.checkStoragePermission(builder.getContext())){
            this.key = Utils.md5(key);
            writeBytes(bytes, filesCallBack);
        } else {
            onPermissionError(filesCallBack);
        }
    }

    public void saveFile(String key, File file, FilesCallBack filesCallBack){
        if (CheckPermission.checkStoragePermission(builder.getContext())){
            this.key = Utils.md5(key);
            writeFile(file, filesCallBack);
        } else {
            onPermissionError(filesCallBack);
        }
    }

    public void saveFile(String url, FilesCallBack filesCallBack){
        if (CheckPermission.checkStoragePermission(builder.getContext())){
            this.key = Utils.md5(url);
            writeDownloadFile(url, filesCallBack);
        } else {
            onPermissionError(filesCallBack);
        }
    }

    /**
     * SAVED TASKS
     */

    private void writeBitmap(Bitmap bitmap, final FilesCallBack filesCallBack){
        onStartProgress(filesCallBack);
        new BitmapConventer(bitmap, new ByteCallBack() {
            @Override
            public void onResultBytes(byte[] bytes) {
                if (bytes == null || bytes.length == 0){
                    onErrorFileRead(filesCallBack);
                } else {
                    try {
                        writeToFile(bytes, savedFile);
                        editor.putString(key, savedFile.getAbsolutePath()).commit();
                        onSaveFile(savedFile.getAbsolutePath(), filesCallBack);
                    } catch (IOException e) {
                        e.printStackTrace();
                        onErrorFileWrite(filesCallBack);
                    }
                }
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void writeBytes(byte[] bytes, FilesCallBack filesCallBack){
        onStartProgress(filesCallBack);
        if (bytes == null || bytes.length == 0){
            onErrorFileRead(filesCallBack);
        } else {
            try {
                writeToFile(bytes, savedFile);
                editor.putString(key, savedFile.getAbsolutePath()).commit();
                onSaveFile(savedFile.getAbsolutePath(),filesCallBack);
            } catch (IOException e) {
                e.printStackTrace();
                onErrorFileWrite(filesCallBack);
            }
        }
    }

    private void writeFile(File file, final FilesCallBack filesCallBack){
        onStartProgress(filesCallBack);
        new FileConventer(file, new ByteCallBack() {
            @Override
            public void onResultBytes(byte[] bytes) {
                if (bytes == null || bytes.length == 0){
                    onErrorFileRead(filesCallBack);
                } else {
                    try {
                        writeToFile(bytes, savedFile);
                        editor.putString(key, savedFile.getAbsolutePath()).commit();
                        onSaveFile(savedFile.getAbsolutePath(),filesCallBack);
                    } catch (IOException e) {
                        e.printStackTrace();
                        onErrorFileWrite(filesCallBack);
                    }
                }
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void writeDownloadFile(String fileUrl, final FilesCallBack filesCallBack){
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
                        editor.putString(key, savedFile.getAbsolutePath()).commit();
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

    private void onStartProgress(FilesCallBack filesCallBack){
        filesCallBack.onStartDownload();
    }

    private void onPermissionError(FilesCallBack filesCallBack){
        filesCallBack.onError(EasyPreferenceError.ERROR_PERMISSION);
    }

    private void onErrorFileRead(FilesCallBack filesCallBack){
        filesCallBack.onError(EasyPreferenceError.ERROR_READ);
    }

    private void onErrorFileWrite(FilesCallBack filesCallBack){
        filesCallBack.onError(EasyPreferenceError.ERROR_WRITE);
    }

    private void onProgressRemaining(int percent, FilesCallBack filesCallBack){
        filesCallBack.onProgress(percent);
    }

    private void onSaveFile(String saveFilePath, FilesCallBack filesCallBack){
        filesCallBack.onSuccess(saveFilePath, key);
        Log.d("MyLog", saveFilePath);
    }

    public static String getSimpleDateForFileName(){
        return new SimpleDateFormat("yyyyMMdd_HHmmss" , Locale.getDefault()).format(new Date());
    }
}
