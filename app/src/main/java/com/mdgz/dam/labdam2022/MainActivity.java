package com.mdgz.dam.labdam2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

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
                /* Agregar el intento del fragmento */
                return true;
            default:
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void buscar() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        navController.navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment);
    }

    @Override
    public void verDetalles() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        navController.navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment);
    }
}