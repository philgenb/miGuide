package de.diakonie.miguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DataManager {


    private StartActivity mainactivity;

    public DataManager(StartActivity mainactivity) {
        this.mainactivity = mainactivity;
    }

    public void setStringValue(String key, String stringValue) {
        Context context = mainactivity.getBaseContext();
        SharedPreferences sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedPref.edit();

        edit.putString(key, stringValue);
        edit.commit();
    }

    public String getStringValue(String key) {
        Context context = mainactivity.getBaseContext();
        SharedPreferences sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        String value = sharedPref.getString(key, "");
        return value;
    }

    public void removeValue(String key) {
        Context context = mainactivity.getBaseContext();
        SharedPreferences sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedPref.edit();

        edit.remove(key);
        edit.commit();
    }

    public Boolean isValueSet(String key) {
        Context context = mainactivity.getBaseContext();
        SharedPreferences sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        return sharedPref.contains(key);
    }

}
