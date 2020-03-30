package com.example.esame2017.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.esame2017.R;
import com.example.esame2017.data.OrdiniProvider;
import com.example.esame2017.data.OrdiniTableHelper;
import com.example.esame2017.fragment.CancDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.esame2017.activity.List01.ORD_ID;

public class Ord01 extends AppCompatActivity implements CancDialog.ICancDialog {
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

    long mId = -1;
    int mTotal;

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

        if (getIntent().getExtras() != null) {
            mId = getIntent().getExtras().getLong(ORD_ID);
            getOrder(mId);
            updateTot();
        }


        mPizzaL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPizzaC++;
                mPizzaCT.setText(mPizzaC + "");
                updateTot();
            }
        });

        mPaninoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaninoC++;
                mPaninoCT.setText(mPaninoC + "");
                updateTot();
            }
        });

        mBibitaL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBibitaC++;
                mBibitaCT.setText(mBibitaC + "");
                updateTot();
            }
        });

        mGelatoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGelatoC++;
                mGelatoCT.setText(mGelatoC + "");
                updateTot();
            }
        });

        mCaffeL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCaffeC++;
                mCaffeCT.setText(mCaffeC + "");
                updateTot();
            }
        });
//-----------------------------------
        mPizzaL.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showCancDialog(1);
                return false;
            }
        });

        mPaninoL.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showCancDialog(2);
                return false;
            }
        });

        mBibitaL.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showCancDialog(3);
                return false;
            }

        });

        mGelatoL.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showCancDialog(4);
                return false;
            }

        });

        mCaffeL.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showCancDialog(5);
                return false;
            }

        });

        mAnnulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues vValues = new ContentValues();
                vValues.put(OrdiniTableHelper.DATA, mDataT);
                vValues.put(OrdiniTableHelper.NUM_BIBITE, mBibitaC);
                vValues.put(OrdiniTableHelper.NUM_CAFFE, mCaffeC);
                vValues.put(OrdiniTableHelper.NUM_GELATI, mGelatoC);
                vValues.put(OrdiniTableHelper.NUM_PANINI, mPaninoC);
                vValues.put(OrdiniTableHelper.NUM_PIZZE, mPizzaC);
                if (mId == -1) {
                    Uri vResultUri = getContentResolver().insert(OrdiniProvider.ORDERS_URI, vValues);
                    Log.d("asda", "onClick: " + vResultUri);
                } else {
                    int vUpdatedRows = getContentResolver().update(Uri.parse(OrdiniProvider.ORDERS_URI + "/" + mId), vValues,
                            null, null);
                    Log.d("asda", "onClick: update "+vUpdatedRows);

                }
                finish();
            }
        });
    }

    private void getOrder(long aId) {

        Cursor vCursor = getContentResolver().query(Uri.parse(OrdiniProvider.ORDERS_URI + "/" + aId),
                null, null, null, null);
        vCursor.moveToNext();
        mDataT = vCursor.getString(vCursor.getColumnIndex(OrdiniTableHelper.DATA));
        mData.setText(mDataT);
        mPizzaC = vCursor.getInt(vCursor.getColumnIndex(OrdiniTableHelper.NUM_PIZZE));
        if (mPizzaC == 0) mPizzaL.setVisibility(View.GONE);
        else mPizzaCT.setText(mPizzaC + "");
        mPaninoC = vCursor.getInt(vCursor.getColumnIndex(OrdiniTableHelper.NUM_PANINI));
        if (mPaninoC == 0) mPaninoL.setVisibility(View.GONE);
        else mPaninoCT.setText(mPaninoC + "");
        mBibitaC = vCursor.getInt(vCursor.getColumnIndex(OrdiniTableHelper.NUM_BIBITE));
        if (mBibitaC == 0) mBibitaL.setVisibility(View.GONE);
        mBibitaCT.setText(mBibitaC + "");
        mGelatoC = vCursor.getInt(vCursor.getColumnIndex(OrdiniTableHelper.NUM_GELATI));
        if (mGelatoC == 0) mGelatoL.setVisibility(View.GONE);
        mGelatoCT.setText(mGelatoC + "");
        mCaffeC = vCursor.getInt(vCursor.getColumnIndex(OrdiniTableHelper.NUM_CAFFE));
        if (mCaffeC == 0) mCaffeL.setVisibility(View.GONE);
        mCaffeCT.setText(mCaffeC + "");


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
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DATA, mDataT);
        outState.putInt(PIZZA, mPizzaC);
        outState.putInt(PANINO, mPaninoC);
        outState.putInt(BIBITA, mBibitaC);
        outState.putInt(GELATO, mGelatoC);
        outState.putInt(CAFFE, mCaffeC);
    }

    @Override
    public void onResponse(boolean aResponse, long aId) {
        if (aResponse) {
            int vId = (int) aId;
            switch (vId) {
                case 1:
                    mPizzaC = 0;
                    mPizzaCT.setText(mPizzaC + "");
                    break;
                case 2:
                    mPaninoC = 0;
                    mPaninoCT.setText(mPaninoC + "");
                    break;
                case 3:
                    mBibitaC = 0;
                    mBibitaCT.setText(mBibitaC + "");
                    break;
                case 4:
                    mGelatoC = 0;
                    mGelatoCT.setText(mGelatoC + "");
                    break;
                case 5:
                    mCaffeC = 0;
                    mCaffeCT.setText(mCaffeC + "");
                    break;
            }
            updateTot();
        } else {
            return;
        }

    }

    private void showCancDialog(int i) {
        CancDialog vDialog = null;
        switch (i) {
            case 1:
                vDialog = new CancDialog("PIZZA", "La quantità di PIZZA verrà azzerata. Voui continuare?", i);
                break;
            case 2:
                vDialog = new CancDialog("PANINO", "La quantità di PANINO verrà azzerata. Voui continuare?", i);
                break;
            case 3:
                vDialog = new CancDialog("BIBITA", "La quantità di BIBITA verrà azzerata. Voui continuare?", i);
                break;
            case 4:
                vDialog = new CancDialog("GELATO", "La quantità di GELATO verrà azzerata. Voui continuare?", i);
                break;
            case 5:
                vDialog = new CancDialog("CAFFE", "La quantità di CAFFE verrà azzerata. Voui continuare?", i);
                break;
        }
        vDialog.show(getSupportFragmentManager(), null);
    }

    private void updateTot() {
        if (mId != -1) {
            mTotal = (mBibitaC * 3)
                    + (mCaffeC * 1)
                    + (mPaninoC * 6)
                    + (mGelatoC * 3)
                    + (mPizzaC * 8);

            mData.setText("TOTALE € " + mTotal);
        }


    }
}
