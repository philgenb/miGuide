package de.diakonie.miguide;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.diakonie.miguide.InstitutionActivity.Institution;

public class InstitutionViewAdapter extends RecyclerView.Adapter<InstitutionViewAdapter.ViewHolder> {

    private final Context context;

    private final ArrayList<Institution> institutions;

    public InstitutionViewAdapter(Context context, ArrayList<Institution> institutions) {
        this.context = context;

        this.institutions = institutions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        // Klick auf Viewholder -> Aufruf von der Liste der Institutionen der jeweiligen Kategorie
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.position = position;

        Institution category = institutions.get(position);

        holder.itemcategory.setText(category.name);
        holder.image.setImageResource(category.imageID);
    }

    @Override
    public int getItemCount() {
        return institutions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        int position;

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
