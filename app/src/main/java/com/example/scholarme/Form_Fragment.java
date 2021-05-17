package com.example.scholarme;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import java.util.Calendar;

public class Form_Fragment extends Fragment_Base {

    private AutoCompleteTextView form_fragment_DDM_institute;
    private AutoCompleteTextView form_fragment_DDM_degree;
    private AutoCompleteTextView  form_fragment_DDM_cities;
    private AutoCompleteTextView  form_fragment_DDM_sectors;
    private AutoCompleteTextView  form_fragment_DDM_study_year;


    private DatePickerDialog datePickerDialog;
    private EditText form_fragment_graduation_EDT;
    private RadioGroup form_fragment_gander_RGP;
    private Switch form_fragment_SWT;
    private ImageButton continue_BTN;
    private Call_Back_SearchForm call_back_searchForm;
    private Results_Fragment results_fragment;

    protected boolean isFull=false;

    public void setCall_back_searchForm(Call_Back_SearchForm _call_back_searchForm){
        this.call_back_searchForm = _call_back_searchForm;
    }

//    private Call_Back_Results call_back_results = new Call_Back_Results() {
//        @Override
//        public void showResults() {
//            results_fragment = new Results_Fragment();
//            results_fragment.setCall_back_showResults(call_back_results);
//            fragment = results_fragment;
//            loadFragment(fragment);
//        }
//    };
    public Form_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_form,container,false);
        findviews(view);
        initviews(view);
        //Glide.with(this).load("https://image.freepik.com/free-vector/abstract-blue-background-science-technology-graphic-design_44392-180.jpg").into(this.form1_fragment_background_IMG);
        return view;
    }

    private void findviews(View view) {
        this.form_fragment_DDM_institute=view.findViewById(R.id.form_fragment_DDM_institute);
        this.form_fragment_DDM_degree=view.findViewById(R.id.form_fragment_DDM_degree);
        this.form_fragment_graduation_EDT=view.findViewById(R.id.form_fragment_graduation_EDT);
        this.form_fragment_DDM_study_year=view.findViewById(R.id.form_fragment_DDM_study_year);
        this.form_fragment_gander_RGP=(RadioGroup)view.findViewById(R.id.form_fragment_gander_RGP);
        this.form_fragment_DDM_cities=view.findViewById(R.id.form_fragment_DDM_cities);
        this.form_fragment_DDM_sectors=view.findViewById(R.id.form_fragment_DDM_sectors);
        this.form_fragment_SWT=(Switch)view.findViewById(R.id.form_fragment_SWT);
        this.continue_BTN=(ImageButton)view.findViewById(R.id.continue_BTN);

    }


    private void initviews(View view) {

        setDRP(R.raw.sectors,R.raw.institutes,R.raw.cities,R.raw.degrees,R.raw.study_year);

        this.form_fragment_SWT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    form_fragment_SWT.setChecked(true);
                    Log.d("DDM", "Switch: " + "checked");
                    search_scholarship.setContribution(true);
                }
                else {
                    form_fragment_SWT.setChecked(false);
                    Log.d("DDM", "Switch: " + "Not checked");
                    search_scholarship.setContribution(false);
                }
            }
        });

        form_fragment_gander_RGP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId){
                Log.d("DDM", "id" + checkedId);
                if (checkedId == R.id.form_fragment_female) {
                    Log.d("DDM", "Institute: F");
                    search_scholarship.setGender(Gender.FEMALE);
                } else if (checkedId == R.id.form_fragment_male) {
                    Log.d("DDM", "Institute: M");
                    search_scholarship.setGender(Gender.MALE);
                }
            }
        });

        form_fragment_graduation_EDT.setInputType(InputType.TYPE_NULL);
        form_fragment_graduation_EDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int yearC = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                if(checkDate(dayOfMonth,monthOfYear, yearC ,day,month,year) == true){
                                    form_fragment_graduation_EDT.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                    Log.d("DDM", "Date: "+form_fragment_graduation_EDT.getText());
                                    search_scholarship.setGraduation_year(form_fragment_graduation_EDT.getText().toString().trim());
                                }else{
                                    Toast.makeText(v.getContext(), "Please Choose a FUTURE DATE", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, yearC, month, day);
                datePickerDialog.show();
            }
        });

        this.continue_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("pttt",search_scholarship.toString());
                if(call_back_searchForm!=null){
                    if(checkForm() == true){
                        Log.d("pttt",search_scholarship.toString());
                        Toast.makeText(v.getContext(), "Product has been Added!!", Toast.LENGTH_SHORT).show();
                        call_back_searchForm.searchForm(search_scholarship);
                    }
                    else{
                        Toast.makeText(v.getContext(), "Please Fill All Fields!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }



    private boolean checkForm() {
        //this.form_fragment_DDM_cities.getText().toString().isEmpty()
        if(search_scholarship.getSectors()==null ||
               search_scholarship.getInstitute()==null ||
                search_scholarship.getDegree()==null ||
                search_scholarship.getLocation()==null ||
                search_scholarship.getStudy_year()==null ||
                search_scholarship.getGraduation_year() == null ||
                (search_scholarship.getGender() != Gender.FEMALE && search_scholarship.getGender() != Gender.MALE))
            return false;
        return true;
    }

    public void setDRP(int sectorsResourceFile,int institutesResourceFile,int citiesResourceFile, int degreesResourceFile,int studyYearResourceFile){

        setFitDropDown(form_fragment_DDM_sectors,sectorsResourceFile);

        setFitDropDown(form_fragment_DDM_institute,institutesResourceFile);
        setFitDropDown(form_fragment_DDM_cities,citiesResourceFile);
        setFitDropDown(form_fragment_DDM_degree,degreesResourceFile);
        setFitDropDown(form_fragment_DDM_study_year,studyYearResourceFile);


    }
    private boolean checkDate(int dayOfMonth, int monthOfYear, int year, int day, int month, int yearC) {
        if(year > yearC)
            return true;
        else{
            if(year == yearC && monthOfYear>month)
                return true;
            else{
                if(year == yearC && monthOfYear==month && dayOfMonth>day)
                    return true;
            }
        }
        return false;
    }

}