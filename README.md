# EasyStore
## Simple, fast library that further accelerates the use of SharedPreferences.

[![](https://jitpack.io/v/iamkurtgoz/EasyStore.svg)](https://jitpack.io/#iamkurtgoz/EasyStore)

## Setup
## Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
## Step 2. Add the dependency
```
dependencies {
    implementation 'com.github.iamkurtgoz:EasyStore:2.2'
}
```
## Step 3. Add read permission - Optional(for save file)
```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```
## Step 4. Add to Application
```
public class SampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        EasyStore.getInstance().init(getApplicationContext(), "mypreference", EasyStoreMode.MODE_PRIVATE);
        //Or crypt mode
        EasyStore.getInstance().init(getApplicationContext(), "mypreference", EasyStoreMode.MODE_PRIVATE, true, "crypt password");
    }
}
```
## Step 5. Set Value - MultiSet
### Variables example
```
//Variables
String name = "Mehmet Kurtgöz";
int age = 22;
float weight = 80.5f;
long total_days = 15L;
boolean is_developer = true;

Set<String> stringSet = new TreeSet<String>();
stringSet.add("Mehmet");
stringSet.add("Kurtgöz");
```
### And save data example
```
EasyStore.use().set("NAME", name); //String set
EasyStore.use().set("AGE", age); //Integer set
EasyStore.use().set("WEIGHT", weight); //Float set
EasyStore.use().set("TOTAL_DAYS", total_days); //Long set
EasyStore.use().set("IS_DEVELOPER", is_developer); //Boolean set
EasyStore.use().set("KEY", stringSet); //Set<String> set

//OR multi set
EasyStore.use().set(
    new EasyModel("NAME", name),
    new EasyModel("AGE", age)
    ..
);
```

## Step 6. Read Value
### Read String
```
//Get String - Default 'not_found'
name = EasyStore.use().get("NAME", "not_found");

//OR Default ''
name = EasyStore.use().getString("NAME");
```

### Read Integer 
```
//Get Integer - Default '22'
age = EasyStore.use().get("AGE", 22);

//OR Default '0'
age = EasyStore.use().getInteger("AGE");
```

### Read Float 
```
//Get Float - Default 'not_found'
weight = EasyStore.use().get("WEIGHT", 80.5f);

//OR Default '0f'
weight = EasyStore.use().getFloat("WEIGHT");
```

### Read Long 
```
//Get Long - Default '15L'
total_days = EasyStore.use().get("TOTAL_DAYS", 15L);

//OR Default '0L'
total_days = EasyStore.use().getLong("TOTAL_DAYS");
```

### Read Boolean 
```
//Get Boolean - Default 'false'
is_developer = EasyStore.use().get("IS_DEVELOPER", false);

//OR Default 'false'
is_developer = EasyStore.use().getBoolean("IS_DEVELOPER");
```

### Read Set<String> 
```
//Get Set<String> - Default 'new TreeSet<String>() - empty set'
stringSet = EasyStore.use().get("KEY", new TreeSet<String>());

//OR Default 'new TreeSet<String>() - empty set'
stringSet = EasyStore.use().getStringSet("KEY");
```

## Step 7. Save - Download File
```
//Save file to cache.. File ext = 'png', callback = this : EasyStoreFilesCallback and saveFile : url, bytes[], file or path
EasyStore.useCacheFile("png", this).saveFile(FILE_URL);
EasyStore.useCacheFile("png", this).saveFile(bytes);
EasyStore.useCacheFile("png", this).saveFile(file);
EasyStore.useCacheFile("png", this).saveFile(path);

//OR
//Save file to external..savePath = 'getDcimDirectoyPath', File ext = 'png', callback = this : EasyStoreFilesCallback and saveFile : url, bytes[], file or path
EasyStore.useFile(getDcimDirectoyPath, "png", this).saveFile(FILE_URL);
EasyStore.useFile(getDcimDirectoyPath, "png", this).saveFile(bytes);
EasyStore.useFile(getDcimDirectoyPath, "png", this).saveFile(file);
EasyStore.useFile(getDcimDirectoyPath, "png", this).saveFile(path);
```

### Step 8. Read - Get File Path
### There are 2 types of reading.
### 1 : Last download - save file path
### 2 : Encrypted code generated at the time of saving - downloading.

### ** HOW TO GET ENCRYPTED CODE **
```
@Override
public void onSuccess(String filePath, String saveKey) {
    //save key is encrypted code
    String path = EasyStore.use().getFilePathFromKey(saveKey)
    //NOTE : Actually 'filePath' and 'path' are the same.
}
```
### AND
```
//Last download - save file path
String path = EasyStore.use().getFilePath();
```
## Contact : iamkurtgoz@gmail.com
