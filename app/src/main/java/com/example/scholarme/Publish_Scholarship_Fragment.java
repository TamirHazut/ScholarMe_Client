package com.example.scholarme;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;


    /**
     * Not Finished
     */
public class Publish_Scholarship_Fragment extends Fragment_Base {

    private EditText scholarship;
    private EditText description;
    private EditText date;
    private EditText c;

    private Call_Back_Publish_Scholarship call_back_publish_scholarship;


    public void setCall_back_publishScholarship(Call_Back_Publish_Scholarship _call_back_publishScholarship){
        this.call_back_publish_scholarship = _call_back_publishScholarship;
    }

    public Publish_Scholarship_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.publish_scholar,container,false);
        findviews(view);
        initviews(view);
        return view;
    }

    private void findviews(View view) {

    }


    private void initviews(View view) {


    }


}
