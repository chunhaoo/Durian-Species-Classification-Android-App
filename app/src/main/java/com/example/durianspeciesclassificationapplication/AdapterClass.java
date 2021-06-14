package com.example.durianspeciesclassificationapplication;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{
    ArrayList<Durians> list;
    public AdapterClass(ArrayList<Durians> list){
        this.list = list;
    }
    @NonNull
    @Override
    public AdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.MyViewHolder holder, int i) {
        holder.name.setText(list.get(i).getName());

        switch(list.get(i).getName()){
            case "D13":
                holder.durianPics.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.d13));
                break;
            case "D24":
                holder.durianPics.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.d24));
                break;
            case "Musang King D197":
                holder.durianPics.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.mk));
                break;
            case "D200 Black Thorn":
                holder.durianPics.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.blackthorn));
                break;
            case "Durian XO":
                holder.durianPics.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.xo));
                break;
            case "D101":
                holder.durianPics.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.d101));
                break;
        }
        String desc = list.get(i).getDesc() + "<br><br><b>Features: </b>" + list.get(i).getFeatures() +
                "<br><br><b>Taste: </b>" + list.get(i).getTaste() + "<br><br><b>Rarity: </b> " + list.get(i).getRarity() +
                "<br><br><b>Source: </b>" + list.get(i).getSource();
        holder.desc.setText(Html.fromHtml(desc));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,desc;
        ImageView durianPics;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.durians);
            desc = itemView.findViewById(R.id.desc);
            durianPics = itemView.findViewById(R.id.durianPics);
        }
    }
}
