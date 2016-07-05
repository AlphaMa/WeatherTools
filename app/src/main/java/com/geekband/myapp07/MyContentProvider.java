package com.geekband.myapp07;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by SUN on 2016/6/24.
 */
public class MyContentProvider extends ContentProvider{

    public static final int VERSION = 1;
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private  static UriMatcher uriMatcher;
    public static final String CONTENT = "content://";
    public static final String AUTHORITY = "com.geekband.myapp07";
    public static final String WEATHER_TABLE_NAME = "weather_tb";
    public static final int WEATHER_URI_CODE = 1;
    public static final String URI = CONTENT + AUTHORITY + "/" + WEATHER_TABLE_NAME;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,WEATHER_TABLE_NAME,WEATHER_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        helper = new MySQLiteOpenHelper(getContext(),"weather.db",null, VERSION);
        db = helper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        String tableName = getTableName(uri);
        if(TextUtils.isEmpty(tableName)){
            return null;
        }
        Cursor c = db.query(tableName,strings,s,strings1,null,null,s1);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        String tableName = getTableName(uri);
        if(TextUtils.isEmpty(tableName)){
            return null;
        }
        long id = db.insert(tableName,null,contentValues);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        String tableName = getTableName(uri);
        if(TextUtils.isEmpty(tableName)){
            return 0;
        }
        int count = db.delete(tableName,s,strings);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        String tableName = getTableName(uri);
        if(TextUtils.isEmpty(tableName)){
            return 0;
        }
        int count = db.update(tableName,contentValues,s,strings);
        return count;
    }

    public String getTableName(Uri uri){
        int type = uriMatcher.match(uri);
        String tableName = null;
        switch (type){
            case WEATHER_URI_CODE:
                tableName = WEATHER_TABLE_NAME;
                break;
        }
        return tableName;
    }


}
