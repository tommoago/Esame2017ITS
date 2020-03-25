package com.example.esame2017.adaper;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.esame2017.R;
import com.example.esame2017.data.OrdiniTableHelper;

public class OrdiniAdapter extends CursorAdapter {
    public OrdiniAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater vInflater = LayoutInflater.from(context);
        View vView = vInflater.inflate(R.layout.cell_ordine, null);
        return vView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView vData = view.findViewById(R.id.textViewData), vQuantita = view.findViewById(R.id.textViewQuantità), vImporto = view.findViewById(R.id.textViewImporto);
        vData.setText(cursor.getString(cursor.getColumnIndex(OrdiniTableHelper.DATA)));
        int vQuantitaInt = cursor.getInt(cursor.getColumnIndex(OrdiniTableHelper.NUM_BIBITE))
                + cursor.getInt(cursor.getColumnIndex(OrdiniTableHelper.NUM_CAFFE))
                + cursor.getInt(cursor.getColumnIndex(OrdiniTableHelper.NUM_PANINI))
                + cursor.getInt(cursor.getColumnIndex(OrdiniTableHelper.NUM_GELATI))
                + cursor.getInt(cursor.getColumnIndex(OrdiniTableHelper.NUM_PIZZE));
        int vImportoInt = (cursor.getInt(cursor.getColumnIndex(OrdiniTableHelper.NUM_BIBITE)) * 3)
                + (cursor.getInt(cursor.getColumnIndex(OrdiniTableHelper.NUM_CAFFE)) * 1)
                + (cursor.getInt(cursor.getColumnIndex(OrdiniTableHelper.NUM_PANINI)) * 6)
                + (cursor.getInt(cursor.getColumnIndex(OrdiniTableHelper.NUM_GELATI)) * 3)
                + (cursor.getInt(cursor.getColumnIndex(OrdiniTableHelper.NUM_PIZZE)) * 8);

        vQuantita.setText(vQuantitaInt + "");
        vImporto.setText(vImportoInt + "");
    }
}
