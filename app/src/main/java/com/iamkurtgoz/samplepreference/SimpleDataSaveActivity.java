package com.iamkurtgoz.samplepreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iamkurtgoz.easypreference.EasyPreference;

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

        editName.setText("");
        editAge.setText("0");
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
                EasyPreference.writeObject(ContactsPreference.NAME, name);
                EasyPreference.writeObject(ContactsPreference.AGE, age);

                editName.setText("");
                editAge.setText("0");
            }
        });
    }

    private void setBtnReadTextClick(){
        btnReadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = EasyPreference.readString(ContactsPreference.NAME, "not found");
                int age = EasyPreference.readInteger(ContactsPreference.AGE, -1);
                editName.setText(name);
                editAge.setText(String.valueOf(age));
            }
        });
    }
}
