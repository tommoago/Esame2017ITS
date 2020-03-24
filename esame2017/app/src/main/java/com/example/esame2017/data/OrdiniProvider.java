package com.example.esame2017.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
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
    private static final UriMatcher mUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMatcher.addURI(AUTORITY,BASE_PATH_ORDERS,ALL_ORDER);
        mUriMatcher.addURI(AUTORITY,BASE_PATH_ORDERS+"/#",SINGLE_ORDER);
    }


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
