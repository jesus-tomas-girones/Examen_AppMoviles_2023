package com.example.tomas.examen;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

//APARTADO 3.a)
public class Registro {
    private String usuario;
    private int minuto;
    private String tipo;

    public Registro(String usuario, int minuto, String tipo) {
        this.usuario = usuario;
        this.minuto = minuto;
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getMinuto() {
        return minuto;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "usuario='" + usuario + '\'' +
                ", minuto=" + minuto +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    public static List<Registro> crearListaDesdeMap(Map<Integer, Registro> mapaRegistros) {
        List<Registro> listaRegistros = new ArrayList<>();
        for (Registro registro : mapaRegistros.values()) {
            listaRegistros.add(registro);
        }
        return listaRegistros;
    }

    //APARTADO 3.b)
    public static Map<Integer, Registro> crearMapDesdeListas(List<String> usuarios, List<Integer> minutos, List<String> tipos) {
        Map<Integer, Registro> mapaRegistros = new HashMap<>();
        for (int i = 0; i < usuarios.size(); i++) {
            mapaRegistros.put(minutos.get(i), new Registro(usuarios.get(i), minutos.get(i), tipos.get(i)));
        }
        return mapaRegistros;
    }

    //APARTADO 4.a)
    //Solución propuesta por Marc.
    //La lógica es la siguiente: Cada vez que entra un usuario, el tipo es "E",
    //por lo que se suma uno al contador. Si el usuario sale, el tipo es "S",
    // por lo que se resta uno al contador.
    public static int contarUsuariosConectados(Map<Integer, Registro> registros)  {
        int contador = 0;
        for (Registro registro : registros.values()) {
            if (registro.getTipo().equals("E")) {
                contador++;
            } else {
                contador--;
            }
        }
        return contador;
    }
}
