package com.codes.golekrewang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.golekrewang.KonfirmasiPembayaranPesanan;
import com.codes.golekrewang.Model.Pesanan;
import com.codes.golekrewang.R;

import java.util.ArrayList;

public class AdapterPesanan extends RecyclerView.Adapter<AdapterPesanan.PesananViewHolder> {
    ArrayList<Pesanan> pesananList;
    Context context;


    public AdapterPesanan(Context context, ArrayList<Pesanan> pesananList) {
        this.pesananList = pesananList;
        this.context = context;
    }

    @NonNull
    @Override
    public PesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan, parent, false);
        PesananViewHolder holder = new PesananViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PesananViewHolder holder, int position) {
        Pesanan pesanan = pesananList.get(position);
        holder.tvCode.setText(pesanan.getCode());
        holder.tvBiaya.setText("Rp "+String.valueOf(pesanan.getTotal()));
        holder.tvJenisLayanan.setText(pesanan.getJenis_layanan());
        holder.tvStatus.setText(pesanan.getStatus());
        holder.tvTime.setText(pesanan.getWaktu_mulai());
        holder.showDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KonfirmasiPembayaranPesanan.class);
                intent.putExtra("jenis",pesanan.getJenis_layanan());
                intent.putExtra("mulai",pesanan.getWaktu_mulai());
                intent.putExtra("durasi",pesanan.getLama_berlangganan());
                intent.putExtra("jk",pesanan.getPreferensi_pekerja());
                intent.putExtra("jumlah",pesanan.getJumlah_pekerja());
                intent.putExtra("pesan",pesanan.getPesan());
                intent.putExtra("child",pesanan.getChild());
                intent.putExtra("total",pesanan.getTotal());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pesananList.size();
    }


    public class PesananViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCode;
        private TextView tvJenisLayanan;
        private TextView tvTime;
        private TextView tvBiaya;
        private TextView tvStatus;
        private Button showDetail;


        public PesananViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code);
            tvJenisLayanan = (TextView) itemView.findViewById(R.id.tv_jenis_layanan);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvBiaya = (TextView) itemView.findViewById(R.id.tv_biaya);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
            showDetail = (Button) itemView.findViewById(R.id.show_detail);
        }
    }
}