package com.example.scholarme;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Form_Fragment extends Fragment {

    private TextView form1_fragment_title;
    private TextView form1_fragment_current_year;
    private AutoCompleteTextView form1_fragment_DDM_institute;
    private AutoCompleteTextView form1_fragment_DDM_degree;
    private RadioGroup form1_fragment_year_radio;
    private DatePicker form1_fragment_graduation_cal;
    private RadioGroup form1_fragment_gander_radio;
    private Button form1_fragment_continue_button;
    private RadioButton radioSexButton;
    private RadioButton currentYearButton;
    final Calendar myCalendar = Calendar.getInstance();
    private ImageView form1_fragment_background_IMG;



    public Form_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_form,container,false);
        findviews(view);
        initviews(view);
        Glide.with(this).load("https://image.freepik.com/free-vector/abstract-blue-background-science-technology-graphic-design_44392-180.jpg").into(this.form1_fragment_background_IMG);
        return view;
    }

    private void findviews(View view) {
        this.form1_fragment_title=view.findViewById(R.id.form1_fragment_title);
        this.form1_fragment_DDM_institute=view.findViewById(R.id.form1_fragment_DDM_institute);
        this.form1_fragment_DDM_degree=view.findViewById(R.id.form1_fragment_DDM_degree);
        this.form1_fragment_graduation_cal=(DatePicker)view.findViewById(R.id.form1_fragment_graduation_cal);
        this.form1_fragment_year_radio=view.findViewById(R.id.form1_fragment_year_radio);
        this.form1_fragment_gander_radio=(RadioGroup)view.findViewById(R.id.form1_fragment_gander_radio);
        this.form1_fragment_continue_button=(Button) view.findViewById(R.id.form1_fragment_continue_button);
        this.form1_fragment_background_IMG=view.findViewById(R.id.form1_fragment_background_IMG);
        this.form1_fragment_current_year=view.findViewById(R.id.form1_fragment_current_year);
    }


    private void initviews(View view) {
        this.form1_fragment_DDM_institute.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.dropdown_menu_list_item, readTextFile(R.raw.institutes)));
        this.form1_fragment_DDM_institute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("DDM", "Institute: " + parent.getItemAtPosition(position).toString());
            }
        });
        this.form1_fragment_DDM_degree.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.dropdown_menu_list_item, readTextFile(R.raw.degrees)));
        this.form1_fragment_DDM_degree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("DDM", "Degree: " + parent.getItemAtPosition(position).toString());
            }
        });

        //Gander Buttons action
        form1_fragment_continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedGender=form1_fragment_gander_radio.getCheckedRadioButtonId();
                int selectedYear=form1_fragment_year_radio.getCheckedRadioButtonId();
                radioSexButton=(RadioButton) view.findViewById(selectedGender);
                currentYearButton=(RadioButton) view.findViewById(selectedYear);
                Toast.makeText(view.getContext(),radioSexButton.getText(),Toast.LENGTH_SHORT).show();
                //
                Log.d("pttt", "onClick="+radioSexButton.getText());
                Log.d("pttt","Selected Date: "+ form1_fragment_graduation_cal.getDayOfMonth()+"/"
                        + (form1_fragment_graduation_cal.getMonth() + 1)+"/"+form1_fragment_graduation_cal.getYear());
                Log.d("pttt", "onClick="+currentYearButton.getText());
            }
        });
    }

    public List<String> readTextFile(int file) {
        List<String> data = new ArrayList<>();
        String string = "";
        InputStream is = this.getResources().openRawResource(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            while (true) {
                try {
                    if ((string = reader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!string.isEmpty()) { data.add(string.replace('\n', '\0')); }
            }
            is.close();
        } finally {
            return data;
        }
    }
}