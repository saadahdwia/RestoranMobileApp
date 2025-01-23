package com.example.restoran.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restoran.MainActivity;
import com.example.restoran.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegistrationActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail, editTextPassword, editTextConfirmationPassword;
    Button btnLogin, btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextName = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextConfirmationPassword = findViewById(R.id.password_conf);
        btnRegister = findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance();
        btnLogin = findViewById(R.id.btn_login);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void register() {
        // Ambil input dari pengguna
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmationPassword.getText().toString().trim();

        // Validasi input
        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email address");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password must be at least 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            editTextConfirmationPassword.setError("Passwords do not match");
            editTextConfirmationPassword.requestFocus();
            return;
        }

        // Proses registrasi dengan Firebase Auth
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name) // Masukkan nama pengguna
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(updateTask -> {
                                        if (updateTask.isSuccessful()) {
                                            Log.d("Registration", "User profile updated: " + name);
                                            // Log data pengguna yang diregistrasi
                                            Log.d("RegistrationActivity", "Registered user: Name = " + name + ", Email = " + email);

                                            // Tampilkan pesan sukses
                                            Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                            // Arahkan ke layar login atau halaman utama
                                            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    });
                        }
                    } else {
                        Log.e("Registration", "Registration failed: " + task.getException().getMessage());
                    }
                });

    }

    public void login() {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

    public void MainActivity(View view) {
        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
    }
}