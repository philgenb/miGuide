package de.diakonie.miguide;

import android.content.Context;
import android.content.SharedPreferences;

public class DataManager {


    private LanguageSelectorActivity mainactivity;

    public DataManager(LanguageSelectorActivity mainactivity) {
        this.mainactivity = mainactivity;
    }

    //---------------- AppSlider Intro FirstStart Information -------------------

    public void setfirstStart(boolean status) {
        Context context = mainactivity.getBaseContext();
        SharedPreferences sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedPref.edit();

        edit.putBoolean("firststart", status);
        edit.commit();
    }

    public boolean wasAppStarted () {
        Context context = mainactivity.getBaseContext();
        SharedPreferences sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        boolean status = sharedPref.getBoolean("firststart", false);
        return status;
    }
    //--------------------------------------


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
