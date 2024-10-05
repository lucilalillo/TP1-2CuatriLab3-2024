package com.lula.tp1_2cuatrilab3_2024.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.lula.tp1_2cuatrilab3_2024.modelo.Usuario;
import com.lula.tp1_2cuatrilab3_2024.modelo.request.ApiClient;
import com.lula.tp1_2cuatrilab3_2024.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        //obtengo el contexto q necesito para pasarle a la api
        context = application.getApplicationContext();
    }

    public void Login(String usu, String psw) {
        //obtengo el usuario de la api
        Usuario usuario = ApiClient.login(context, usu, psw);
        //valido si existe el usuario, sino existe muestro un msj
        if (usuario == null) {
            Toast.makeText(context.getApplicationContext(), "Email o Usuario incorrecto", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(context, RegistroActivity.class);
            //pongo esta bandera porque lanzo la activity desde un ViewModel
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ApiClient.leer(context);
            context.startActivity(intent);
        }
    }
}
