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

public class CategoryActivity extends AppCompatActivity {


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

        registerToolBar();

        loadArrays();

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(recyclerLayoutManager);

        recyclerAdapter = new RecyclerViewAdapter(categorys, itemimages, getBaseContext());
        recyclerview.setAdapter(recyclerAdapter);
    }

    private void loadArrays() {
        if(categorys.isEmpty()) {
            categorys.add("Lebensmittel");
            categorys.add("Bekleidung");
            categorys.add("Freizeit");
            categorys.add("Kind & Baby");
            categorys.add("Möbel");
            categorys.add("Haushalt");
            categorys.add("Diverses");
        }
        if(itemimages.isEmpty()) {
            itemimages.add(R.drawable.ic_einkaufen);

            itemimages.add(R.drawable.ic_bekleidung);
            itemimages.add(R.drawable.ic_freizeit);
            itemimages.add(R.drawable.ic_logo_miguide);
            itemimages.add(R.drawable.ic_mobel);
            itemimages.add(R.drawable.ic_logo_miguide);
            itemimages.add(R.drawable.ic_logo_miguide);
        }
    }
    //setzt die Actionbar
    public void registerToolBar() {
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
            Intent intent = new Intent(CategoryActivity.this, LanguageSelectorActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


    //Blockiert die Zurücktaste im Kategorienmenü
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "exit", Toast.LENGTH_SHORT);
    }
}
