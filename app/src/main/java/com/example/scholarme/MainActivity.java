package com.example.scholarme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.content.Intent;
import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {
    private ImageView background_IMG;
    private Form_Fragment form_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        form_fragment=new Form_Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_layout,form_fragment).commit();

        findviews();
        initviews();
        Glide.with(this).load("https://image.freepik.com/free-vector/abstract-low-polygonal-graduation-cap_127544-825.jpg").into(this.background_IMG);

    }

    private void initviews() {

    }

    private void findviews() {
        this.background_IMG=findViewById(R.id.main_background_IMG);
    }

}