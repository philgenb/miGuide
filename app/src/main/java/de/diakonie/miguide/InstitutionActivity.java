package de.diakonie.miguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import de.diakonie.miguide.CategoryActivity.Category;
import static de.diakonie.miguide.CategoryActivity.CATEGORIES;

public class InstitutionActivity extends AppCompatActivity {

    static class Institution {
        String name;
        int imageID;
    }

    public ArrayList<Institution> institutions = new ArrayList<>();
    {
        for (int i = 0; i < 10; i++) {
            addInstitution("Einrichtung " + i, R.drawable.ic_logo_miguide);
        }
    }

    public void addInstitution(String name, int imageID) {
        Institution institution = new Institution();
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

        institutions.clear();
        for (int i = 0; i < 10; i++) {
            addInstitution(getString(category.nameID) + " " + i, R.drawable.ic_logo_miguide);
        }
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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InstitutionActivity.this, CategoryActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0); //Keine Animation
    }
}

