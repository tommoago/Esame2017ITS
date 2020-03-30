package com.example.esame2017.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.esame2017.R;
import com.example.esame2017.data.OrdiniProvider;

public class Home01 extends AppCompatActivity {
    Button mInsOrd, mModOrd;
    TextView mTextOrd;

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

        mTextOrd = findViewById(R.id.textViewTotOrd);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor vCursor = getContentResolver().query(OrdiniProvider.ORDERS_URI, null, null, null, null);

        int vTotOrds = vCursor.getCount();
        mTextOrd.setText("Totale ordini inseriti: " + vTotOrds);
    }
}
