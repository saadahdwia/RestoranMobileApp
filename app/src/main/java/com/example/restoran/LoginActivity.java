package com.example.restoran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void register(View view) {
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    public void MainActivity(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}