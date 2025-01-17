package com.example.lightyear_beroepsproduct3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private Context context;
    private ArrayList<GeconfigureerdeLightyear> geconfigureerdeLightyearList;

    public ProfileAdapter(Context context, ArrayList<GeconfigureerdeLightyear> geconfigureerdeLightyearList) {
        this.context = context;
        this.geconfigureerdeLightyearList = geconfigureerdeLightyearList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.profile_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        GeconfigureerdeLightyear geconfigureerdeLightyear = geconfigureerdeLightyearList.get(position);
        holder.ivProfileLightyear.setImageResource(geconfigureerdeLightyearList.get(position).getImageResource());
        holder.tvProfileConfiguratienummer.setText(geconfigureerdeLightyearList.get(position).getFormattedConfig());
        holder.tvProfileModel.setText(String.valueOf(geconfigureerdeLightyearList.get(position).getMdl()));
        if(geconfigureerdeLightyear instanceof GeconfigureerdeLightyearPioneerEdition) {
            GeconfigureerdeLightyearPioneerEdition glpe = (GeconfigureerdeLightyearPioneerEdition) geconfigureerdeLightyear;
            holder.tvProfilePioneerEdition.setText(String.format("- %s", glpe.getPnrdtn()));
        } else {
            holder.tvProfilePioneerEdition.setVisibility(View.INVISIBLE);
            holder.ivProfilePioneerEdition.setVisibility(View.INVISIBLE);
        }
        holder.profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ProfileBestellingActivity.class);
                i.putExtra(ConfiguratorActivity.CONFIGURERENMODEL, geconfigureerdeLightyearList.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return geconfigureerdeLightyearList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfileLightyear, ivProfilePioneerEdition;
        TextView tvProfileConfiguratienummer, tvProfileModel, tvProfilePioneerEdition;
        LinearLayout profileLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileLightyear = itemView.findViewById(R.id.ivProfileLightyear);
            ivProfilePioneerEdition = itemView.findViewById(R.id.ivProfilePioneerEdition);
            tvProfileConfiguratienummer = itemView.findViewById(R.id.tvProfileConfiguratienummer);
            tvProfileModel = itemView.findViewById(R.id.tvProfileModel);
            tvProfilePioneerEdition = itemView.findViewById(R.id.tvProfilePioneerEdition);
            profileLayout = itemView.findViewById(R.id.profileLayout);
        }
    }
}
