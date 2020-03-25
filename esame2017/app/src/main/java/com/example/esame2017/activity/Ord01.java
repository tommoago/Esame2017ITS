package com.example.esame2017.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.esame2017.R;

public class Ord01 extends AppCompatActivity {
    Button mAnnulla, mConferma;
    TextView mData, mPizzaCT, mPaninoCT, mBibitaCT, mGelatoCT, mCaffeCT;
    LinearLayout mPizzaL, mPaninoL, mBibitaL, mGelatoL, mCaffeL;

    int mPizzaC=0, mPaninoC=0, mBibitaC=0, mGelatoC=0, mCaffeC=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ord01);
        getSupportActionBar().setTitle("Gestione Ordini");


    }
}
