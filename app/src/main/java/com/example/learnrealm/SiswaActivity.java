package com.example.learnrealm;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.learnrealm.adapter.SiswaAdapter;
import com.example.learnrealm.model.SiswaModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SiswaActivity extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    SiswaAdapter siswaAdapter;
    List<SiswaModel> siswaModels;
    private FloatingActionButton tambah;
    private static final int TIME_LIMIT = 1500;
    private static long backPressed;
    private Toolbar toolbar;
    SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);
        tambah = (FloatingActionButton) findViewById(R.id.myFAB);

        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Siswa");
//        getSupportActionBar().setIcon(R.drawable.iconapps);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        toolbar.setNavigationIcon(R.drawable.iconapps);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        swipeLayout = findViewById(R.id.swipe_container);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 2000);
                Toast.makeText(getApplicationContext(), "Daftar Siswa is up to date !", Toast.LENGTH_SHORT).show();// Delay in millis
            }
        });

        recyclerView = findViewById(R.id.rv_siswa);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //bentuk grid
        //recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        siswaModels = new ArrayList<>();

        siswaModels = realmHelper.getAllSiswa();

        show();



        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SiswaActivity.this, MainActivity.class));
            }
        });





//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SiswaActivity.this, DetailActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        siswaAdapter.notifyDataSetChanged();
        show();
    }

    public void show(){
        siswaAdapter = new SiswaAdapter(this, siswaModels);
        recyclerView.setAdapter(siswaAdapter);
    }

    @Override
    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()){
            super.onBackPressed();
        }else {
            Toast.makeText(getApplicationContext(),"Tekan lagi untuk keluar",Toast.LENGTH_SHORT).show();
        }
        backPressed =System.currentTimeMillis();
    }
}
