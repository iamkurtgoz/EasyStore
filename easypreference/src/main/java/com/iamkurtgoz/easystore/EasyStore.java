package com.iamkurtgoz.easystore;

import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.iamkurtgoz.easystore.CheckPermission;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;


public class EasyStore {

    private static final String LAST_FILE_SAVE_PATH = "com.iamkurtgoz.easystory.LAST_FILE_SAVE_PATH";

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** INITALIZE *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    public static BasicValue use(){
        EasyStoreBuilder builder = EasyStoreBuilder.getBuilder();
        return BasicValue.from(builder);
    }

    public static FilesValue fileCache(String saveExtension, EasyStoreFilesCallback easyStoreFilesCallback){
        EasyStoreBuilder builder = EasyStoreBuilder.getBuilder();
        return FilesValue.from(builder, "", saveExtension, true, easyStoreFilesCallback);
    }

    public static FilesValue fileExternal(String savePath, String saveExtension, EasyStoreFilesCallback easyStoreFilesCallback){
        EasyStoreBuilder builder = EasyStoreBuilder.getBuilder();
        return FilesValue.from(builder, savePath, saveExtension, false, easyStoreFilesCallback);
    }

    public static class BasicValue extends ContextWrapper {

        private static SharedPreferences sharedPreferences;

        public static BasicValue from(EasyStoreBuilder easyStoreBuilder){
            return new BasicValue(easyStoreBuilder);
        }

        private BasicValue(EasyStoreBuilder easyStoreBuilder) {
            super(easyStoreBuilder.getContext());
            sharedPreferences = easyStoreBuilder.getSharedPreferences();
        }

        private SharedPreferences.Editor getEditor(){
            if (sharedPreferences == null){
                throw new NullPointerException("EasyStoreError, Preference is a null.");
            }
            return sharedPreferences.edit();
        }

        public boolean hasExistStringKey(String key){
            boolean isExist = false;
            if (!get(key, "").equalsIgnoreCase("")){
                isExist = true;
            }
            return isExist;
        }

        public boolean hasExistIntegerKey(String key){
            boolean isExist = false;
            if (get(key, 0) != 0){
                isExist = true;
            }
            return isExist;
        }

        public boolean hasExistFloatKey(String key){
            boolean isExist = false;
            if (get(key, 0f) != 0f){
                isExist = true;
            }
            return isExist;
        }

        public boolean hasExistBooleanKey(String key){
            boolean isExist = false;
            if (get(key, false)){
                isExist = true;
            }
            return isExist;
        }

        public boolean hasExistLongKey(String key){
            boolean isExist = false;
            if (get(key, 0L) != 0L){
                isExist = true;
            }
            return isExist;
        }

        /**********************************************************************************************/
        /**********************************************************************************************/
        /*************************************** WRITE DATA *******************************************/
        /**********************************************************************************************/
        /**********************************************************************************************/

        public void set(String key, String value){
            set(key, (Object) value);
        }

        public void set(String key, Integer value){
            set(key, (Object) value);
        }

        public void set(String key, Float value){
            set(key, (Object) value);
        }

        public void set(String key, Boolean value){
            set(key, (Object) value);
        }

        public void set(String key, Long value){
            set(key, (Object) value);
        }

        public void set(String key, Set<String> value){
            set(key, (Object) value);
        }

        public void set(ArrayList<EasyModel> values){
            for (EasyModel model : values){
                String key = model.getKey();
                Object value = model.getValue();
                set(key, value);
            }
        }

        public void set(EasyModel ...values){
            for (EasyModel model : values){
                String key = model.getKey();
                Object value = model.getValue();
                set(key, value);
            }
        }


        private void set(String key, Object value){
            if (key == null || value == null){
                throw new NullPointerException("NullPointerException, key or value!");
            }
            if (value instanceof String){
                getEditor().putString(key, (String) value).commit();
            } else if (value instanceof Integer){
                getEditor().putInt(key, (Integer) value).commit();
            } else if (value instanceof Float){
                getEditor().putFloat(key, (Float) value).commit();
            } else if (value instanceof Boolean){
                getEditor().putBoolean(key, (Boolean) value).commit();
            } else if (value instanceof Long){
                getEditor().putLong(key, (Long) value).commit();
            } else if (value instanceof Set) {
                getEditor().putStringSet(key, (Set<String>) value).commit();
            } else {
                throw new UnknownError("Unknown object");
            }
        }

        /**********************************************************************************************/
        /**********************************************************************************************/
        /*************************************** READ DATA *******************************************/
        /**********************************************************************************************/
        /**********************************************************************************************/

        //Read String
        public String get(String key, String defaultValue){
            return sharedPreferences.getString(key, defaultValue);
        }

        public String getString(String key){
            return get(key, "");
        }

        //Read Integer
        public Integer get(String key, Integer defaultValue){
            return sharedPreferences.getInt(key, defaultValue);
        }

        public Integer getInteger(String key){
            return get(key, 0);
        }

        //Read Float
        public Float get(String key, Float defaultValue){
            return sharedPreferences.getFloat(key, defaultValue);
        }

        public Float getFloat(String key){
            return get(key, 0f);
        }

        //Read Boolean
        public Boolean get(String key, Boolean defaultValue){
            return sharedPreferences.getBoolean(key, defaultValue);
        }

        public Boolean getBoolean(String key){
            return get(key, false);
        }

        //Read Long
        public Long get(String key, Long defaultValue){
            return sharedPreferences.getLong(key, defaultValue);
        }

        public Long getLong(String key){
            return get(key, 0L);
        }

        //Read Set<String>
        public Set<String> get(String key, Set<String> defaultValue){
            return sharedPreferences.getStringSet(key, defaultValue);
        }

        public Set<String> getStringSet(String key){
            return get(key, new TreeSet<String>());
        }


        //FILES
        public String getFilePath(){
            return sharedPreferences.getString(LAST_FILE_SAVE_PATH, "not_found");
        }

        public String getFilePathFromKey(String key){
            return sharedPreferences.getString(key, "not_found");
        }
    }

    public static class FilesValue extends ContextWrapper {

        private static SharedPreferences sharedPreferences;
        private static String savePath;
        private static String saveExtension;
        private static Boolean isCache;
        private static EasyStoreFilesCallback easyStoreFilesCallback;

        private File savedFile;
        private String key;

        public static FilesValue from(EasyStoreBuilder easyStoreBuilder, String savePath, String saveExtension, Boolean isCache, EasyStoreFilesCallback easyStoreFilesCallback){
            return new FilesValue(easyStoreBuilder, savePath, saveExtension, isCache, easyStoreFilesCallback);
        }

        private FilesValue(EasyStoreBuilder easyStoreBuilder, String getSavePath, String getSaveExtension, Boolean getIsCache, EasyStoreFilesCallback getEasyStoreFilesCallback){
            super(easyStoreBuilder.getContext());
            sharedPreferences = easyStoreBuilder.getSharedPreferences();
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
                            getEditor().putString(LAST_FILE_SAVE_PATH, savedFile.getAbsolutePath()).commit();
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
                    getEditor().putString(LAST_FILE_SAVE_PATH, savedFile.getAbsolutePath()).commit();
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
                            getEditor().putString(LAST_FILE_SAVE_PATH, savedFile.getAbsolutePath()).commit();
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
                            getEditor().putString(LAST_FILE_SAVE_PATH, savedFile.getAbsolutePath()).commit();
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
}
