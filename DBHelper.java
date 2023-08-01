package com.uasbryanchristopher202102276.uas_202102276;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context,"UAS_202102276.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table perawat(kode_perawat TEXT primary key, nama_perawat TEXT,jenis_kelamin TEXT,jenjang_pendidikan TEXT,alamat TEXT, telepon TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists perawat");
    }

    public Boolean insertData (String kode,String nama, String jeniskelamin,String jenjangpendidikan, String alamat, String telfon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kode_perawat",kode);
        values.put("nama_perawat",nama);
        values.put("jenis_kelamin", jeniskelamin);
        values.put("jenjang_pendidikan", jenjangpendidikan);
        values.put("alamat", alamat);
        values.put("telepon", telfon);
        long result = db.insert("perawat", null,values);
        if (result==1) return false;
        else
            return true;
    }

    public Cursor tampilData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from perawat", null);
        return cursor;
    }

    public boolean hapusData (String kode){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from perawat where kode_perawat=?", new String[]{kode});
        if (cursor.getCount()>0){
            long result = db.delete("perawat", "kode_perawat=?", new String[]{kode});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Boolean editData (String kode, String nama, String jeniskelamin,String jenjangpendidikan, String alamat, String telfon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nama_perawat",nama);
        values.put("jenis_kelamin", jeniskelamin);
        values.put("jenjang_pendidikan", jenjangpendidikan);
        values.put("alamat", alamat);
        values.put("telepon", telfon);
        Cursor cursor = db.rawQuery("Select * from perawat where kode_perawat=?", new String[]{kode});
        if (cursor.getCount()>0){
            long result = db.update("perawat",values, "kode_perawat=?",new String[]{kode});
            if (result == -1){
                return false;
            }else {
                return true;
            }

        }else {
            return  false;
        }
    }

    public Boolean checkkodeperawat (String kode){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from perawat where kode_perawat=?", new String[] {kode});
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }






}
