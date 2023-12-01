package com.example.tomas.examen;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//APARTADO 6
public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.ViewHolder> {

    private List<Registro> listaRegistros;

    public RegistroAdapter(List<Registro> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_lista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Registro registro = listaRegistros.get(position);
        holder.personaliza(registro);
    }

    @Override
    public int getItemCount() {
        return listaRegistros.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView usuario;
        TextView minuto;
        TextView tipo;

        ViewHolder(View itemView) {
            super(itemView);
            usuario = itemView.findViewById(R.id.usuario);
            minuto = itemView.findViewById(R.id.minuto);
            tipo = itemView.findViewById(R.id.tipo);
        }

        void personaliza(Registro registro) {
            usuario.setText(registro.getUsuario());
            minuto.setText(""+registro.getMinuto());
            tipo.setText(registro.getTipo());
            if (tipo.getText() == "E"){
                tipo.setTextColor(Color.GREEN); //verde
            } else {
                tipo.setTextColor(Color.RED); //rojo
            }
        }
    }
}
