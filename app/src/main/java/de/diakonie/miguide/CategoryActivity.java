package de.diakonie.miguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

        loadArrays();

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(recyclerLayoutManager);

        recyclerAdapter = new RecyclerViewAdapter(categorys, itemimages, getBaseContext());
        recyclerview.setAdapter(recyclerAdapter);
    }

    private void loadArrays() {
        if(categorys.isEmpty()) {
            categorys.add("test");
            categorys.add("test2");
        }
        if(itemimages.isEmpty()) {
            itemimages.add(R.drawable.ic_logo_miguide);
            itemimages.add(R.drawable.ic_logo_miguide);
        }
    }
}
