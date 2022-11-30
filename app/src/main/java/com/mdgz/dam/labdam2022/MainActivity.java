package com.mdgz.dam.labdam2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.mdgz.dam.labdam2022.model.Alojamiento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements BusquedaFragment.OnBuscarListener, ResultadoBusquedaFragment.OnVerDetallesListener{



    private Toolbar miToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miToolbar = findViewById(R.id.mi_toolbar);
        setSupportActionBar(miToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuOptBuscar:
                try {
                    NavHostFragment navHostFragment =
                            (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
                    NavController navController = navHostFragment.getNavController();

                    navController.navigate(R.id.busquedaFragment);//Modificar para usar acciones????
                }catch (Exception e){
                }
                return true;

            case R.id.mnuOptReservas:
                /* Agregar el intento del fragmento */
                return true;
            case R.id.mnuOptFavoritos:
                /* Agregar el intento del fragmento */
                return true;
            case R.id.mnuOptConfiguracion:
                NavHostFragment navHostFragment =
                        (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
                NavController navController = navHostFragment.getNavController();

                navController.navigate(R.id.settingsFragment);
                return true;
            default:
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void escribirEnArchivo(JSONArray busqueda){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("datosBusqueda.json", Context.MODE_PRIVATE);
            fos.write(busqueda.toString().getBytes());
            fos.close();
            System.out.println("archivo escrito");
        } catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) { e.printStackTrace();}
    }

    @Override
    public void buscar(String tipoAlojamiento, int cantidadOcupantes, boolean incluyeWifi, String ciudad, int minimo, int maximo) {


        JSONArray arreglo = new JSONArray();
        JSONObject archivo = new JSONObject();
        long start = System.nanoTime();
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        long elapsedTime = System.nanoTime() - start;

        try {
            archivo.put("tiempo", String.valueOf(elapsedTime));
            archivo.put("cantidadOcupantes", String.valueOf(cantidadOcupantes));
            archivo.put("incluyeWifi", incluyeWifi);
            archivo.put("ciudad", ciudad);
            archivo.put("minimo", String.valueOf(minimo));
            archivo.put("maximo", String.valueOf(maximo));
            archivo.put("cantidadResultados", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arreglo.put(archivo);
        escribirEnArchivo(arreglo);
        navController.navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment);
    }

    @Override
    public void verDetalles(Alojamiento alojamiento) {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        Bundle bundle = new Bundle();
        bundle.putParcelable("alojamiento", (Parcelable) alojamiento);
        navController.navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment, bundle);
    }


}