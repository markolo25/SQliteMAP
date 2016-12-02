package com.maps.mark.assignment4;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Mark on 12/2/2016.
 */

public class LocationsContentProvider extends ContentProvider {
    private static final String PROVIDER_NAME = "com.maps.mark.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/locations");
    private static final int LOCATIONS = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private LocationsDB database;

    static {
        uriMatcher.addURI(PROVIDER_NAME, "locations", LOCATIONS);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        database = new LocationsDB(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (uriMatcher.match(uri) == LOCATIONS) {
            return database.getAllMarkers();
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
