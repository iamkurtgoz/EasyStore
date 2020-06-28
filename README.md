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
Kotlin
dependencies {
    implementation 'com.github.iamkurtgoz:EasyStore:3.1'
}

For Java Version:
dependencies {
    implementation 'com.github.iamkurtgoz:EasyStore:2.2'
}
```
## Step 3. Add to Application
```
public class SampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        EasyStoreBuilder(getApplicationContext(), "mypreference", EasyStoreMode.MODE_PRIVATE);
    }
}
```
## Step 4. Set Value - MultiSet
### Variables example
```
//Variables
val name = "Mehmet Kurtgöz"
val age = 22
val weight = 80.5f
val total_days = 15L
val is_developer = true
```
### And save string
```
name.save("Name")
//or
"Mehmet Kurtgöz".save("Name")
//or
EasyStore.save("Name", name)
```
### And save int
```
age.save("AGE")
//or
22.save("AGE")
//or
EasyStore.save("AGE", age)
```
### And save float
```
weight.save("WEIGHT")
//or
80f.save("WEIGHT")
//or
EasyStore.save("WEIGHT", weight)
```
### And save long
```
total_days.save("TOTAL_DAYS")
//or
15L.save("TOTAL_DAYS")
//or
EasyStore.save("TOTAL_DAYS", total_days)
```
### And save boolean
```
is_developer.save("DEVELOPER")
//or
true.save("DEVELOPER")
//or
EasyStore.save("DEVELOPER", is_developer)
```
### And save list
```
listOf(
        EasyModel("NAME", name),
        EasyModel("AGE", age),
        EasyModel("WEIGHT", weight),
        EasyModel("TOTAL_DAYS", total_days),
        EasyModel("DEVELOPER", is_developer)
).save()
```

## Step 5. Read Value
```
Log.d("MyLog", "NAME: ${EasyStore.readString("NAME")}")
Log.d("MyLog", "AGE: ${EasyStore.readInt("AGE")}")
Log.d("MyLog", "WEIGHT: ${EasyStore.readFloat("WEIGHT")}")
Log.d("MyLog", "TOTAL_DAYS: ${EasyStore.readLong("TOTAL_DAYS")}")
Log.d("MyLog", "DEVELOPER: ${EasyStore.readBoolean("DEVELOPER")}")
```

## Step 6. Exist Control Value
```
val nameExist = EasyStore.existString("NAME")
val ageExist = EasyStore.existString("AGE")
val weightExist = EasyStore.existString("WEIGHT")
val totalDaysExist = EasyStore.existString("TOTAL_DAYS")
val developerExist = EasyStore.existString("DEVELOPER")
```
## Contact : iamkurtgoz@gmail.com
