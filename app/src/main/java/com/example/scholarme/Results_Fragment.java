package com.example.scholarme;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Results_Fragment extends Fragment_Base {

    private RecyclerView recyclerView;
    private static final String TAG = "MainActivity";
    private static final int NUM_COLUMNS = 2;

    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private Call_Back_Results call_back_results;
    private List<Scholarship> scholarships;
    private Gson gson = new Gson();

    public Results_Fragment() {
    }

    public void setCall_back_showResults(Call_Back_Results _call_back_results){
        this.call_back_results = _call_back_results;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_results,container,false);
        Type listType = new TypeToken<List<Scholarship>>(){}.getType();
        scholarships = gson.fromJson(getArguments().getString(Constants.SCHOLARSHIPS), listType);
        findviews(view);
        initImageBitmaps();
        //initviews(view);
        //Glide.with(this).load("https://image.freepik.com/free-vector/abstract-blue-background-science-technology-graphic-design_44392-180.jpg").into(this.form1_fragment_background_IMG);
        return view;
    }

    private void findviews(View view) {

        this.recyclerView = view.findViewById(R.id.recyclerView);

    }

    private void initImageBitmaps(){
        List<ScholarshipAdapterItem> items = new ArrayList<>();
        for (Scholarship scholarship: scholarships) {
            String image_url = "p" + (int)(Math.ceil(scholarship.getMatchingPercentage()/10)*10);
            int image_url_id = this.getActivity().getApplicationContext().getResources().getIdentifier(image_url, "drawable", this.getActivity().getApplicationContext().getPackageName());
            items.add(new ScholarshipAdapterItem(image_url_id, scholarship.getName(), scholarship.getDescription(), scholarship.getMatchingPercentage()));
        }
        /*Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add(R.drawable.p10);
        mNames.add("Havasu Falls");

        mImageUrls.add(R.drawable.p100);
        mNames.add("Trondheim");

        mImageUrls.add(R.drawable.p20);
        mNames.add("Portugal");

        mImageUrls.add(R.drawable.p60);
        mNames.add("Rocky Mountain National Park");

        mImageUrls.add(R.drawable.p90);
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add(R.drawable.p50);
        mNames.add("Portugal");

        mImageUrls.add(R.drawable.p30);
        mNames.add("Rocky Mountain National Park");
        mImageUrls.add(R.drawable.p40);
        mNames.add("Trondheim");

        mImageUrls.add(R.drawable.p80);
        mNames.add("Portugal");

        mImageUrls.add(R.drawable.p70);
        mNames.add("Rocky Mountain National Park");

//        mImageUrls.add(R.drawable.med);
//        mNames.add("Havasu Falls");
//
//        mImageUrls.add(R.drawable.huge);
//        mNames.add("Trondheim");
//
//        mImageUrls.add(R.drawable.small);
//        mNames.add("Portugal");
//
//        mImageUrls.add(R.drawable.med);
//        mNames.add("Rocky Mountain National Park");
//
//        mImageUrls.add(R.drawable.med);
//        mNames.add("Havasu Falls");
//
//        mImageUrls.add(R.drawable.small);
//        mNames.add("Trondheim");
//
//        mImageUrls.add(R.drawable.huge);
//        mNames.add("Havasu Falls");
//
//        mImageUrls.add(R.drawable.huge);
//        mNames.add("Trondheim");
//
//        mImageUrls.add(R.drawable.big);
//        mNames.add("Havasu Falls");
//
//        mImageUrls.add(R.drawable.big);
//        mNames.add("Trondheim");

*/
        initRecyclerView(items);

    }

    private void initRecyclerView(List<ScholarshipAdapterItem> items){
        Log.d(TAG, "initRecyclerView: initializing staggered recyclerview.");

        Results_Adapter staggeredRecyclerViewAdapter =
                new Results_Adapter(getContext(), items);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
    }


}
