package com.example.esame2017.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esame2017.R;
import com.example.esame2017.adaper.OrdiniAdapter;
import com.example.esame2017.data.OrdiniProvider;
import com.example.esame2017.data.OrdiniTableHelper;
import com.example.esame2017.fragment.CancDialog;

public class List01 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, CancDialog.ICancDialog {
    public static final String ORD_ID = "ORD_ID";
    ListView mList;
    OrdiniAdapter mAdapter;
    public static final int MY_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list01);
        setTitle("Gestione Ordini");
        mList = findViewById(R.id.listView);
        mAdapter = new OrdiniAdapter(this, null);
        mList.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(MY_LOADER_ID, null, this);

        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor vResult = getContentResolver().query(Uri.parse(OrdiniProvider.ORDERS_URI + "/" + id), null, null, null, null);
                vResult.moveToNext();
                String vdate = vResult.getString(vResult.getColumnIndex(OrdiniTableHelper.DATA));

                CancDialog vDialog = new CancDialog("CANCELLA ORDINE", "Vuoi cancellare l’ordine " + vdate + " ?", id);
                vDialog.show(getSupportFragmentManager(), null);
                return true;
            }

        });

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vIntent=new Intent(List01.this, Ord01.class);
                Bundle vBundle=new Bundle();
                vBundle.putLong(ORD_ID,id);
                vIntent.putExtras(vBundle);
                startActivity(vIntent);
            }
        });

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, OrdiniProvider.ORDERS_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mAdapter.changeCursor(data);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mAdapter.changeCursor(null);

    }

    @Override
    public void onResponse(boolean aResponse, long aId) {
        if (aResponse) {
            int vDeletedRows=getContentResolver().delete(Uri.parse(OrdiniProvider.ORDERS_URI + "/" + aId), null, null);
            Log.d("asda", "onResponse: delete items"+vDeletedRows);
        }

    }
}
