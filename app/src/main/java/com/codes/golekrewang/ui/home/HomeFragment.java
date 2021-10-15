package com.codes.golekrewang.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.codes.golekrewang.Model.User;
import com.codes.golekrewang.PilihLayananActivity;
import com.codes.golekrewang.R;
import com.codes.golekrewang.SewaAlatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    LinearLayout btn_menu_home_1, btn_menu_home6;
    private TextView tvNamaLengkap;
    DatabaseReference databaseReference;
    public static String nama, email;
    private LinearLayout btnMenuHome2;
    private LinearLayout btnMenuHome3;
    private LinearLayout btnMenuHome4;
    private LinearLayout btnMenuHome5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btn_menu_home_1 = root.findViewById(R.id.btn_menu_home_1);
        btnMenuHome2 = (LinearLayout) root.findViewById(R.id.btn_menu_home_2);
        btnMenuHome3 = (LinearLayout) root.findViewById(R.id.btn_menu_home_3);
        btnMenuHome4 = (LinearLayout) root.findViewById(R.id.btn_menu_home_4);
        btnMenuHome5 = (LinearLayout) root.findViewById(R.id.btn_menu_home_5);
        btn_menu_home6 = root.findViewById(R.id.btn_menu_home_6);
        tvNamaLengkap = (TextView) root.findViewById(R.id.tv_nama_lengkap);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(firebaseUser.getUid());
        btn_menu_home_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PilihLayananActivity.class);
                intent.putExtra("type","asisten");
                startActivity(intent);
            }
        });
        btnMenuHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PilihLayananActivity.class);
                intent.putExtra("type","lansia");
                startActivity(intent);
            }
        });
        btnMenuHome3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PilihLayananActivity.class);
                intent.putExtra("type","anak");
                startActivity(intent);
            }
        });
        btnMenuHome4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PilihLayananActivity.class);
                intent.putExtra("type","sopir");
                startActivity(intent);
            }
        });
        btnMenuHome5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PilihLayananActivity.class);
                intent.putExtra("type","kebun");
                startActivity(intent);
            }
        });

        btn_menu_home6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SewaAlatActivity.class);
                startActivity(intent);
            }
        });
        SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.orange);
        pDialog.setTitleText("Loading ...");
        pDialog.setCancelable(true);
        pDialog.show();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User post = dataSnapshot.getValue(User.class);
                tvNamaLengkap.setText(post.getNama());
                nama = post.getNama();
                email = post.getEmail();
                pDialog.dismissWithAnimation();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };
        databaseReference.addValueEventListener(postListener);
        return root;
    }

    private void initView() {

    }
}