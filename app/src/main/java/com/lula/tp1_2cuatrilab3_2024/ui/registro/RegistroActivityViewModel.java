package com.lula.tp1_2cuatrilab3_2024.ui.registro;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lula.tp1_2cuatrilab3_2024.modelo.Usuario;
import com.lula.tp1_2cuatrilab3_2024.modelo.request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutable;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        //obtengo el contexto q necesito para pasarle a la api
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioMutable()
    {
        if(usuarioMutable == null)
        {
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }
    public void guardar(String nombre, String apellido, long dni, String mail, String pass){
        Usuario usu = new Usuario(dni, nombre, apellido, mail, pass);
        ApiClient.guardar(context, usu);
    }

    public void recuperarUsuario()
    {
        Usuario usu = ApiClient.leer(context);
        if(usu != null)
        {
            usuarioMutable.setValue(usu);
        }
        else{
            Toast.makeText(getApplication(), "No hay datos", Toast.LENGTH_LONG).show();
        }
    }
}
