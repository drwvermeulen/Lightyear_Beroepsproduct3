package com.example.lightyear_beroepsproduct3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private Context context;
    private ArrayList configuratienummer, model, kleur, lak, velg;

    public ProfileAdapter(Context context, ArrayList configuratienummer, ArrayList model, ArrayList kleur, ArrayList lak, ArrayList velg) {
        this.context = context;
        this.configuratienummer = configuratienummer;
        this.model = model;
        this.kleur = kleur;
        this.lak = lak;
        this.velg = velg;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.profile_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvPRConfiguratienummer.setText(String.valueOf(configuratienummer.get(position)));
        holder.tvPRModel.setText(String.valueOf(model.get(position)));
        holder.tvPRKleur.setText(String.valueOf(kleur.get(position)));
        holder.tvPRLak.setText(String.valueOf(lak.get(position)));
        holder.tvPRVelg.setText(String.valueOf(velg.get(position)));
    }

    @Override
    public int getItemCount() {
        return configuratienummer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPRConfiguratienummer, tvPRModel, tvPRKleur, tvPRLak, tvPRVelg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPRConfiguratienummer = itemView.findViewById(R.id.tvPRConfiguratienummer);
            tvPRModel = itemView.findViewById(R.id.tvPRModel);
            tvPRKleur = itemView.findViewById(R.id.tvPRKleur);
            tvPRLak = itemView.findViewById(R.id.tvPRLak);
            tvPRVelg = itemView.findViewById(R.id.tvPRVelg);
        }
    }
}
