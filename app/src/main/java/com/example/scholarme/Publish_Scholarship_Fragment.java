package com.example.scholarme;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;


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
        //Glide.with(this).load("https://image.freepik.com/free-vector/abstract-blue-background-science-technology-graphic-design_44392-180.jpg").into(this.form1_fragment_background_IMG);
        return view;
    }

    private void findviews(View view) {
//        this.scholarship=view.findViewById(R.id.scholarship);
//        this.name=view.findViewById(R.id.name);
//        this.year=view.findViewById(R.id.year);
//        this.description=view.findViewById(R.id.description);
//        this.date=view.findViewById(R.id.date);
//        this.email=view.findViewById(R.id.email);
//        //this.form1_fragment_continue_button=(Button) view.findViewById(R.id.form1_fragment_continue_button);
    }


    private void initviews(View view) {
//        this.form1_fragment_DDM_institute.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.dropdown_menu_list_item, readTextFile(R.raw.institutes)));
//        this.form1_fragment_DDM_institute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("DDM", "Institute: " + parent.getItemAtPosition(position).toString());
//            }
//        });
//        this.form1_fragment_DDM_degree.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.dropdown_menu_list_item, readTextFile(R.raw.degrees)));
//        this.form1_fragment_DDM_degree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("DDM", "Degree: " + parent.getItemAtPosition(position).toString());
//            }
//        });

        //Gander Buttons action
//        form1_fragment_continue_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int selectedGender=form1_fragment_gander_radio.getCheckedRadioButtonId();
//                radioSexButton=(RadioButton) view.findViewById(selectedGender);
//                Toast.makeText(view.getContext(),radioSexButton.getText(),Toast.LENGTH_SHORT).show();
//                //
//                Log.d("pttt", "onClick="+radioSexButton.getText());
//                Log.d("pttt","Selected Date: "+ form1_fragment_graduation_cal.getDayOfMonth()+"/"
//                        + (form1_fragment_graduation_cal.getMonth() + 1)+"/"+form1_fragment_graduation_cal.getYear());
//                Log.d("pttt", "onClick="+form1_fragment_study_year.getText());
//            }
//        });

//        this.form1_fragment_continue_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                next_form_fragment=new Next_Form_Fragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_layout, next_form_fragment ); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
//            }
//        });


    }


}
