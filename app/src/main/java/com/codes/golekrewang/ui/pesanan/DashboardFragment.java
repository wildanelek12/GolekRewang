package com.codes.golekrewang.ui.pesanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codes.golekrewang.Adapter.AdapterPesanan;
import com.codes.golekrewang.Model.Pesanan;
import com.codes.golekrewang.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    Button btn_show_detail;
    private RecyclerView rvPesanan;
    ArrayList<Pesanan>pesananArrayList;
    AdapterPesanan adapterPesanan;
    

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pesanan, container, false);

        rvPesanan = (RecyclerView) root.findViewById(R.id.rv_pesanan);
        pesananArrayList = new ArrayList<>();
        rvPesanan.setLayoutManager(new LinearLayoutManager(getContext()));
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("pesanan");
        reference2.orderByChild("user_id").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pesananArrayList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        Pesanan l = npsnapshot.getValue(Pesanan.class);
                        pesananArrayList.add(l);
                    }
                    adapterPesanan = new AdapterPesanan(getContext(), pesananArrayList);
                    adapterPesanan.notifyDataSetChanged();
                    rvPesanan.setAdapter(adapterPesanan);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return root;
    }


}