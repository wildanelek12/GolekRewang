package com.codes.golekrewang;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PilihLayananActivity extends AppCompatActivity {

    private Button btnLanjut;
    private ConstraintLayout clAlat1;
    private TextView tvAlat1;
    private ConstraintLayout clAlat2;
    private TextView tvAlat2;
    private ConstraintLayout clPref1;
    private TextView tvPref1;
    private ConstraintLayout clPref2;
    private TextView tvPref2;

    public static String alat_kebersihan = "Disediakan Sendiri", lama_berlangganan, lama_berlangganan2, prefrensi_pekerja = "Laki - Laki", jumlah_pekerja, foto,jenis;
    public static int total;
    private EditText etLama;
    private Spinner spinnerLama;
    private EditText etJumlah;
    private CheckBox cbIzinkan;
    private TextView tvTotal;
    private TextView tvJenisLy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_layanan);
        initView();
        final Dialog dialog = new Dialog(PilihLayananActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        if (getIntent().getStringExtra("type").equals("asisten")){
            tvJenisLy.setText("Asisten Rumah aangga");
            dialog.setContentView(R.layout.item_agreement);
            Button dialogButton = (Button) dialog.findViewById(R.id.btn_tutup_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }else if (getIntent().getStringExtra("type").equals("lansia")){
            tvJenisLy.setText("Merawat Lansia");
            dialog.setContentView(R.layout.item_agreement_lansia);
            Button dialogButton = (Button) dialog.findViewById(R.id.btn_tutup_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }else if (getIntent().getStringExtra("type").equals("anak")){
            tvJenisLy.setText("Menjaga Anak");
            dialog.setContentView(R.layout.item_agreement_anak);
            Button dialogButton = (Button) dialog.findViewById(R.id.btn_tutup_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }else if (getIntent().getStringExtra("type").equals("sopir")){
            tvJenisLy.setText("Sopir");
            dialog.setContentView(R.layout.item_agreement_sopir);
            Button dialogButton = (Button) dialog.findViewById(R.id.btn_tutup_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }else if (getIntent().getStringExtra("type").equals("kebun")){
            tvJenisLy.setText("Tukang Kebun");
            dialog.setContentView(R.layout.item_agreement_kebun);
            Button dialogButton = (Button) dialog.findViewById(R.id.btn_tutup_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }


        etLama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (etLama.getText().toString().equals("")) {
                    etLama.setText("0");
                }

                if (spinnerLama.getSelectedItem().toString().equals("Hari")) {
                    total = Integer.parseInt(etLama.getText().toString()) * 120000;
                    tvTotal.setText("Rp " + String.valueOf(total));
                } else if (spinnerLama.getSelectedItem().toString().equals("Bulan")) {
                    total = Integer.parseInt(etLama.getText().toString()) * 3000000;
                    tvTotal.setText("Rp " + String.valueOf(total));
                } else {
                    total = Integer.parseInt(etLama.getText().toString()) * 30000000;
                    tvTotal.setText("Rp " + String.valueOf(total));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clAlat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alat_kebersihan = "Disediakan Sendiri";
                clAlat1.setBackgroundResource(R.drawable.bg_btn_checked);
                clAlat1.setPadding(25, 25, 25, 25);
                tvAlat1.setTextColor(Color.parseColor("#ffffff"));
                clAlat2.setBackgroundResource(R.drawable.bg_btn_not_checked);
                clAlat2.setPadding(25, 25, 25, 25);
                tvAlat2.setTextColor(Color.parseColor("#000000"));
            }
        });
        clAlat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alat_kebersihan = "Disediakan Perusahaan";
                clAlat2.setBackgroundResource(R.drawable.bg_btn_checked);
                clAlat2.setPadding(25, 25, 25, 25);
                tvAlat2.setTextColor(Color.parseColor("#ffffff"));
                clAlat1.setBackgroundResource(R.drawable.bg_btn_not_checked);
                clAlat1.setPadding(25, 25, 25, 25);
                tvAlat1.setTextColor(Color.parseColor("#000000"));
            }
        });
        clPref1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefrensi_pekerja = "Laki - Laki";
                clPref1.setBackgroundResource(R.drawable.bg_btn_checked);
                clPref1.setPadding(25, 25, 25, 25);
                tvPref1.setTextColor(Color.parseColor("#ffffff"));
                clPref2.setBackgroundResource(R.drawable.bg_btn_not_checked);
                clPref2.setPadding(25, 25, 25, 25);
                tvPref2.setTextColor(Color.parseColor("#000000"));
            }
        });
        clPref2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefrensi_pekerja = "Perempuan";
                clPref2.setBackgroundResource(R.drawable.bg_btn_checked);
                clPref2.setPadding(25, 25, 25, 25);
                tvPref2.setTextColor(Color.parseColor("#ffffff"));
                clPref1.setBackgroundResource(R.drawable.bg_btn_not_checked);
                clPref1.setPadding(25, 25, 25, 25);
                tvPref1.setTextColor(Color.parseColor("#000000"));
            }
        });


        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jenis = tvJenisLy.getText().toString();
                lama_berlangganan = etLama.getText().toString();
                lama_berlangganan2 = spinnerLama.getSelectedItem().toString();
                jumlah_pekerja = etJumlah.getText().toString();
                if (cbIzinkan.isChecked()) {
                    foto = "Boleh";
                } else {
                    foto = "Tidak Boleh";
                }
                startActivity(new Intent(PilihLayananActivity.this, ActivityJadwalTempat.class));
            }
        });
    }

    private void initView() {
        btnLanjut = (Button) findViewById(R.id.btn_lanjut);
        clAlat1 = (ConstraintLayout) findViewById(R.id.cl_alat_1);
        tvAlat1 = (TextView) findViewById(R.id.tv_alat_1);
        clAlat2 = (ConstraintLayout) findViewById(R.id.cl_alat_2);
        tvAlat2 = (TextView) findViewById(R.id.tv_alat_2);
        clPref1 = (ConstraintLayout) findViewById(R.id.cl_pref_1);
        tvPref1 = (TextView) findViewById(R.id.tv_pref_1);
        clPref2 = (ConstraintLayout) findViewById(R.id.cl_pref_2);
        tvPref2 = (TextView) findViewById(R.id.tv_pref2);
        etLama = (EditText) findViewById(R.id.et_lama);
        spinnerLama = (Spinner) findViewById(R.id.spinner_lama);
        etJumlah = (EditText) findViewById(R.id.et_jumlah);
        cbIzinkan = (CheckBox) findViewById(R.id.cb_izinkan);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        tvJenisLy = (TextView) findViewById(R.id.tv_jenis_ly);
    }
}