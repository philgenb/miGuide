package de.diakonie.miguide;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.diakonie.miguide.CategoryActivity.Category;
import static de.diakonie.miguide.CategoryActivity.CATEGORIES;

import java.util.ArrayList;

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.ViewHolder> {

    private final Context context;

    public CategoryViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        // Klick auf Viewholder -> Aufruf von der Liste der institutionen der jeweiligen Kategorie
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.position;
                if (position == -1) {
                    return;
                }

                Category category = CATEGORIES.get(position);
                Intent intent = new Intent(context, InstitutionActivity.class);
                intent.putExtra("categoryID", position);
                context.startActivity(intent);
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Category category = CATEGORIES.get(position);

        holder.position = position;
        holder.itemcategory.setText(context.getString(category.nameID));
        holder.image.setImageResource(category.imageID);
    }

    @Override
    public int getItemCount() {
        return CATEGORIES.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        int position = -1;

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
