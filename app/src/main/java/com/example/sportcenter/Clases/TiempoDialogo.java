package com.example.sportcenter.Clases;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.sportcenter.R;

public class TiempoDialogo extends DialogFragment{

    private Context entorno;
    private View vista;
    EstablecerTiempo tiempo;
    private EditText horas,minuto;

    public TiempoDialogo(Context contexto){this.entorno = contexto;}

    public Dialog onCreateDialog(Bundle savedInstancestate){

        AlertDialog.Builder constructor = new AlertDialog.Builder(entorno);
        LayoutInflater bombin = requireActivity().getLayoutInflater();
        vista = bombin.inflate(R.layout.dialog_tiempo,null);

        horas = vista.findViewById(R.id.horas);
        minuto = vista.findViewById(R.id.minutos);


        constructor.setView(vista).setPositiveButton("ESTABLECER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String time="";

                //time = horas.getText().toString()+":"+minuto.getText().toString();
                
                if (validarTiempo(horas.getText().toString(),minuto.getText().toString())){
                    time = horas.getText().toString()+":"+minuto.getText().toString();
                    tiempo.setTiempo(time);
                    dialog.dismiss();
                }else{
                    Toast.makeText(entorno, "Dato introducido no valido", Toast.LENGTH_SHORT).show();
                }



            }
        }).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tiempo.setTiempo(null);
                dialog.dismiss();
            }
        });


        return constructor.create();
    }
    
    private boolean validarTiempo(String hora,String minuto){
        int h,m;

        h =Integer.valueOf(hora);
        m = Integer.valueOf(minuto);

        if (h>0 && m>0 && m<60){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        entorno = context;
        tiempo = (EstablecerTiempo) context;
    }

    public interface EstablecerTiempo{
        public void setTiempo(String fecha);
    }
}
