package com.example.scholarme;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Base extends Fragment {
    protected String selected;

    protected Search_Scholarship search_scholarship = new Search_Scholarship();

    protected List<String> readTextFile(int resourceFile) {
        List<String> data = new ArrayList<>();
        String string = "";
        InputStream is = this.getResources().openRawResource(resourceFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            while (true) {
                try {
                    if ((string = reader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!string.isEmpty()) {
                    data.add(string.replace('\n', '\0')); }
            }
            is.close();
        } finally {
            return data;
        }
    }

    protected void setFitDropDown(AutoCompleteTextView dropDown, int  resourceFile){
        dropDown.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.dropdown_menu_list_item, readTextFile(resourceFile)));
        dropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setSelected(parent.getItemAtPosition(position).toString().trim());
                Log.d("DDM", "selected: " + selected);
                if (resourceFile == R.raw.degrees) {
                    search_scholarship.setDegree(selected);
                    Log.d("DDM", "degree: " + search_scholarship.getDegree());
                } else if (resourceFile == R.raw.institutes) {
                    search_scholarship.setInstitute(selected);
                    Log.d("DDM", "Institute: " + search_scholarship.getInstitute());
                } else if (resourceFile == R.raw.cities) {
                    search_scholarship.setLocation(selected);
                    Log.d("DDM", "city: " + search_scholarship.getLocation());
                }
                else if (resourceFile == R.raw.sectors) {
                    search_scholarship.setSectors(selected);
                    Log.d("DDM", "sector: " + search_scholarship.getSectors());
            }
                else if (resourceFile == R.raw.study_year) {
                    search_scholarship.setStudy_year(selected);
                    Log.d("DDM", "study_year: " + search_scholarship.getSectors());
                }
            }
        });
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
