package com.example.esame2017.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esame2017.R;
import com.example.esame2017.adaper.OrdiniAdapter;
import com.example.esame2017.data.OrdiniProvider;
import com.example.esame2017.fragment.CancDialog;

public class List01 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, CancDialog.ICancDialog {
    ListView mList;
    OrdiniAdapter mAdapter;
    public static final int MY_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list01);
        mList = findViewById(R.id.listView);
        mAdapter = new OrdiniAdapter(this, null);
        mList.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(MY_LOADER_ID, null, this);

        mList.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int id = Integer.parseInt(((TextView) v.findViewById(R.id.textViewId)).getText().toString());
                Toast.makeText(List01.this, id+"asd", Toast.LENGTH_SHORT).show();
                return false;
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

    }
}
