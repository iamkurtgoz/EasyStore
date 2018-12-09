# Easy Preference


## What Works

##### Simple, fast library that further accelerates the use of SharedPreferences.

### How to Import
##### Step 1. Add the JitPack repository to your build file
```java
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

##### Step 2. Add the dependency
```java
dependencies {
    implementation 'com.github.iamkurtgoz:Easy-Preference:1.0'
}
```
[![](https://jitpack.io/v/iamkurtgoz/Easy-Preference.svg)](https://jitpack.io/#iamkurtgoz/Easy-Preference)

##### Step 3. Add to Application
```java
public class SampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        EasyPreference.with(getApplicationContext(), "mypreference", PreferenceMode.MODE_PRIVATE).create();
    }
}
```
##### Step 4. Add to Manifest
```xml
<application
    android:name=".SampleApp"
    ----
</application>
```
## What Works
##### Way 1
```java
//write
EasyPreference.writeString(ContactsPreference.TOKEN_ID, tokenId);
EasyPreference.writeString(ContactsPreference.NAME, name);
EasyPreference.writeInteger(ContactsPreference.AGE, age);
EasyPreference.writeBoolean(ContactsPreference.IS_STUDENT, isStudent);

//read
String token = EasyPreference.readString(ContactsPreference.TOKEN_ID);
String name = EasyPreference.readString(ContactsPreference.NAME);
int age = EasyPreference.readInteger(ContactsPreference.AGE);
boolean is_student = EasyPreference.readBoolean(ContactsPreference.IS_STUDENT);
```

##### Way 2
```java
//write
EasyPreference.writeObject(ContactsPreference.TOKEN_ID, tokenId);
EasyPreference.writeObject(ContactsPreference.NAME, name);
EasyPreference.writeObject(ContactsPreference.AGE, age);
EasyPreference.writeObject(ContactsPreference.IS_STUDENT, isStudent);

//read
//using default value
String token = EasyPreference.readString(ContactsPreference.TOKEN_ID, "default token");
String name = EasyPreference.readString(ContactsPreference.NAME, "default name");
int age = EasyPreference.readInteger(ContactsPreference.AGE, -1);
boolean is_student = EasyPreference.readBoolean(ContactsPreference.IS_STUDENT, false);
```

##### Way 3
```java
//write
EasyModel[] easyModels = new EasyModel[]{
new EasyModel(ContactsPreference.TOKEN_ID, tokenId),
new EasyModel(ContactsPreference.NAME, name),
new EasyModel(ContactsPreference.AGE, age),
new EasyModel(ContactsPreference.IS_STUDENT, isStudent)
};
EasyPreference.writeObjects(easyModels);

//read
//using default value
String token = EasyPreference.readString(ContactsPreference.TOKEN_ID, "default token");
String name = EasyPreference.readString(ContactsPreference.NAME, "default name");
int age = EasyPreference.readInteger(ContactsPreference.AGE, -1);
boolean is_student = EasyPreference.readBoolean(ContactsPreference.IS_STUDENT, false);
```

# CONTACT : kurtgozmehmet159@gmail.com
