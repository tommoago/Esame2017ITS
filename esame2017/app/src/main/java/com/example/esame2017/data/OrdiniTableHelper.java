package com.example.esame2017.data;

import android.provider.BaseColumns;

public class OrdiniTableHelper implements BaseColumns {

    public static final String TABLE_NAME = "ordini";
    public static final String DATA = "data";
    public static final String NUM_PIZZE = "numPizze";
    public static final String NUM_PANINI = "numPanini";
    public static final String NUM_BIBITE = "numBibite";
    public static final String NUM_GELATI = "numGelati";
    public static final String NUM_CAFFE = "numCaffe";

    public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DATA + " TEXT, " +
            NUM_PIZZE + " INTEGER, " +
            NUM_PANINI + " INTEGER, " +
            NUM_GELATI + " INTEGER, " +
            NUM_BIBITE + " INTEGER, " +
            NUM_CAFFE + " INTEGER ) ;";


}
