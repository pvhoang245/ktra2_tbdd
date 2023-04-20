package com.example.ktra2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ktra2.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Chitieu.db";
    private static int DATABASE_VERSION=1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql=" CREATE TABLE items( " +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " title TEXT, category TEXT, price TEXT, date TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onOpen(db);
    }
    //get all oder by date desc
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = " date DESC ";
        Cursor rs=st.query("items", null,null,null,null,
                null, order);
        while(rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String c = rs.getString(2);
            String p = rs.getString(3);
            String date = rs.getString(4);
            list.add(new Item(id, title,c,p,date));
        }
        return list;
    }

    //add
    public long addItem(Item i) {
        ContentValues values = new ContentValues();
        values.put("title", i.getTitle());
        values.put("category", i.getCategory());
        values.put("price", i.getPrice());
        values.put("date", i.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("items", null, values);
    }
    //lay item theo date
    public List<Item> getByDate(String date){
        List<Item> list = new ArrayList<>();
        String where = " date like ? ";
        String[] args = {date};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null,where,args,null,null,null);
        while(rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String c = rs.getString(2);
            String p = rs.getString(3);
            list.add(new Item(id, title,c,p, date));
        }
        return list;
    }
    //update
    public int update(Item i) {
        ContentValues values = new ContentValues();
        values.put("title", i.getTitle());
        values.put("category", i.getCategory());
        values.put("price", i.getPrice());
        values.put("date", i.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String where = " id=? ";
        String[] args = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("items", values, where, args);
    }

    //delete
    public int delete(int id) {
        String where = " id=? ";
        String[] args = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("items", where, args);
    }
    public List<Item> searchByTitle(String key){
        List<Item> list = new ArrayList<>();
        String where = " title like ? ";
        String[] args = { "%"+key+"%" };
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null,where,args,null,null,null);
        while(rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String c = rs.getString(2);
            String p = rs.getString(3);
            String d = rs.getString(4);
            list.add(new Item(id, title,c,p, d));
        }
        return list;
    }
    public List<Item> searchByCategory(String category){
        List<Item> list = new ArrayList<>();
        String where = " category like ? ";
        String[] args = {category};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null,where,args,null,null,null);
        while(rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String c = rs.getString(2);
            String p = rs.getString(3);
            String d = rs.getString(4);
            list.add(new Item(id, title,c,p, d));
        }
        return list;
    }
    public List<Item> searchByDateFromTo(String from, String to){
        List<Item> list = new ArrayList<>();
        String where = " date BETWEEN ? AND ? ";
        String[] args = {from.trim(), to.trim()};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null,where,args,null,null,null);
        while(rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String c = rs.getString(2);
            String p = rs.getString(3);
            String d = rs.getString(4);
            list.add(new Item(id, title,c,p, d));
        }
        return list;
    }

}























