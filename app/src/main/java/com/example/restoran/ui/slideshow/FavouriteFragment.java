package com.example.restoran.ui.slideshow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.restoran.R;

public class FavouriteFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container,false);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://maps.app.goo.gl/hifP1zYXHYvuFYNPA"));
        startActivity(intent);

        Intent intent1= new Intent(Intent.ACTION_SEND);
        intent1.setType("text/plain");
        intent1.putExtra(Intent.EXTRA_SUBJECT, "Restoran Savaris");
        intent1.putExtra(Intent.EXTRA_TEXT, "Ayo kunjungi Restoran Savaris! Lokasi: Jl. ABC No.123, Surabaya.");
        startActivity(Intent.createChooser(intent1, "Bagikan informasi melalui"));

        Intent intent2 = new Intent(Intent.ACTION_DIAL);
        intent2.setData(Uri.parse("tel:+628123456789"));
        startActivity(intent2);



        return root;
    }




}