package com.uasbryanchristopher202102276.uas_202102276;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Perawat extends AppCompatActivity {


    EditText kode,nama,jenis,jenjang,alamat,telepon;
    Button simpan,tampil,hapus,edit;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perawat);

        kode = findViewById(R.id.edtkode);
        nama = findViewById(R.id.edtnama);
        jenis = findViewById(R.id.edtjk);
        jenjang = findViewById(R.id.edtjenjang);
        alamat = findViewById(R.id.edtalamat);
        telepon = findViewById(R.id.edttelepon);
        simpan = findViewById(R.id.btnsimpan);
        tampil = findViewById(R.id.btntampil);
        hapus = findViewById(R.id.btnhapus);
        edit = findViewById(R.id.btnedit);
        db = new DBHelper(this);


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  isikode = kode.getText().toString();
                String isinama = nama.getText().toString();
                String isijenis = jenis.getText().toString();
                String isijenjang =jenjang.getText().toString();
                String isialamat = alamat.getText().toString();
                String isitelepon = telepon.getText().toString();

                if (TextUtils.isEmpty(isikode) || TextUtils.isEmpty(isinama) || TextUtils.isEmpty(isijenis)
                        || TextUtils.isEmpty(isijenjang) || TextUtils.isEmpty(isialamat) || TextUtils.isEmpty(isitelepon)){
                    Toast.makeText(Perawat.this,"Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                }else {
                    Boolean checkkode = db.checkkodeperawat(isikode);
                    if (checkkode == false){
                        Boolean insert = db.insertData(isikode,isinama,isijenis,isijenjang,isialamat,isitelepon);
                        if (insert == true){
                            Toast.makeText(Perawat.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            //   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            //  startActivity(intent);
                        }else {
                            Toast.makeText(Perawat.this,"Data gagal disimpan", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(Perawat.this,"Data Perawat Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.tampilData();
                if(res.getCount() == 0){
                    Toast.makeText(Perawat.this,"Tidak ada Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Kode Perawat : " + res.getString(0) + "\n");
                    buffer.append("Nama Perawat : " + res.getString(1) + "\n");
                    buffer.append("Jenis Kelamin : " + res.getString(2) + "\n");
                    buffer.append("Jenjang Pendidikan : " + res.getString(3) + "\n");
                    buffer.append("Alamat : " + res.getString(4) + "\n\n");
                    buffer.append("Telepon : " + res.getString(4) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Perawat.this);
                builder.setCancelable(true);
                builder.setTitle("Data Perawat");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kp = kode.getText().toString();
                Boolean cekHapusData = db.hapusData(kp);
                if (cekHapusData == true)
                    Toast.makeText(Perawat.this,"Data Terhapus", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Perawat.this,"Data Tidak Ada", Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  isikode = kode.getText().toString();
                String isinama = nama.getText().toString();
                String isijenis = jenis.getText().toString();
                String isijenjang =jenjang.getText().toString();
                String isialamat = alamat.getText().toString();
                String isitelepon = telepon.getText().toString();

                if (TextUtils.isEmpty(isikode) || TextUtils.isEmpty(isinama) || TextUtils.isEmpty(isijenis)
                        || TextUtils.isEmpty(isijenjang) || TextUtils.isEmpty(isialamat)|| TextUtils.isEmpty(isitelepon)){
                    Toast.makeText(Perawat.this,"Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                }else {
                    Boolean edit = db.editData(isikode, isinama, isijenis, isijenjang, isialamat,isitelepon);
                    if (edit == true) {
                        Toast.makeText(Perawat.this, "Data Berhasil di Edit", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Perawat.this, "Data Gagal di Edit", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }
}