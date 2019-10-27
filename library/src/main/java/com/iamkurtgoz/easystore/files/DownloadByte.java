package com.iamkurtgoz.easystore.files;

import android.os.AsyncTask;

import com.iamkurtgoz.easystore.files.DownloadFilesCallBack;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadByte extends AsyncTask<Void, Integer,byte[]> {

    private String fileUrl;
    private DownloadFilesCallBack byteCallBack;

    public DownloadByte(String fileUrl, DownloadFilesCallBack byteCallBack){
        this.fileUrl = fileUrl;
        this.byteCallBack = byteCallBack;
    }

    @Override
    protected byte[] doInBackground(Void... voids) {
        if (fileUrl == null || fileUrl.equalsIgnoreCase("")){
            return new byte[0];
        } else {
            try {
                URL url = new URL(fileUrl);
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                int responseCode = httpConn.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    int contentLength = httpConn.getContentLength();
                    InputStream inputStream = httpConn.getInputStream();
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream(contentLength);

                    int bytesRead = -1, totalBytesRead = -1;
                    byte[] data = new byte[1024];
                    while ((bytesRead = inputStream.read(data)) != -1) {
                        if (isCancelled()) {
                            break;
                        }
                        buffer.write(data, 0, bytesRead);
                        totalBytesRead += bytesRead;
                        int progress = (int) (totalBytesRead * (100 / (double) contentLength));
                        publishProgress(progress);
                    }

                    buffer.close();
                    inputStream.close();
                    httpConn.disconnect();
                    return buffer.toByteArray();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            } catch (OutOfMemoryError ex){
                ex.printStackTrace();
                return new byte[0];
            }
            return new byte[0];
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        byteCallBack.onProgress(values[0]);
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        super.onPostExecute(bytes);
        byteCallBack.onResultBytes(bytes);
    }
}
