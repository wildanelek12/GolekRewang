package com.codes.golekrewang.ui.riwayat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.codes.golekrewang.DetailRiwayat;
import com.codes.golekrewang.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    Button btn_riwayat;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_riwayat, container, false);
        btn_riwayat = root.findViewById(R.id.btn_detail);
        btn_riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DetailRiwayat.class));
            }
        });
        return root;
    }
}