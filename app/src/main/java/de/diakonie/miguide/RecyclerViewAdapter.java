package de.diakonie.miguide;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> categorys = new ArrayList<>();
    private ArrayList<Integer> itemimages = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> categorys, ArrayList<Integer> itemimages, Context context) {
        this.categorys = categorys;
        this.itemimages = itemimages;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() { //Klick auf Viewholder -> Aufruf von der Liste der Insitutionen der jeweiligen Kategorie
            @Override

            public void onClick(View view) {
                String clickcategory = holder.itemcategory.getText().toString();
                Intent intent = new Intent(context, InstitutionActivity.class);
                intent.putExtra("category", clickcategory);
                context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.itemcategory.setText(categorys.get(position));
        holder.image.setImageResource(itemimages.get(position));

        /* KlickListener - nicht empfohlen
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("VIEW", "test");
            }
        });
        */
        
    }

    @Override
    public int getItemCount() {
        return categorys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView itemcategory;
        RelativeLayout itemlayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemimage);
            itemcategory = itemView.findViewById(R.id.itemcategory);
            itemlayout = itemView.findViewById(R.id.itemlayout);
        }
    }
}
