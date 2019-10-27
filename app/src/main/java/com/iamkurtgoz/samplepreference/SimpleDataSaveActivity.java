package com.iamkurtgoz.samplepreference;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.iamkurtgoz.easystore.EasyStore;

public class SimpleDataSaveActivity extends AppCompatActivity {

    private EditText editName, editAge;
    private Button btnSaveText, btnReadText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_data_save);
        init();
        registerHandlers();
    }

    private void init(){
        editName = (EditText) findViewById(R.id.editName);
        editAge = (EditText) findViewById(R.id.editAge);
        btnSaveText = (Button) findViewById(R.id.btnSaveText);
        btnReadText = (Button) findViewById(R.id.btnReadText);

        String name = EasyStore.use().get(Const.CONST_NAME, "not found");
        int age = EasyStore.use().get(Const.CONST_AGE, -1);
        editName.setText(name);
        editAge.setText(String.valueOf(age));
    }

    private void registerHandlers(){
        setBtnSaveTextClick();
        setBtnReadTextClick();
    }

    private void setBtnSaveTextClick(){
        btnSaveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                int age = Integer.parseInt(editAge.getText().toString());
                EasyStore.use().set(Const.CONST_NAME, name);
                EasyStore.use().set(Const.CONST_AGE, age);
            }
        });
    }

    private void setBtnReadTextClick(){
        btnReadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = EasyStore.use().get(Const.CONST_NAME, "not found");
                int age = EasyStore.use().get(Const.CONST_AGE, -1);
                editName.setText(name);
                editAge.setText(String.valueOf(age));
            }
        });
    }
}
