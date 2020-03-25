package com.example.esame2017.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.esame2017.R;

public class Home01 extends AppCompatActivity {
    Button mInsOrd, mModOrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home01);

        mInsOrd = findViewById(R.id.buttonInsertOrd);
        mInsOrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home01.this, Ord01.class));
            }
        });
        mModOrd = findViewById(R.id.buttonModOrd);
        mModOrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home01.this, List01.class));

            }
        });
    }
}
