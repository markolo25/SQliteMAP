package com.maps.mark.assignment4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mark on 12/1/2016.
 */

public class LocationsDB extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "locationDB";
    private static final String TABLE_NAME = "location";
    private static final String PRIMARY_KEY = "key";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ZOOM_LEVEL = "zoomLevel";


    public LocationsDB(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + "(" +
                PRIMARY_KEY + " INTEGER PRIAMRY KEY AUTOINCREMENT, " +
                LATITUDE + " FLOAT, " +
                LONGITUDE + " FLOAT, " +
                ZOOM_LEVEL + " FLOAT" +
                ")";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public long insertMarker(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_NAME, null, values); // Returned ID of row just inserted.
    }

    public Cursor getAllMarkers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, new String[]{LATITUDE, LONGITUDE, ZOOM_LEVEL}, null, null, null, null, null);
    }

    public int deleteAllMarkers() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }

}
