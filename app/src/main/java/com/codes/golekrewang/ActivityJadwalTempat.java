package com.codes.golekrewang;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivityJadwalTempat extends AppCompatActivity {

    private EditText etTglMulai;
    private EditText etWaktuMulai;
    private Button button;
    private TimePickerDialog timePickerDialog;
    final Calendar myCalendar = Calendar.getInstance();
    public static String tgl_mulai, waktu_mulai, alamat;
    private EditText etAlamat;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_tempat);
        initView();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        tvTotal.setText(String.valueOf(PilihLayananActivity.total));

        etTglMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ActivityJadwalTempat.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        etWaktuMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alamat = etAlamat.getText().toString();
                waktu_mulai = etWaktuMulai.getText().toString();
                tgl_mulai = etTglMulai.getText().toString();
                startActivity(new Intent(ActivityJadwalTempat.this, ActivityKonfirmasiPesanan.class));
            }
        });
    }

    private void initView() {
        etTglMulai = (EditText) findViewById(R.id.et_tgl_mulai);
        etWaktuMulai = (EditText) findViewById(R.id.et_waktu_mulai);
        button = (Button) findViewById(R.id.button);
        etAlamat = (EditText) findViewById(R.id.et_alamat);
        tvTotal = (TextView) findViewById(R.id.tv_total);
    }

    private void showTimeDialog() {

        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                etWaktuMulai.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
            }
        },
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etTglMulai.setText(sdf.format(myCalendar.getTime()));
    }
}