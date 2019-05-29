package de.diakonie.miguide;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.diakonie.miguide.InstitutionActivity.InstitutionInfo;

import static de.diakonie.miguide.CSVReader.KNOWN_INSTITUTIONS;
import static de.diakonie.miguide.InstitutionActivity.institutions;

public class InstitutionViewAdapter extends RecyclerView.Adapter<InstitutionViewAdapter.ViewHolder> {

    private final Context context;

    private final ArrayList<InstitutionInfo> institutions;

    public InstitutionViewAdapter(Context context, ArrayList<InstitutionInfo> institutions) {
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
                int position = holder.position;
                if (position == -1) {
                    return;
                }

                Intent intent = new Intent(context, InstitutionView.class);
                intent.putExtra("institutionID", position);
                context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.position = position;

        InstitutionInfo category = institutions.get(position);
        holder.itemcategory.setText(category.name);

        int imgID = getPictureIDByInstitution(category.name);
        if(imgID != -1 && imgID != 0) {
            holder.image.setImageResource(imgID); //new
        } else {
            Log.e("Grabbing Picture", "imgID = - 1 - Categoryname " + category.name + " can't be found");
            holder.image.setImageResource(category.imageID); //old
        }


        //holder.image.setImageResource(category.imageID); //old
    }

    private int getPictureIDByInstitution(String name) {
        Institution currentInstitution = getInstitutionbyName(name);
        if(currentInstitution != null) {
            String ImagePath = currentInstitution.ImagePath;
            Log.i("Grabbing Picture", "ImgPath: " + ImagePath);
            if(!ImagePath.isEmpty()) {
                int resID = context.getResources().getIdentifier(ImagePath, "drawable", context.getPackageName()); //BildID durch BildPfad
                Log.i("Loading Picture", "ImgID: " + resID);
                return resID;
            }
        } else {
            Log.e("InstitutionList", "Error: institution with the name can't be found");
        }
        return -1;
    }

    private Institution getInstitutionbyName(String name) {
        for (Institution institution : KNOWN_INSTITUTIONS) {
            if(institution.Name.equals(name)) {
                return institution;
            }
        }
        return null;
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
