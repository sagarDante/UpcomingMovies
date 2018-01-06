package com.chincholkar.sagar.upcomingmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class About_Activity extends AppCompatActivity {

    private TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_);
        Name = (TextView)findViewById(R.id.name);

        Name.setText(" Created by \n Sagar Chinvholkar");
    }
}
