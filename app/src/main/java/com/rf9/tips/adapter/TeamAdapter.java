package com.rf9.tips.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rf9.tips.R;
import com.rf9.tips.model.DataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    ArrayList<DataModel> list;
    Activity activity;

    public TeamAdapter(ArrayList<DataModel> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (list.get(position).getDate().equals("empty")) {
            holder.date.setVisibility(View.GONE);
        } else {
            holder.date.setText(list.get(position).getDate());
        }

        holder.leagueName.setText(list.get(position).getLeague());
        holder.teamOne.setText(list.get(position).getTeam());
        holder.odds.setText(list.get(position).getOdds());
        holder.tipsName.setText(list.get(position).getTips());


        if (list.get(position).getVs().equals("check")) {
            Picasso.get().load(R.drawable.check).into(holder.VS);
        }
        if (list.get(position).getVs().equals("cross")) {
            Picasso.get().load(R.drawable.cross).into(holder.VS);
        }
        if (list.get(position).getVs().equals("pending")) {
            Picasso.get().load(R.drawable.clock).into(holder.VS);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, leagueName, teamOne, teamTwo, odds, tipsName;
        ImageView VS;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            leagueName = itemView.findViewById(R.id.league_name);
            teamOne = itemView.findViewById(R.id.team_one);
            odds = itemView.findViewById(R.id.odds);
            tipsName = itemView.findViewById(R.id.pro_tips);
            VS = itemView.findViewById(R.id.vs);
        }
    }
}
