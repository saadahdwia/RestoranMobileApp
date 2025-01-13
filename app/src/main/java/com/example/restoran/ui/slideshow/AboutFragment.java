package com.example.restoran.ui.slideshow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.restoran.R;

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout fragment_about
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        // Inisialisasi tombol
        Button btnOpenMaps = root.findViewById(R.id.btn_open_maps);
        Button btnShareLocation = root.findViewById(R.id.btn_share_location);
        Button btnCallRestaurant = root.findViewById(R.id.btn_call_restaurant);

        // Tambahkan OnClickListener ke tombol Open Maps
        btnOpenMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://maps.app.goo.gl/hifP1zYXHYvuFYNPA"));
                startActivity(intent);
            }
        });

        // Tambahkan OnClickListener ke tombol Share Location
        btnShareLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Restoran Savaris");
                intent.putExtra(Intent.EXTRA_TEXT, "Ayo kunjungi Restoran Savaris! Lokasi: Jl. ABC No.123, Surabaya.");
                startActivity(Intent.createChooser(intent, "Bagikan informasi melalui"));
            }
        });

        // Tambahkan OnClickListener ke tombol Call Restaurant
        btnCallRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+628123456789"));
                startActivity(intent);
            }
        });

        return root;
    }
}
