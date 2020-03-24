package com.example.lightyear_beroepsproduct3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    String data1[], data2[];
    int images[];
    Context context;

    public ProfileAdapter(Context cntxt, String s1[], String s2[], int img[]) {
        context = cntxt;
        data1 = s1;
        data2 = s2;
        images = img;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.profile_row, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        holder.tvConfiguratienummer.setText(data1[position]);
        holder.tvConfiguratieItems.setText(data2[position]);
        holder.ivProfile.setImageResource(images[position]);
        holder.clProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProfileBestellingActivity.class);
                i.putExtra("data1", data1[position]);
                i.putExtra("data2", data2[position]);
                i.putExtra("images", images[position]);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder{
        TextView tvConfiguratienummer, tvConfiguratieItems;
        ImageView ivProfile;
        ConstraintLayout clProfile;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            tvConfiguratienummer = itemView.findViewById(R.id.tvConfiguratienummer);
            tvConfiguratieItems = itemView.findViewById(R.id.tvConfiguratieItems);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            clProfile = itemView.findViewById(R.id.clProfile);
        }
    }
}
