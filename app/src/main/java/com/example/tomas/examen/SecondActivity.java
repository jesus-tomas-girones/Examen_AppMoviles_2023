package com.example.tomas.examen;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//APARTADO 5
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textViewPassword = findViewById(R.id.textView);
        String password = getIntent().getStringExtra("password");
        textViewPassword.setText(password);
    }
}
