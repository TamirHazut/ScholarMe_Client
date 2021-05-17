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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 1/17/2018.
 */

public class Results_Adapter extends RecyclerView.Adapter<Results_Adapter.ViewHolder> {

    private static final String TAG = "StaggeredRecyclerViewAd";

//    private ArrayList<String> mNames = new ArrayList<>();
//    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private List<ScholarshipAdapterItem> items;
    private Context mContext;

//    public Results_Adapter(Context context, ArrayList<String> names, ArrayList<Integer> imageUrls) {
//        mNames = names;
//        mImageUrls = imageUrls;
//        mContext = context;
//    }

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



//        Glide.with(mContext)
//                .load(mImageUrls.get(position))
//                .apply(requestOptions)
//                .into(holder.image);

        holder.name.setText(items.get(position).getName());
        holder.image.setImageResource(items.get(position).getImage());

        holder.image.setOnClickListener(new View.OnClickListener() {
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

        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.imageview_widget);
            this.name = itemView.findViewById(R.id.name_widget);
        }
    }
}