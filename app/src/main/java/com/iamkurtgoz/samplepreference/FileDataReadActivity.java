package com.iamkurtgoz.samplepreference;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;



import com.bumptech.glide.Glide;
import com.iamkurtgoz.easypreference.EasyPreference;
import com.iamkurtgoz.easystore.EasyStore;

public class FileDataReadActivity extends AppCompatActivity {

    private EditText editCopyKey;
    private Button btnReadKey;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_data_read);
        init();
        registerHandlers();
    }

    private void init(){
        editCopyKey = (EditText) findViewById(R.id.editCopyKey);
        btnReadKey = (Button) findViewById(R.id.btnReadKey);
        imgView = (ImageView) findViewById(R.id.imgView);
    }

    private void registerHandlers(){
        setBtnReadKeyClick();
    }

    private void setBtnReadKeyClick(){
        btnReadKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = editCopyKey.getText().toString();
                String imgPath = "";
                if (!key.matches("") && EasyStore.use().hasExistStringKey(key)){
                    imgPath = EasyStore.use().getFilePathFromKey(key);
                } else {
                    imgPath = EasyStore.use().getFilePath();
                }

                Glide.with(FileDataReadActivity.this)
                        .load(imgPath)
                        .into(imgView);
            }
        });
    }
}
