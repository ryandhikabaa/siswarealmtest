package com.example.learnrealm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.learnrealm.DetailActivity;
import com.example.learnrealm.R;
import com.example.learnrealm.model.SiswaModel;

import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.MyViewHolder> {
    private List<SiswaModel> siswaModels;
    Context context;

    public SiswaAdapter(Context context, List<SiswaModel> siswaModels){
        this.context = context;
        this.siswaModels = siswaModels;
    }

    @Override
    public SiswaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_siswa, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SiswaAdapter.MyViewHolder holder, int position) {
        final SiswaModel model = siswaModels.get(position);
        holder.nama.setText(model.getNama());
//        holder.absen.setText(""+model.getAbsen());
        holder.kelas.setText(model.getKelas());
//        holder.umur.setText(""+model.getUmur());
//        holder.alamat.setText(model.getAlamat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("id", model.getId());
                intent.putExtra("nama", model.getNama());
                intent.putExtra("absen", model.getAbsen());
                intent.putExtra("kelas", model.getKelas());
                intent.putExtra("umur", model.getUmur());
                intent.putExtra("alamat", model.getAlamat());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return siswaModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama,absen,kelas,umur,alamat;

        public MyViewHolder(View itemView){
            super(itemView);
            kelas = itemView.findViewById(R.id.tvKelas);
            nama = itemView.findViewById(R.id.tvNama);

        }
    }
}


