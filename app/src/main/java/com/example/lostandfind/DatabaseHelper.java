package com.example.lostandfind;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // 数据库名称和版本
    private static final String DATABASE_NAME = "LostAndFoundDB";
    private static final int DATABASE_VERSION = 1;

    // 表名和列名
    private static final String TABLE_ITEMS = "items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ITEM_NAME = "item_name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";

    // 创建表的 SQL 语句
    private static final String CREATE_TABLE_ITEMS = "CREATE TABLE " + TABLE_ITEMS + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_ITEM_NAME + " TEXT," +
            COLUMN_DESCRIPTION + " TEXT," +
            COLUMN_DATE + " TEXT," +
            COLUMN_LOCATION + " TEXT," +
            COLUMN_LATITUDE + " REAL, " +
            COLUMN_LONGITUDE + " REAL" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建表
        db.execSQL(CREATE_TABLE_ITEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 如果表已存在，删除表并重新创建
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    // 插入一条数据
    public long insertItem(String itemName, String description, String date, String location,Double latitude,Double longitude ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_NAME, itemName);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        long id = db.insert(TABLE_ITEMS, null, values);
        db.close();
        return id;
    }

    // 获取单个数据项
    // 获取单个数据项
    public Item getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEMS, null, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);

        Item item = null;
        if (cursor != null && cursor.moveToFirst()) {
            item = new Item();
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_ITEM_NAME);
            int descIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
            int dateIndex = cursor.getColumnIndex(COLUMN_DATE);
            int locIndex = cursor.getColumnIndex(COLUMN_LOCATION);
            int latIndex = cursor.getColumnIndex(COLUMN_LATITUDE);
            int lonIndex = cursor.getColumnIndex(COLUMN_LONGITUDE);

            if (idIndex != -1) {
                item.setId(cursor.getInt(idIndex));
            }
            if (nameIndex != -1) {
                item.setItemName(cursor.getString(nameIndex));
            }
            if (descIndex != -1) {
                item.setDescription(cursor.getString(descIndex));
            }
            if (dateIndex != -1) {
                item.setDate(cursor.getString(dateIndex));
            }
            if (locIndex != -1) {
                item.setLocation(cursor.getString(locIndex));
            }
            if(latIndex!=-1){
                item.setLatitude(cursor.getDouble(latIndex));
            }
            if(lonIndex!=-1){
                item.setLongitude(cursor.getDouble(lonIndex));
            }
            cursor.close();
        }
        db.close();
        return item;
    }


    // 查询所有数据
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // 遍历查询结果并添加到列表中
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Item item = new Item();
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int nameIndex = cursor.getColumnIndex(COLUMN_ITEM_NAME);
                int descIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
                int dateIndex = cursor.getColumnIndex(COLUMN_DATE);
                int locIndex = cursor.getColumnIndex(COLUMN_LOCATION);
                int latIndex = cursor.getColumnIndex(COLUMN_LATITUDE);
                int lonIndex = cursor.getColumnIndex(COLUMN_LONGITUDE);

                if (idIndex != -1) {
                    item.setId(cursor.getInt(idIndex));
                }
                if (nameIndex != -1) {
                    item.setItemName(cursor.getString(nameIndex));
                }
                if (descIndex != -1) {
                    item.setDescription(cursor.getString(descIndex));
                }
                if (dateIndex != -1) {
                    item.setDate(cursor.getString(dateIndex));
                }
                if (locIndex != -1) {
                    item.setLocation(cursor.getString(locIndex));
                }
                if(latIndex!=-1){
                    item.setLatitude(cursor.getDouble(latIndex));
                }
                if(lonIndex!=-1){
                    item.setLongitude(cursor.getDouble(lonIndex));
                }
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return itemList;
    }

    // 删除一条数据
    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}