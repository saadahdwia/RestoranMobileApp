package com.example.restoran.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoran.MainActivity;
import com.example.restoran.R;
import com.example.restoran.activities.WelcomeActivity;
import com.example.restoran.adapters.HomeHorAdapter;
import com.example.restoran.adapters.HomeVerAdapter;
import com.example.restoran.databinding.FragmentHomeBinding;
import com.example.restoran.models.HomeHorModel;
import com.example.restoran.models.HomeVerModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView homeHorizontalRec, homeVerticalRec;
    List<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    /////////vertical
    List<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;

    TextView textViewFullName;


    void displayUserData() {
        // Dapatkan FirebaseUser
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Ambil data dari FirebaseUser
            String uid = user.getUid();
            String email = user.getEmail();
            String displayName = user.getDisplayName() != null ? user.getDisplayName() : "No Display Name";
            Uri photoUrl = user.getPhotoUrl();

            // Tampilkan data di UI
            textViewFullName.setText("Name: " + displayName);

            // Catat data lengkap di log
            Log.d("UserData", "User ID: " + uid);
            Log.d("UserData", "Email: " + email);
            Log.d("UserData", "Display Name: " + displayName);
            if (photoUrl != null) {
                Log.d("UserData", "Photo URL: " + photoUrl.toString());
            } else {
                Log.d("UserData", "Photo URL: Not Set");
            }

            // Tampilkan UID jika diperlukan
            Log.d("UserData", "UID: " + uid);
        } else {
            // Jika tidak ada pengguna login
            Log.d("UserData", "No user is logged in");
            textViewFullName.setText("No user logged in");
        }
    }




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container,false);

        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);
        textViewFullName = root.findViewById(R.id.textView8);

        displayUserData();
        /////////////Horizontal RecyclerView
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


        /////////////Vertical RecyclerView
        homeVerModelList = new ArrayList<>();

        homeVerModelList.add(new HomeVerModel(R.drawable.pai_berry,"Pai Berry","10.00-23.00 Wib","4.0","Start - Rp.15.000"));
        homeVerModelList.add(new HomeVerModel(R.drawable.brownis_telur,"Brownis Telur","10.00-23.00 Wib","4.0","Start - Rp.15.000"));
        homeVerModelList.add(new HomeVerModel(R.drawable.fillet_ikan,"Fillet Ikan","10.00-23.00 Wib","4.0","Start - Rp.15.000"));
        homeVerModelList.add(new HomeVerModel(R.drawable.salad,"Salad Segar","10.00-23.00 Wib","4.0","Start - Rp.15.000"));
        homeVerModelList.add(new HomeVerModel(R.drawable.sparkling_lemon,"Sparkling Lemon","10.00-23.00 Wib","4.0","Start - Rp.15.000"));
        homeVerModelList.add(new HomeVerModel(R.drawable.jus_apel,"Jus Apel","10.00-23.00 Wib","4.0","Start - Rp.15.000"));
        homeVerModelList.add(new HomeVerModel(R.drawable.air_selasih,"Sparkling Lemon","10.00-23.00 Wib","4.0","Start - Rp.15.000"));
        homeVerModelList.add(new HomeVerModel(R.drawable.sparkling_lemon,"Sparkling Lemon","10.00-23.00 Wib","4.0","Start - Rp.15.000"));

        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModelList);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        homeVerticalRec.setHasFixedSize(true);
        homeVerticalRec.setNestedScrollingEnabled(false);

        return root;
    }
}