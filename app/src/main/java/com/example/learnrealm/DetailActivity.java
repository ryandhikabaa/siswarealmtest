package com.example.learnrealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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
        btn_kembali = findViewById(R.id.btnCancel);
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

        btn_kembali.setOnClickListener(this);
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
        }else if (v == btn_kembali) {
            startActivity(new Intent(DetailActivity.this, SiswaActivity.class));
            finish();
        }
    }
}
