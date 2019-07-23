package com.example.learnrealm;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNama,etAbsen,etKelas,etUmur,etAlamat;
    String nama,kelas,alamat;
    int id,absen,umur;
    Button btn_ubah, btn_hapus, btn_kembali;
    RealmHelper realmHelper;
    Realm realm;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Data Siswa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        // Inisialisasi
        etNama = findViewById(R.id.et_nama);
        etAbsen = findViewById(R.id.et_absen);
        etKelas = findViewById(R.id.et_kelas);
        etUmur = findViewById(R.id.et_umur);
        etAlamat = findViewById(R.id.et_alamat);
        btn_ubah = findViewById(R.id.btnUpdate);
        btn_hapus = findViewById(R.id.btnHapus);
//        btn_kembali = findViewById(R.id.btnCancel);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            id = bundle.getInt("id");
            nama = getIntent().getStringExtra("nama");
            absen = bundle.getInt("absen");
            kelas = getIntent().getStringExtra("kelas");
            umur = bundle.getInt("umur");
            alamat = getIntent().getStringExtra("alamat");

            etNama.setText(nama);
            etAbsen.setText(""+absen);
            etKelas.setText(kelas);
            etUmur.setText(""+umur);
            etAlamat.setText(alamat);

        }

//        btn_kembali.setOnClickListener(this);
        btn_hapus.setOnClickListener(this);
        btn_ubah.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_ubah){
            realmHelper.update(id , etNama.getText().toString(), Integer.parseInt(etAbsen.getText().toString()),etKelas.getText().toString(), Integer.parseInt(etUmur.getText().toString()),etAlamat.getText().toString() );
            Toast.makeText(DetailActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
            etNama.setText("");
            etAbsen.setText("");
            etKelas.setText("");
            etUmur.setText("");
            etAlamat.setText("");
            finish();
        }else if (v == btn_hapus) {
            realmHelper.delete(id);
            Toast.makeText(DetailActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
            finish();
        }
//        else if (v == btn_kembali) {
//            startActivity(new Intent(DetailActivity.this, SiswaActivity.class));
//            finish();
//        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Konfirmasi Pembatalan");
        builder.setMessage("Apakah anda yakin membatalkan update data siswa?");
        builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        return true;
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Konfirmasi Pembatalan");
        builder.setMessage("Apakah anda yakin membatalkan update data siswa?");
        builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
