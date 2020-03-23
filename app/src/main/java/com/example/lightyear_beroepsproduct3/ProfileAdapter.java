package com.example.lightyear_beroepsproduct3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        holder.tvAantalBestellingen.setText(data1[position]);
        holder.tvConfiguratieBestellingen.setText(data2[position]);
        holder.ivProfile.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder{
        TextView tvAantalBestellingen, tvConfiguratieBestellingen;
        ImageView ivProfile;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAantalBestellingen = itemView.findViewById(R.id.tvAantalBestellingen);
            tvConfiguratieBestellingen = itemView.findViewById(R.id.tvConfiguratieBestellingen);
            ivProfile = itemView.findViewById(R.id.ivProfile);
        }
    }
}
