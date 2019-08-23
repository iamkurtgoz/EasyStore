package com.iamkurtgoz.easypreference.files;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
@Deprecated
public class FileConventer extends AsyncTask<Void,byte[],byte[]> {

    private File file;
    private ByteCallBack byteCallBack;

    public FileConventer(File file, ByteCallBack byteCallBack){
        this.file = file;
        this.byteCallBack = byteCallBack;
    }

    @Override
    protected byte[] doInBackground(Void... voids) {
        if (file == null || !file.exists()){
            return new byte[0];
        } else {
            int size = (int) file.length();
            byte[] bytes = new byte[size];
            try {
                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                buf.read(bytes, 0, bytes.length);
                buf.close();
                return bytes;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new byte[0];
            } catch (IOException e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        super.onPostExecute(bytes);
        byteCallBack.onResultBytes(bytes);
    }
}
