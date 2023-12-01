package com.example.tomas.examen;

import static com.example.tomas.examen.Registro.crearListaDesdeMap;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Aquí asumimos que ya tienes una lista de registros
        RegistroAdapter adapter = new RegistroAdapter(crearListaDesdeMap( MainActivity.registros));
        recyclerView.setAdapter(adapter);
    }

}
