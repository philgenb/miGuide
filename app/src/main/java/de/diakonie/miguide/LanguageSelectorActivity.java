package de.diakonie.miguide;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class LanguageSelectorActivity extends AppCompatActivity {

    DataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languageselector);

        registerToolBar();

        this.dm = new DataManager(this);

        if(dm.isValueSet("locale")) {
            // setLocale(dm.getStringValue("locale"));
            Log.i("locale", "Locale already Set!");
            Log.i("locale", "Locale found: " + dm.getStringValue("locale"));
        }

        Button DButton = findViewById(R.id.DButton);
        DButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("de");
                Log.i("locale", dm.getStringValue("locale"));
                openCategoryMenu();
            }
        });

        Button EButton = findViewById(R.id.EButton);
        EButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("en");
                Log.i("locale", dm.getStringValue("locale"));
                openCategoryMenu();
            }
        });

    }

    private void openCategoryMenu() {
        Intent intent = new Intent(LanguageSelectorActivity.this, CategoryActivity.class);
        startActivity(intent);
    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        dm.setStringValue("locale", lang);
        recreate();
    }

    public void registerToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
