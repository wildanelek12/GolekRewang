package com.codes.golekrewang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.codes.golekrewang.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity extends AppCompatActivity {

    private TextView goToLog;
    private Button register;
    private EditText etRegName;
    private EditText etRegEmail;
    private EditText etRegNo;
    private EditText etRegPw;
    private EditText etRegKonfirmPw;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        goToLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (!etRegPw.getText().toString().equals(etRegKonfirmPw.getText().toString())) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Password Tidak Sama")
                            .show();
                } else if (etRegEmail.getText().toString().matches("") || etRegName.getText().toString().matches("") || etRegPw.getText().toString().matches("") || etRegKonfirmPw.getText().toString().matches("")) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Semua Wajib Di isi !")
                            .show();
                } else if (!etRegEmail.getText().toString().trim().matches(emailPattern)) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Format Email Salah")
                            .show();
                } else if (etRegPw.getText().toString().length() <= 8) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Password Harus Lebih dari 8 Karakter")
                            .show();
                } else {
                    storeData();
                }


            }

        });
    }

    private void initView() {
        goToLog = (TextView) findViewById(R.id.go_to_log);
        etRegEmail = (EditText) findViewById(R.id.et_reg_email);
        etRegName = (EditText) findViewById(R.id.et_reg_name);
        etRegPw = (EditText) findViewById(R.id.et_reg_pw);
        etRegNo = (EditText) findViewById(R.id.et_reg_no);
        etRegKonfirmPw = (EditText) findViewById(R.id.et_reg_konfirm_pw);
        register = (Button) findViewById(R.id.register);
    }

    private void storeData() {
        SweetAlertDialog pDialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.orange);
        pDialog.setTitleText("Loading ...");
        pDialog.setCancelable(true);
        pDialog.show();
        mAuth.createUserWithEmailAndPassword(etRegEmail.getText().toString(), etRegPw.getText().toString())
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            User user1 = new User(currentUser.getUid(),etRegName.getText().toString(),etRegEmail.getText().toString(),etRegPw.getText().toString(),etRegNo.getText().toString());
                            databaseReference.child("user").child(currentUser.getUid()).setValue(user1);
                            Intent intent = new Intent(RegisterActivity.this, MainMenu.class);
                            startActivity(intent);
                            pDialog.dismissWithAnimation();
                            finish();

                        } else {
                            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("Something went wrong!")
                                    .show();
                            pDialog.dismissWithAnimation();
                        }
                    }
                });
    }
}