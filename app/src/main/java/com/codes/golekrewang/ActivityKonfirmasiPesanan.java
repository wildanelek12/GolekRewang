package com.codes.golekrewang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codes.golekrewang.Model.Pesanan;
import com.codes.golekrewang.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityKonfirmasiPesanan extends AppCompatActivity {

    private Button btnLanjut;
    private TextView etPesan;
    private TextView tvPenyedia;
    private TextView tvMulai;
    private TextView tvLama;
    private TextView tvJk;
    private TextView tvJumlah;
    private Spinner spinnerBank;
    private TextView tvTotal;
    DatabaseReference databaseReference;
    public static String pesan,code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pesanan);
        initView();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("pesanan");
        tvPenyedia.setText(PilihLayananActivity.alat_kebersihan);
        tvMulai.setText(ActivityJadwalTempat.tgl_mulai+" "+ActivityJadwalTempat.waktu_mulai);
        tvLama.setText(PilihLayananActivity.lama_berlangganan+" " +PilihLayananActivity.lama_berlangganan2);
        tvJk.setText(PilihLayananActivity.prefrensi_pekerja);
        tvJumlah.setText(PilihLayananActivity.jumlah_pekerja);
        tvTotal.setText(String.valueOf(PilihLayananActivity.total));



        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = getAlphaNumericString(5);
                pesan = etPesan.getText().toString();
                String mChild = databaseReference.push().getKey();
                Pesanan pesanan = new Pesanan(PilihLayananActivity.jenis,PilihLayananActivity.alat_kebersihan,tvLama.getText().toString(),PilihLayananActivity.prefrensi_pekerja,PilihLayananActivity.jumlah_pekerja,PilihLayananActivity.foto,pesan,code, FirebaseAuth.getInstance().getCurrentUser().getUid(),"Dikonfirmasi","null",PilihLayananActivity.total,mChild,spinnerBank.getSelectedItem().toString(),tvMulai.getText().toString(),ActivityJadwalTempat.alamat, HomeFragment.nama);
                databaseReference.child(mChild).setValue(pesanan);
                new SweetAlertDialog(ActivityKonfirmasiPesanan.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Perhatian")
                        .setContentText("Data sudah di setorkan")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                startActivity(new Intent(ActivityKonfirmasiPesanan.this, OrderConfirm.class));
                            }
                        })
                        .show();

            }
        });

    }

    private void initView() {
        btnLanjut = (Button) findViewById(R.id.btn_lanjut);
        etPesan = (TextView) findViewById(R.id.et_pesan);
        tvPenyedia = (TextView) findViewById(R.id.tv_penyedia);
        tvMulai = (TextView) findViewById(R.id.tv_mulai);
        tvLama = (TextView) findViewById(R.id.tv_lama);
        tvJk = (TextView) findViewById(R.id.tv_jk);
        tvJumlah = (TextView) findViewById(R.id.tv_jumlah);
        spinnerBank = (Spinner) findViewById(R.id.spinner_bank);
        tvTotal = (TextView) findViewById(R.id.tv_total);
    }
    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}