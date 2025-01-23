package com.example.restoran.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restoran.MainActivity;
import com.example.restoran.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail , editTextPassword;
    Button btnRegister, btnLogin;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    public void register() {
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }
    public void login() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        // Firebase Auth Email/Password Login
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Login berhasil
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Simpan status login dan email di SharedPreferences
                            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("isLoggedIn", true); // Menyimpan status login
                            editor.putString("userEmail", user.getEmail()); // Menyimpan email pengguna
                            editor.apply(); // Terapkan perubahan

                            // Log untuk debugging
                            Log.d("Login", "User email saved: " + user.getEmail());
                        }

                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        // Login gagal
                        Toast.makeText(LoginActivity.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_login){
            login();
        } else if(v.getId() == R.id.btn_register){
            register();
        }
    }
}