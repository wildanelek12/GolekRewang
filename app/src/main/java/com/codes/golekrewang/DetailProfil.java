package com.codes.golekrewang;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codes.golekrewang.ui.home.HomeFragment;

public class DetailProfil extends AppCompatActivity {

    private TextView tvNama;
    private TextView tvNamaDetail;
    private TextView tvEmailDetail;
    private Button btnEditProfil;
    private Button btnEditPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profil);

        initView();
        tvNama.setText(HomeFragment.nama);
        tvNamaDetail.setText(HomeFragment.nama);
        tvEmailDetail.setText(HomeFragment.email);

    }

    private void initView() {
        tvNama = (TextView) findViewById(R.id.tv_nama);
        tvNamaDetail = (TextView) findViewById(R.id.tv_nama_detail);
        tvEmailDetail = (TextView) findViewById(R.id.tv_email_detail);
        btnEditProfil = (Button) findViewById(R.id.btn_edit_profil);
        btnEditPassword = (Button) findViewById(R.id.btn_edit_password);
    }
}