package de.diakonie.miguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    public static class Category {
        int nameID;
        int imageID;
    }

    public static final ArrayList<Category> CATEGORIES = new ArrayList<>();
    static {
        loadArrays();
    }

    //----------------

    private RecyclerView recyclerview;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;

    public CategoryActivity() {
    }

    private static void loadArrays() {
        int imagePlaceholder = R.drawable.ic_logo_miguide;

        addCategory(R.string.Lebensmittel, R.drawable.ic_einkaufen);
        addCategory(R.string.Bekleidung, R.drawable.ic_bekleidung);
        addCategory(R.string.Freizeit, R.drawable.ic_freizeit);
        addCategory(R.string.Kind, imagePlaceholder);
        addCategory(R.string.Möbel, R.drawable.ic_mobel);
        addCategory(R.string.Haushalt, imagePlaceholder);
        addCategory(R.string.Diverses, imagePlaceholder);
    }

    private static void addCategory(int nameID, int imageID) {
        Category category = new Category();
        category.nameID = nameID;
        category.imageID = imageID;

        CATEGORIES.add(category);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        registerToolBar();
        CSVReader.init(getBaseContext()); //Edited 18 Uhr

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(recyclerLayoutManager);

        //Dekoration
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerview.addItemDecoration(itemDecoration);

        recyclerAdapter = new CategoryViewAdapter(getBaseContext());
        recyclerview.setAdapter(recyclerAdapter);
    }

    // Setzt die Actionbar
    public void registerToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.categories);
        setSupportActionBar(toolbar);
    }

    // Fügt der Actionbar das Menü hinzu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);
        return true;
    }

    // Actionbar - Wenn Optionen ausgewählt -> Sprachauswahlfenster
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
