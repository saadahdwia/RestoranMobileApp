package com.example.restoran;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restoran.activities.WelcomeActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restoran.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    void displayUserData() {

        // Akses header layout
        View headerView = binding.navView.getHeaderView(0);
        // Referensi ke elemen di nav_header_main
        TextView textUserName = headerView.findViewById(R.id.id_text_view_user_fullname);
        TextView textUserEmail = headerView.findViewById(R.id.id_text_view_user_email);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Ambil data dari FirebaseUser
            String uid = user.getUid();
            String email = user.getEmail();
            String displayName = user.getDisplayName() != null ? user.getDisplayName() : "No Display Name";
            Uri photoUrl = user.getPhotoUrl();

            // Tampilkan data di UI
            textUserEmail.setText(displayName);
            textUserName.setText(email);


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
            textUserEmail.setText("No user logged in");
            textUserName.setText("No user logged in");

        }
    }
    void logout() {
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();

                if (user != null) {
                    mAuth.signOut();
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", false); // Menyimpan status login
                    editor.putString("userEmail", ""); // Menyimpan email pengguna
                    editor.apply(); // Terapkan perubahan
                    // Logout dari Firebase
                    FirebaseAuth.getInstance().signOut();

                    Log.d("Logout", "User " + user.getEmail() + " logged out successfully");
                    Toast.makeText(MainActivity.this,"logged out successfully",Toast.LENGTH_SHORT).show();
                    // Arahkan ke halaman login
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Hapus semua aktivitas sebelumnya
                    startActivity(intent);
                    finish(); // Tutup MainActivity
                } else {
                    // Log jika tidak ada pengguna yang login
                    Log.d("Logout", "No user is logged in");
                    Toast.makeText(MainActivity.this, "No user is logged in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Inisialisasi NavigationView

        displayUserData();
        logout();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_daily_meal, R.id.nav_about, R.id.nav_my_cart)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}