package com.example.tomas.examen;

import static com.example.tomas.examen.Registro.contarUsuariosConectados;
import static com.example.tomas.examen.Registro.crearMapDesdeListas;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements SensorEventListener { //APARTADO 8 Sensor

    //APARTADO 3 Java
    List<String> USUARIO = Arrays.asList("ajuan", "pmati", "pmati", "ajuan", "pmati");
    List<Integer> MINUTO = Arrays.asList(1, 3, 4, 5, 8);
    List<String> TIPO = Arrays.asList("E", "E", "S", "S", "E");
    //APARTADO 6
    public static Map<Integer, Registro> registros; // Lo hacemos static para poder acceder a él desde RecyclerViewActivity
    //APARTADO 8 Sensor
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //APARTADO 3.b) Java
        registros = crearMapDesdeListas(USUARIO, MINUTO, TIPO);
        Log.d("Registro", "Registros: " + registros);

        //APARTADO 4.a) Algoritmo
        int usuariosConectados = contarUsuariosConectados(registros);
        Log.d("Registro", "Usuarios conectados: " + usuariosConectados);

        //APARTADO 4.b) Algoritmo
        //No es necesario crear un método. LO hacemos directamente.
        Map<String, Integer> tiempoPorUsuario = new HashMap<>();
        Map<String, Integer> ultimoIngreso = new HashMap<>();
        for (Registro registro : registros.values()) {
            String usuario = registro.getUsuario();
            int minuto = registro.getMinuto();
            String tipo = registro.getTipo();
            if (tipo.equals("E")) {
                ultimoIngreso.put(usuario, minuto);
                tiempoPorUsuario.putIfAbsent(usuario, 0);
            } else if (tipo.equals("S") && ultimoIngreso.containsKey(usuario)) {
                int tiempoEnSesion = minuto - ultimoIngreso.get(usuario);
                tiempoPorUsuario.put(usuario, tiempoPorUsuario.get(usuario) + tiempoEnSesion);
                ultimoIngreso.remove(usuario);
            }
        }
        // Añadir tiempo para usuarios aún conectados
        ultimoIngreso.forEach((usuario, inicioSesion) -> {
            int tiempoActual = 10 - inicioSesion; // Asumiendo que el tiempo actual es 10
            tiempoPorUsuario.put(usuario, tiempoPorUsuario.get(usuario) + tiempoActual);
        });
        Log.d("Registro", "Tiempo por usuario: " + tiempoPorUsuario);

        //APARTADO 5 Actividad
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.editTextTextPassword);
                String password = editText.getText().toString();
                if (password.length() < 6) {
                    Toast.makeText(MainActivity.this, "La contraseña debe ser más larga", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
            }
        });

        //APARTADO 6 RecyclerView
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });
    }

    //APARTADO 8 Sensor
    @Override
    public void onResume() {
        super.onResume();
        //APARTADO 8 Sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
        if (!listaSensores.isEmpty()) {
            Sensor magneticSensor = listaSensores.get(0);
            sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);}
    }

    @Override
    public void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        double lectura= sensorEvent.values[0];
        Log.d("SENSORES", "Magnetico en x: "+lectura);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}
}