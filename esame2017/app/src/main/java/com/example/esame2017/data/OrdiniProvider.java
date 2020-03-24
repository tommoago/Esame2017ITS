package com.example.esame2017.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrdiniProvider extends ContentProvider {


    public static final String AUTORITY = "com.example.esame2017.data.OrdiniProvider";
    public static final String BASE_PATH_ORDERS = "orders";
    public static final int ALL_ORDER = 1;
    public static final int SINGLE_ORDER = 0;
    public static final String MIME_TYPE_ORDERS = ContentResolver.CURSOR_DIR_BASE_TYPE + "vnd.all_orders";
    public static final String MIME_TYPE_ORDER = ContentResolver.CURSOR_ITEM_BASE_TYPE + "vnd.single_order";
    public static final Uri ORDERS_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTORITY + "/" + BASE_PATH_ORDERS);
    private OrdiniDB mDb;
    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMatcher.addURI(AUTORITY, BASE_PATH_ORDERS, ALL_ORDER);
        mUriMatcher.addURI(AUTORITY, BASE_PATH_ORDERS + "/#", SINGLE_ORDER);
    }


    @Override
    public boolean onCreate() {
        mDb = new OrdiniDB(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase vDb = mDb.getReadableDatabase();
        SQLiteQueryBuilder vBuilder = new SQLiteQueryBuilder();
        switch (mUriMatcher.match(uri)) {
            case SINGLE_ORDER:
                vBuilder.setTables(OrdiniTableHelper.TABLE_NAME);
                vBuilder.appendWhere(OrdiniTableHelper._ID + " = " + uri.getLastPathSegment());
                break;
            case ALL_ORDER:
                vBuilder.setTables(OrdiniTableHelper.TABLE_NAME);
                break;
        }

        Cursor vCursor = vBuilder.query(vDb, projection, selection, selectionArgs, null, null, sortOrder);
        vCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return vCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (mUriMatcher.match(uri)) {
            case SINGLE_ORDER:
                return MIME_TYPE_ORDER;

            case ALL_ORDER:
                return MIME_TYPE_ORDERS;

        }

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        if (mUriMatcher.match(uri) == ALL_ORDER) {
            SQLiteDatabase vDb = mDb.getWritableDatabase();
            long vResult = vDb.insert(OrdiniTableHelper.TABLE_NAME, null, values);
            String vResultString = ContentResolver.SCHEME_CONTENT + "://" + BASE_PATH_ORDERS + "/" + vResult;
            getContext().getContentResolver().notifyChange(uri, null);
            return Uri.parse(vResultString);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String vTable = "", vQuery = "";
        SQLiteDatabase vDb = mDb.getWritableDatabase();
        switch (mUriMatcher.match(uri)) {
            case ALL_ORDER:
                vTable = OrdiniTableHelper.TABLE_NAME;
                vQuery = selection;
                break;
            case SINGLE_ORDER:
                vTable = OrdiniTableHelper.TABLE_NAME;
                vQuery = OrdiniTableHelper._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    vQuery += " AND " + selection;
                }
                break;
        }
        int vDeletedRows = vDb.delete(vTable, vQuery, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return vDeletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String vTable = "", vQuery = "";
        SQLiteDatabase vDb = mDb.getWritableDatabase();
        switch (mUriMatcher.match(uri)) {
            case ALL_ORDER:
                vTable = OrdiniTableHelper.TABLE_NAME;
                vQuery = selection;
                break;
            case SINGLE_ORDER:
                vTable = OrdiniTableHelper.TABLE_NAME;
                vQuery = OrdiniTableHelper._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    vQuery += " AND " + selection;
                }
                break;
        }
        int vUpdatedRows = vDb.update(vTable, values, vQuery, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return vUpdatedRows;
    }
}
