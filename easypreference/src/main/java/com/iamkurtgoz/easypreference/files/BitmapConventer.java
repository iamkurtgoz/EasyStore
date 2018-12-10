package com.iamkurtgoz.easypreference.files;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;

public class BitmapConventer extends AsyncTask<byte[],byte[],byte[]> {

    private Bitmap bitmap;
    private ByteCallBack byteCallBack;

    public BitmapConventer(Bitmap bitmap, ByteCallBack byteCallBack){
        this.bitmap = bitmap;
        this.byteCallBack = byteCallBack;
    }

    @Override
    protected byte[] doInBackground(byte[]... bytes) {
        if (bitmap == null){
            return new byte[0];
        } else {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] byteArray = outputStream.toByteArray();
            bitmap.recycle();
            return byteArray;
        }
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        super.onPostExecute(bytes);
        byteCallBack.onResultBytes(bytes);
    }

}
