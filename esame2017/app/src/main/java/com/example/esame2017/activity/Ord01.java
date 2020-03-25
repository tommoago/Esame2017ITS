package com.example.esame2017.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.esame2017.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ord01 extends AppCompatActivity {
    private static final String DATA = "DATA";
    private static final String PIZZA = "PIZZA";
    private static final String PANINO = "PANINO";
    private static final String BIBITA = "BIBITA";
    private static final String GELATO = "GELATO";
    private static final String CAFFE = "CAFFE";

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

        if (savedInstanceState != null) {
            mDataT = savedInstanceState.getString(DATA);
            mData.setText(mDataT);
            mPizzaC = savedInstanceState.getInt(PIZZA);
            mPizzaCT.setText(mPizzaC + "");
            mPaninoC = savedInstanceState.getInt(PANINO);
            mPaninoCT.setText(mPaninoC + "");
            mBibitaC = savedInstanceState.getInt(BIBITA);
            mBibitaCT.setText(mBibitaC + "");
            mGelatoC = savedInstanceState.getInt(GELATO);
            mGelatoCT.setText(mGelatoC + "");
            mCaffeC = savedInstanceState.getInt(CAFFE);
            mCaffeCT.setText(mCaffeC + "");
        } else {
            Calendar calendardate = Calendar.getInstance();
            String strdate = null;

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");

            if (calendardate != null) {
                strdate = sdf.format(calendardate.getTime());
            }

            mDataT = strdate;
            mData.setText(mDataT);
        }


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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(DATA, mDataT);
        outState.putInt(PIZZA, mPizzaC);
        outState.putInt(PANINO, mPaninoC);
        outState.putInt(BIBITA, mBibitaC);
        outState.putInt(GELATO, mGelatoC);
        outState.putInt(CAFFE, mCaffeC);
    }
}
