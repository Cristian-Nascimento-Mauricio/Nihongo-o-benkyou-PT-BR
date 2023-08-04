package com.example.nihongoobenkyou.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.nihongoobenkyou.DataBase.AppDataBase;

public class SharedPreferencesManager {

    private  SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    private  Context context;
    public SharedPreferencesManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("SharedPrefences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


    }

    public String AppVersion() {

        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

       return packageInfo.versionName;
    }


    public String LastAppVersion(){
        float version = Float.parseFloat(AppVersion());
        float LastVersion = Float.parseFloat(sharedPreferences.getString("LastAppVersion","0.1"));

        if(version > LastVersion){
            editor.putString("LastAppVersion",AppVersion());
            editor.apply();
        }

        return Float.toString(LastVersion);
    }

    public Boolean DatabaseInstall(){

        return sharedPreferences.getBoolean("DataBaseInstall",false);
    }
    public void installOfDatabase(){
        editor.putBoolean("DataBaseInstall",true);
        editor.apply();
    }


}
