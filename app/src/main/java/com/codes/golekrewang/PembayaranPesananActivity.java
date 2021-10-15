package com.codes.golekrewang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PembayaranPesananActivity extends AppCompatActivity {

    private Button btnSudahBayar;
    DatabaseReference databaseReference;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_pesanan);
        initView();
        tvTotal.setText("Rp "+String.valueOf(KonfirmasiPembayaranPesanan.total));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("pesanan").child(KonfirmasiPembayaranPesanan.child);
        btnSudahBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("status").setValue("Diproses");
                databaseReference.child("nama_pekerja").setValue(KonfirmasiPembayaranPesanan.nama_pekerja);
                new SweetAlertDialog(PembayaranPesananActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Perhatian")
                        .setContentText("Pesanan anda sedang dalam proses !")
                        .setConfirmText("Oke")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                startActivity(new Intent(PembayaranPesananActivity.this, MainMenu.class));
                            }
                        })
                        .show();
            }
        });
    }

    private void initView() {
        btnSudahBayar = (Button) findViewById(R.id.btn_sudah_bayar);
        tvTotal = (TextView) findViewById(R.id.tv_total);
    }
}