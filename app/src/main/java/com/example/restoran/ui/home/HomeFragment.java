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

        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);

        homeHorModelList = new ArrayList<>();

        homeHorModelList.add(new HomeHorModel(R.drawable.brownis_telur,"Brownis Telur"));
        homeHorModelList.add(new HomeHorModel(R.drawable.pai_berry,"Pai Berry"));
        homeHorModelList.add(new HomeHorModel(R.drawable.fillet_ikan,"Fillet Ikan Dengan Sayur"));
        homeHorModelList.add(new HomeHorModel(R.drawable.salad,"Salad Segar"));
        homeHorModelList.add(new HomeHorModel(R.drawable.sparkling_lemon,"Sparkling Lemon"));
        homeHorModelList.add(new HomeHorModel(R.drawable.jus_apel,"Jus Apel"));
        homeHorModelList.add(new HomeHorModel(R.drawable.air_selasih,"Air Selasih"));
        homeHorModelList.add(new HomeHorModel(R.drawable.teh_chamomile,"Teh Chamomile"));

        homeHorAdapter = new HomeHorAdapter(homeHorModelList, getActivity());

        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);
        return root;
    }
}