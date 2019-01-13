package de.diakonie.miguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class InstitutionActivity extends AppCompatActivity {

    public ArrayList<String> categorys = new ArrayList<>();
    public ArrayList<Integer> itemimages = new ArrayList<>();

    //----------------

    RecyclerView recyclerview;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager recyclerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);



        getIncomingIntent();

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(recyclerLayoutManager);

        recyclerAdapter = new RecyclerViewAdapter(categorys, itemimages, getBaseContext());
        recyclerview.setAdapter(recyclerAdapter);
    }

    private void getIncomingIntent() {
        if(getIntent().hasExtra("category")) {
            String category = getIntent().getStringExtra("category");
            registerToolBar(category);
            loadArrays(category);
        }
    }

    private void loadArrays(String category) {
        if(categorys.isEmpty()) {
            categorys.add("Einrichtung 1");
            categorys.add("Einrichtung 2");
            categorys.add("Einrichtung 3");
        }
        if(itemimages.isEmpty()) {
            itemimages.add(R.drawable.ic_logo_miguide);
            itemimages.add(R.drawable.ic_logo_miguide);
            itemimages.add(R.drawable.ic_logo_miguide);
        }
    }
    //setzt die Actionbar
    public void registerToolBar(String category) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.categories);
        setSupportActionBar(toolbar);
    }

    //fügt der Actionbar das Menü hinzu
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

