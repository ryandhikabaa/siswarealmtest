package com.example.learnrealm;

import android.util.Log;

import com.example.learnrealm.model.SiswaModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

//pembantu dalam proses mengakses database.
public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final SiswaModel siswaModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(SiswaModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    siswaModel.setId(nextId);
                    SiswaModel model = realm.copyToRealm(siswaModel);
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<SiswaModel> getAllSiswa(){
        RealmResults<SiswaModel> results = realm.where(SiswaModel.class).findAll();
        return results;
    }

    // untuk meng-update data
    public void update(final Integer id,final String nama, final Integer absen, final String kelas, final Integer umur, final String alamat){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SiswaModel model = realm.where(SiswaModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setNama(nama);
                model.setAbsen(absen);
                model.setKelas(kelas);
                model.setUmur(umur);
                model.setAlamat(alamat);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("pppp", "onSuccess: Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    // untuk menghapus data
    public void delete(Integer id){
        final RealmResults<SiswaModel> model = realm.where(SiswaModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}


