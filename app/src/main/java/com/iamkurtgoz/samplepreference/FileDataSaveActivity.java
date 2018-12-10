package com.iamkurtgoz.samplepreference;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iamkurtgoz.easypreference.EasyPreference;
import com.iamkurtgoz.easypreference.FilesCallBack;
import com.iamkurtgoz.easypreference.files.EasyPreferenceError;

import java.util.ArrayList;

public class FileDataSaveActivity extends AppCompatActivity {

    private ArrayList<String> arrayModel = new ArrayList<>();

    private CheckBox checkBoxCache;
    private Button btnDownloadUrl, btnSaveResource, btnCopyKey;
    private ProgressBar progressBar;
    private ImageView imageView;
    private ListView listviewValue;

    private String key = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_data_save);
        init();
        registerHandlers();
    }

    private void init(){
        checkBoxCache = (CheckBox) findViewById(R.id.checkBoxCache);
        btnDownloadUrl = (Button) findViewById(R.id.btnDownloadUrl);
        btnSaveResource = (Button) findViewById(R.id.btnSaveResource);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageView = (ImageView) findViewById(R.id.imageView);
        listviewValue = (ListView) findViewById(R.id.listviewValue);
        btnCopyKey = (Button) findViewById(R.id.btnCopyKey);
    }

    private void registerHandlers(){
        setBtnDownloadUrlClick();
        setBtnSaveResourceClick();
        setBtnCopyKeyClick();
    }

    private void setBtnDownloadUrlClick(){
        btnDownloadUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCopyKey.setVisibility(View.INVISIBLE);
                if (arrayModel.size()>0){
                    arrayModel.clear();
                }
                if (checkBoxCache.isChecked()){
                    EasyPreference.FileToCache("png").saveFile(ContactsPreference.PICTURE_URL_1, filesCallBack);
                } else {
                    EasyPreference.FileToExternalStorage(getDcimDirectory(),"png").saveFile(ContactsPreference.PICTURE_URL_1, filesCallBack);
                }
            }
        });
    }

    private void setBtnSaveResourceClick(){
        btnSaveResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCopyKey.setVisibility(View.INVISIBLE);
                if (arrayModel.size()>0){
                    arrayModel.clear();
                }
                Bitmap stinson = BitmapFactory.decodeResource(getResources(),R.drawable.barney);
                if (checkBoxCache.isChecked()){
                    EasyPreference.FileToCache("png").saveFile("stinsonresource", stinson, filesCallBack);
                } else {
                    EasyPreference.FileToExternalStorage(getDcimDirectory(),"png").saveFile("stinsonresource", stinson, filesCallBack);
                }
            }
        });
    }

    private void setBtnCopyKeyClick(){
        btnCopyKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied text", key);
                if (clipboard != null) {
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(FileDataSaveActivity.this, "Copied text", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getDcimDirectory(){
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
    }

    FilesCallBack filesCallBack = new FilesCallBack() {
        @Override
        public void onStartDownload() {
            arrayModel.add("Start Download");
            addListData();

        }

        @Override
        public void onError(int errorCode) {
            String errorMessage = "";
            if (errorCode == EasyPreferenceError.ERROR_READ){
                errorMessage = "Read error.";
            } else if (errorCode == EasyPreferenceError.ERROR_WRITE){
                errorMessage = "Write error";
            } else if (errorCode == EasyPreferenceError.ERROR_PERMISSION){
                errorMessage = "Permission error";
            }
            arrayModel.add(errorMessage);
            addListData();

        }

        @Override
        public void onProgress(int percent) {
            arrayModel.add("Progress : %" + percent);
            progressBar.setProgress(percent);
            addListData();

        }

        @Override
        public void onSuccess(String filePath, String saveKey) {
            arrayModel.add(filePath + " - KEY : " + saveKey);
            addListData();
            progressBar.setProgress(100);
            key = saveKey;
            btnCopyKey.setVisibility(View.VISIBLE);

            Glide.with(FileDataSaveActivity.this)
                    .load(filePath)
                    .into(imageView);
        }
    };

    private void addListData(){
        listviewValue.setAdapter(new ArrayAdapter<String>(FileDataSaveActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayModel));
    }
}
