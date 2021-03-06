package de.diakonie.miguide;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class LanguageSelectorActivity extends AppCompatActivity {

    private DataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languageselector);

        registerToolBar();

        this.dm = new DataManager(this);

        checkFirstStart(dm);


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

        Button AButton = findViewById(R.id.AButton);
        AButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("ar");
                Log.i("locale", dm.getStringValue("locale"));
                openCategoryMenu();
            }
        });

        Button CreditsButton = findViewById(R.id.creditsButton);
        CreditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LanguageSelectorActivity.this, CreditsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void openCategoryMenu() {
        Intent intent = new Intent(LanguageSelectorActivity.this, CategoryActivity.class);
        startActivity(intent);
    }

    private void checkFirstStart(DataManager dm) {
        //dm.setfirstStart(false); //TESTING
        /*
        if(!dm.wasAppStarted()) {
            Log.i("FirstStart", "First Start detected... Loading AppSliderIntro");
            Intent intent = new Intent(LanguageSelectorActivity.this, CleverIntro.class);
            dm.setfirstStart(true);
            startActivity(intent);
        }
        */
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
