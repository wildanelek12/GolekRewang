package com.codes.golekrewang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class KonfirmasiPembayaranPesanan extends AppCompatActivity {

    private Button btnLanjut;
    private RadioButton radioButton;
    private RadioButton radioButton2;
    private TextView tvJenisLayanan;
    private TextView tvTglMulai;
    private TextView tvLama;
    private TextView tvJk;
    private TextView tvTotal;
    private TextView tvPesan;
    private TextView tvDetail1;
    private ImageView check1;
    private ImageView notChecked1;
    private CardView cardPekerja2;
    private TextView tvDetail2;
    private ImageView check2;
    private ImageView notChecked2;
    private TextView tvDetail3;
    private ImageView check3;
    private ImageView notChecked3;

    public static String nama_pekerja="Indahwati",child;
    public static int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran_pesanan);
        initView();


        tvJenisLayanan.setText(getIntent().getStringExtra("jenis"));
        tvTglMulai.setText(getIntent().getStringExtra("mulai"));
        tvLama.setText(getIntent().getStringExtra("durasi"));
        tvJk.setText(getIntent().getStringExtra("jk"));
        tvTotal.setText(getIntent().getStringExtra("jumlah"));
        tvPesan.setText(getIntent().getStringExtra("pesan"));
        child = getIntent().getStringExtra("child");
        total = getIntent().getIntExtra("total",0);

        tvDetail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KonfirmasiPembayaranPesanan.this,DetailPekerja1.class);
                startActivity(intent);
            }
        });
        tvDetail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KonfirmasiPembayaranPesanan.this,DetailPekerja2.class);
                startActivity(intent);
            }
        });
        tvDetail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KonfirmasiPembayaranPesanan.this,DetailPekerja3.class);
                startActivity(intent);
            }
        });


        notChecked1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama_pekerja = "Indahwati";
                notChecked1.setVisibility(View.INVISIBLE);
                notChecked2.setVisibility(View.VISIBLE);
                notChecked3.setVisibility(View.VISIBLE);

                check1.setVisibility(View.VISIBLE);
                check2.setVisibility(View.INVISIBLE);
                check3.setVisibility(View.INVISIBLE);

            }
        });
        notChecked2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama_pekerja = "Budi Hermawan";
                notChecked1.setVisibility(View.VISIBLE);
                notChecked2.setVisibility(View.INVISIBLE);
                notChecked3.setVisibility(View.VISIBLE);

                check1.setVisibility(View.INVISIBLE);
                check2.setVisibility(View.VISIBLE);
                check3.setVisibility(View.INVISIBLE);

            }
        });
        notChecked3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama_pekerja = "Kusmiani";
                notChecked1.setVisibility(View.VISIBLE);
                notChecked2.setVisibility(View.VISIBLE);
                notChecked3.setVisibility(View.INVISIBLE);

                check1.setVisibility(View.INVISIBLE);
                check2.setVisibility(View.INVISIBLE);
                check3.setVisibility(View.VISIBLE);

            }
        });


        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KonfirmasiPembayaranPesanan.this, PembayaranPesananActivity.class));
            }
        });


    }

    private void initView() {
        btnLanjut = (Button) findViewById(R.id.btn_lanjut);

        tvJenisLayanan = (TextView) findViewById(R.id.tv_jenis_layanan);
        tvTglMulai = (TextView) findViewById(R.id.tv_tgl_mulai);
        tvLama = (TextView) findViewById(R.id.tv_lama);
        tvJk = (TextView) findViewById(R.id.tv_jk);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        tvPesan = (TextView) findViewById(R.id.tv_pesan);
        tvDetail1 = (TextView) findViewById(R.id.tv_detail_1);
        check1 = (ImageView) findViewById(R.id.check_1);
        notChecked1 = (ImageView) findViewById(R.id.not_checked_1);
        cardPekerja2 = (CardView) findViewById(R.id.card_pekerja2);
        tvDetail2 = (TextView) findViewById(R.id.tv_detail_2);
        check2 = (ImageView) findViewById(R.id.check_2);
        notChecked2 = (ImageView) findViewById(R.id.not_checked_2);
        tvDetail3 = (TextView) findViewById(R.id.tv_detail_3);
        check3 = (ImageView) findViewById(R.id.check_3);
        notChecked3 = (ImageView) findViewById(R.id.not_checked_3);
    }
}