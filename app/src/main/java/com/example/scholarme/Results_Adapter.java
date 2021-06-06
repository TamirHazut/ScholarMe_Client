package com.example.scholarme;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by User on 1/17/2018.
 */

public class Results_Adapter extends RecyclerView.Adapter<Results_Adapter.ViewHolder> {

    private static final String TAG = "StaggeredRecyclerViewAd";

    private List<ScholarshipAdapterItem> items;
    private Context mContext;


    public Results_Adapter(Context context, List<ScholarshipAdapterItem> items) {
        this.mContext = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);


        holder.result_item_name.setText(items.get(position).getName());
        holder.result_item_percents.setText(String.valueOf(Double.valueOf(items.get(position).getMatchingPercentage()).intValue()));
        holder.result_item_background.setImageResource(items.get(position).getImage());

        holder.result_item_percents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " +items.get(position).getName());
                Toast.makeText(mContext, items.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView result_item_background;
        TextView result_item_name;
        TextView result_item_percents;

        public ViewHolder(View itemView) {
            super(itemView);
            this.result_item_background = itemView.findViewById(R.id.result_item_background);
            this.result_item_percents = itemView.findViewById(R.id.result_item_percents);
            this.result_item_name = itemView.findViewById(R.id.result_item_name);

        }

    }
}