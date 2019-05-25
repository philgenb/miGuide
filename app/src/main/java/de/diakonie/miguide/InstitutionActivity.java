package de.diakonie.miguide;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Locale;

import de.diakonie.miguide.CategoryActivity.Category;
import static de.diakonie.miguide.CategoryActivity.CATEGORIES;

import static de.diakonie.miguide.CSVReader.KNOWN_INSTITUTIONS;

public class InstitutionActivity extends AppCompatActivity {

    static class InstitutionInfo {
        String name;
        int imageID;
    }

    public static ArrayList<InstitutionInfo> institutions = new ArrayList<>();
/*
    {
        for (int i = 0; i < 10; i++) {
            addInstitution("Einrichtung " + i, R.drawable.ic_logo_miguide);
        }
    }
*/
    public void addInstitution(String name, int imageID) {
        InstitutionInfo institution = new InstitutionInfo();
        institution.name = name;
        institution.imageID = imageID;

        institutions.add(institution);
    }


    //----------------

    RecyclerView recyclerview;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager recyclerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        parseIncomingIntent();


        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(recyclerLayoutManager);

        //Dekoration
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerview.addItemDecoration(itemDecoration);

        recyclerAdapter = new InstitutionViewAdapter(getBaseContext(), institutions);
        recyclerview.setAdapter(recyclerAdapter);
    }

    private void parseIncomingIntent() {
        int categoryID = getIntent().getIntExtra("categoryID", -1);
        if (categoryID == -1) {
            Log.e("de.diakonie.miguide.InstitutionActivity", "categoryID not defined");
            return;
        }

        registerToolBar(categoryID);
        loadArrays(categoryID);
    }

    private void loadArrays(int categoryID) {
        Category category = CATEGORIES.get(categoryID);


        //ArrayList<Institution> foundInstitutions = new ArrayList<Institution>();
        institutions.clear();
        for (Institution institution : KNOWN_INSTITUTIONS) {
            //Log.i("InstitutionActivity - Compare Inst", "categoryName: " + institution.Kategorie + " GermanString: " + getStringinGerman(category.nameID));
            Log.i("InstitutionActivity", "CategoryString: " + getResources().getString(category.nameID));
            if (institution.Kategorie.contains(getStringinGerman(category.nameID))) {
                addInstitution(institution.Name , R.drawable.ic_missing_image);
            }
        }
        /*
        institutions.clear();
        for (int i = 0; i < 10; i++) {
            addInstitution(getString(category.nameID) + " " + i, R.drawable.ic_logo_miguide);
        }
        */
    }

    public String getStringinGerman(int StringID) {
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        Locale savedLocale = conf.locale;
        conf.locale = Locale.GERMAN;
        res.updateConfiguration(conf, null);

        String str = res.getString(StringID);

        conf.locale = savedLocale;
        res.updateConfiguration(conf, null);
        return str;
    }

    // Setzt die Actionbar
    public void registerToolBar(int categoryID) {
        Category category = CATEGORIES.get(categoryID);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(category.nameID);
        setSupportActionBar(toolbar);
    }

    // Fügt der Actionbar das Menü hinzu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);
        return true;
    }

    //Actionbar - Wenn Optionen ausgewählt -> Sprachauswahlfenster
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item1) {
            Intent intent = new Intent(InstitutionActivity.this, LanguageSelectorActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


    //Zurücktaste -> Öffne Kategorienmenü
    /*
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InstitutionActivity.this, CategoryActivity.class);
        startActivity(intent);
        //overridePendingTransition(R.anim.lefttoright, 0); //Keine Animation
    }
    */
}

