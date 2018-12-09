package com.iamkurtgoz.samplepreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.iamkurtgoz.easypreference.EasyModel;
import com.iamkurtgoz.easypreference.EasyPreference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //values
    String tokenId = "mytokenid";
    String name = "Mehmet Kurtgöz";
    int age = 21;
    boolean isStudent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //way 1
        way1();

        //way2
        way2();

        //way3
        way3();
    }

    private void way1(){
        EasyPreference.writeString(ContactsPreference.TOKEN_ID, tokenId);
        EasyPreference.writeString(ContactsPreference.NAME, name);
        EasyPreference.writeInteger(ContactsPreference.AGE, age);
        EasyPreference.writeBoolean(ContactsPreference.IS_STUDENT, isStudent);

        String token = EasyPreference.readString(ContactsPreference.TOKEN_ID);
        String name = EasyPreference.readString(ContactsPreference.NAME);
        int age = EasyPreference.readInteger(ContactsPreference.AGE);
        boolean is_student = EasyPreference.readBoolean(ContactsPreference.IS_STUDENT);

        writeLog("Way1 Read Token : " + token);
        writeLog("Way1 Read Name : " + name);
        writeLog("Way1 Read Age : " + age);
        writeLog("Way1 Read is student : " + (is_student ? "Student" : "Not Student")); //default value
    }

    private void way2(){
        EasyPreference.writeObject(ContactsPreference.TOKEN_ID, tokenId);
        EasyPreference.writeObject(ContactsPreference.NAME, name);
        EasyPreference.writeObject(ContactsPreference.AGE, age);
        EasyPreference.writeObject(ContactsPreference.IS_STUDENT, isStudent);

        //using default value
        String token = EasyPreference.readString(ContactsPreference.TOKEN_ID, "default token");
        String name = EasyPreference.readString(ContactsPreference.NAME, "default name");
        int age = EasyPreference.readInteger(ContactsPreference.AGE, -1);
        boolean is_student = EasyPreference.readBoolean(ContactsPreference.IS_STUDENT, false);

        writeLog("Way1 Read Token : " + token);
        writeLog("Way1 Read Name : " + name);
        writeLog("Way1 Read Age : " + age);
        writeLog("Way1 Read is student : " + (is_student ? "Student" : "Not Student")); //default value
    }

    private void way3(){
        EasyModel[] easyModels = new EasyModel[]{
                new EasyModel(ContactsPreference.TOKEN_ID, tokenId),
                new EasyModel(ContactsPreference.NAME, name),
                new EasyModel(ContactsPreference.AGE, age),
                new EasyModel(ContactsPreference.IS_STUDENT, isStudent)
        };
        EasyPreference.writeObjects(easyModels);

        //using default value
        String token = EasyPreference.readString(ContactsPreference.TOKEN_ID, "default token");
        String name = EasyPreference.readString(ContactsPreference.NAME, "default name");
        int age = EasyPreference.readInteger(ContactsPreference.AGE, -1);
        boolean is_student = EasyPreference.readBoolean(ContactsPreference.IS_STUDENT, false);

        writeLog("Way1 Read Token : " + token);
        writeLog("Way1 Read Name : " + name);
        writeLog("Way1 Read Age : " + age);
        writeLog("Way1 Read is student : " + (is_student ? "Student" : "Not Student")); //default value
    }


    private void writeLog(String value){
        Log.d("MyLog", value);
    }
}
