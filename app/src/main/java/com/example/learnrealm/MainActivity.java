package com.example.learnrealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learnrealm.model.SiswaModel;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSimpan, btnTampil;
    EditText nama,absen,kelas,umur,alamat;
    String sNama,sKelas,sAlamat;
    Integer sAbsen,sUmur;
    Realm realm;
    RealmHelper realmHelper;
    SiswaModel siswaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnTampil = findViewById(R.id.btnTampil);
        nama = findViewById(R.id.et_nama);
        absen = findViewById(R.id.et_absen);
        kelas = findViewById(R.id.et_kelas);
        umur = findViewById(R.id.et_umur);
        alamat = findViewById(R.id.et_alamat);

        //Set up Realm
        Realm.init(MainActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        btnSimpan.setOnClickListener(this);
        btnTampil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSimpan){
            sNama = nama.getText().toString();
            sAbsen = Integer.parseInt(absen.getText().toString());
            sKelas = kelas.getText().toString();
            sUmur = Integer.parseInt(umur.getText().toString());
            sAlamat = alamat.getText().toString();

            if (!sAbsen.equals("") && !sNama.isEmpty()){
                siswaModel = new SiswaModel();
                siswaModel.setNama(sNama);
                siswaModel.setAbsen(sAbsen);
                siswaModel.setKelas(sKelas);
                siswaModel.setUmur(sUmur);
                siswaModel.setAlamat(sAlamat);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(siswaModel);
                Log.d("nama", "onClick: "+siswaModel.getNama());
                Log.d("nama", "onClick: "+siswaModel.getAbsen());
                Log.d("nama", "onClick: "+siswaModel.getKelas());
                Log.d("nama", "onClick: "+siswaModel.getUmur());
                Log.d("nama", "onClick: "+siswaModel.getAlamat());

                Toast.makeText(MainActivity.this, "Berhasil Disimpan!", Toast.LENGTH_SHORT).show();

                nama.setText("");
                absen.setText("");
                kelas.setText("");
                umur.setText("");
                alamat.setText("");

                startActivity(new Intent(MainActivity.this, SiswaActivity.class));
            }else {
                Toast.makeText(MainActivity.this, "Terdapat inputan yang kosong", Toast.LENGTH_SHORT).show();
            }
        }else if (v == btnTampil){
            startActivity(new Intent(MainActivity.this, SiswaActivity.class));
        }
    }

}
