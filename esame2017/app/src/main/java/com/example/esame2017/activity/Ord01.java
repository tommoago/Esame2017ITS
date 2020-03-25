package com.example.esame2017.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.esame2017.R;

import java.util.Calendar;

public class Ord01 extends AppCompatActivity {
    Button mAnnulla, mConferma;
    TextView mData, mPizzaCT, mPaninoCT, mBibitaCT, mGelatoCT, mCaffeCT;
    LinearLayout mPizzaL, mPaninoL, mBibitaL, mGelatoL, mCaffeL; //Counter

    int mPizzaC = 0, mPaninoC = 0, mBibitaC = 0, mGelatoC = 0, mCaffeC = 0;

    String mDataT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ord01);
        getSupportActionBar().setTitle("Gestione Ordini");

        setViews();

        mDataT = Calendar.getInstance().toString();
        mData.setText(mDataT);

        mPizzaL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPizzaC++;
                mPizzaCT.setText(mPizzaC + "");
            }
        });

        mPaninoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaninoC++;
                mPaninoCT.setText(mPaninoC + "");
            }
        });

        mBibitaL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBibitaC++;
                mBibitaCT.setText(mBibitaC + "");
            }
        });

        mGelatoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGelatoC++;
                mGelatoCT.setText(mGelatoC + "");
            }
        });

        mCaffeL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCaffeC++;
                mCaffeCT.setText(mCaffeC + "");
            }
        });
    }

    private void setViews() {
        mAnnulla = findViewById(R.id.buttonAnnulla);
        mConferma = findViewById(R.id.buttonConferma);
        mData = findViewById(R.id.textViewData);
        mPaninoCT = findViewById(R.id.paninoCounter);
        mPizzaCT = findViewById(R.id.pizzaCounter);
        mGelatoCT = findViewById(R.id.gelatoCounter);
        mBibitaCT = findViewById(R.id.bibitaCounter);
        mCaffeCT = findViewById(R.id.caffeCounter);
        mPizzaL = findViewById(R.id.pizzaLayout);
        mPaninoL = findViewById(R.id.paninoLayout);
        mBibitaL = findViewById(R.id.bibitaLayout);
        mGelatoL = findViewById(R.id.gelatoLayot);
        mCaffeL = findViewById(R.id.caffeLayout);
    }
}
