package com.example.tramlistsample.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tramlistsample.R;
import com.example.tramlistsample.data.network.Responses.Tram;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter <HomeAdapter.RecyclerViewHolder>{

    public static final String TAG = "HomeAdapter";

    private ArrayList<Tram> tramList;
    private Context context;



    public HomeAdapter(ArrayList<Tram> categoryArrayList, Context context){
        this.tramList = categoryArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tram_item,parent,false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Tram tram = tramList.get(position);
        holder.tvDestination.setText(tram.destination);
        if(!tram.dueMins.isEmpty()) holder.tvDueMin.setText(tram.dueMins+" mins due");


    }

    @Override
    public int getItemCount() {
        return tramList.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvDestination;
        TextView tvDueMin;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            tvDestination = itemView.findViewById(R.id.destination);
            tvDueMin = itemView.findViewById(R.id.due_min);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


        }
    }

}
