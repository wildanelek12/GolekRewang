package com.codes.golekrewang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirm extends AppCompatActivity {

    private Button backToMenu;
    private TextView tvCode;
    private TextView tvBookingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        initView();
        tvCode.setText(ActivityKonfirmasiPesanan.code);
        tvBookingTime.setText("Booking Time : "+ActivityJadwalTempat.tgl_mulai+" "+ActivityJadwalTempat.waktu_mulai);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderConfirm.this, MainMenu.class));
            }
        });
    }


    private void initView() {
        backToMenu = (Button) findViewById(R.id.back_to_menu);
        tvCode = (TextView) findViewById(R.id.tv_code);
        tvBookingTime = (TextView) findViewById(R.id.tv_booking_time);
    }
}