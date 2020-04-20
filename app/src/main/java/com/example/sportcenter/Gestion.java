package com.example.sportcenter;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sportcenter.Clases.Login;
import com.example.sportcenter.Objetos.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Gestion {

    Usuario user;
    DatabaseReference referencia_user;

    public Usuario obtener() {

        referencia_user = FirebaseDatabase.getInstance().getReference("usuario");
        Query sentencia = referencia_user.orderByChild("email").equalTo("victor@gmail.com").limitToFirst(1);
        sentencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        user = d.getValue(Usuario.class);
                    }
                } else {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return user;
    }


    public int getImagen(String img) {

        switch (img) {
            case "playlist_img_5":
                return R.drawable.playlist_img_4;
            case "playlist_img_energia":
                return R.drawable.playlist_img_energia;
            case "playlist_img_3":
                return R.drawable.playlist_img_3;
            case "playlist_img_motivacion":
                return R.drawable.playlist_img_motivacion;
            case "playlist_img_2":
                return R.drawable.playlist_img_2;
            case "playlist_img_beast":
                return R.drawable.playlist_img_beast;

        }
        return R.drawable.playlist_img_beast;
    }

    public int getReproductor(String reproductor) {

        switch (reproductor) {
            case "youtube":
                return R.drawable.ic_youtube;
            case "spotify":
                return R.drawable.ic_spotify;
        }
        return R.drawable.ic_youtube;
    }



}
