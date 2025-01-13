package com.example.restoran.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoran.R;
import com.example.restoran.adapters.HomeHorAdapter;
import com.example.restoran.databinding.FragmentHomeBinding;
import com.example.restoran.models.HomeHorModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView homeHorizontalRec;
    List<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container,false);

//        // Inisialisasi tombol
//        FloatingActionButton btn_whatsapp = root.findViewById(R.id.btn_whatsapp);
//
//        // Tambahkan aksi untuk tombol
//        btn_whatsapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Intent untuk membuka aplikasi WhatsApp
//                String phoneNumber = "+628123456789"; // Nomor telepon tujuan
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://wa.me/" + phoneNumber));
//                startActivity(intent);
//            }
//        });

        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);

        homeHorModelList = new ArrayList<>();

        homeHorModelList.add(new HomeHorModel(R.drawable.nasi_goreng,"Pizza"));
        homeHorModelList.add(new HomeHorModel(R.drawable.hamburger,"Hamburger"));
        homeHorModelList.add(new HomeHorModel(R.drawable.fried_potatoes,"Fries"));
        homeHorModelList.add(new HomeHorModel(R.drawable.ice_cream,"Ice Cream"));
        homeHorModelList.add(new HomeHorModel(R.drawable.nsandwich,"Sandwich"));

        homeHorAdapter = new HomeHorAdapter(getActivity(),homeHorModelList);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);
        return root;
    }
}