package com.mdgz.dam.labdam2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mdgz.dam.labdam2022.databinding.ActivityVisualizaArchivoBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class VisualizaArchivo extends AppCompatActivity {

    TableLayout tabla;

    private String leerDeArchivo(){
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();
        try {
            fis = openFileInput("datosBusqueda.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffRdr= new BufferedReader(isr);
            String line;
            while ((line = buffRdr.readLine()) != null) { sb.append(line); }
            fis.close();
        } catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) { e.printStackTrace(); }
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_archivo);
        tabla = findViewById(R.id.tablaResultados);

        try {
            JSONArray arreglo =
                    (JSONArray) new JSONTokener(this.leerDeArchivo()).nextValue();
            System.out.println(arreglo.length());
            for(int i=0;i<arreglo.length();i++){
                JSONObject datos = arreglo.getJSONObject(i);
                String tiempo = (String) datos.get("tiempo");
                TableRow tablaRow = new TableRow(this);
                TextView tiempoText = new TextView(this);
                tiempoText.setText(tiempo);
                tablaRow.addView(tiempoText);
                tabla.addView(tablaRow);

                String cantOcupantes = (String) datos.get("cantidadOcupantes");
                TableRow tablaRowCantOcupantes = new TableRow(this);
                TextView cantOcupantesText = new TextView(this);
                cantOcupantesText.setText(cantOcupantes);
                tablaRowCantOcupantes.addView(cantOcupantesText);
                tabla.addView(tablaRowCantOcupantes);

                String tieneWifi = String.valueOf(datos.get("incluyeWifi"));
                TableRow tablaRowWifi = new TableRow(this);
                TextView wifiText = new TextView(this);
                wifiText.setText(tieneWifi);
                tablaRowWifi.addView(wifiText);
                tabla.addView(tablaRowWifi);

                String ciudad = (String) datos.get("ciudad");
                TableRow tablaRowCiudad = new TableRow(this);
                TextView ciudadText = new TextView(this);
                ciudadText.setText(ciudad);
                tablaRowCiudad.addView(ciudadText);
                tabla.addView(tablaRowCiudad);

                String minimo = (String) datos.get("minimo");
                TableRow tablaRowMinimo = new TableRow(this);
                TextView minimoText = new TextView(this);
                minimoText.setText(minimo);
                tablaRowMinimo.addView(minimoText);
                tabla.addView(tablaRowMinimo);

                String maximo = (String) datos.get("maximo");
                TableRow tablaRowMaximo = new TableRow(this);
                TextView maximoText = new TextView(this);
                maximoText.setText(maximo);
                tablaRowMaximo.addView(maximoText);
                tabla.addView(tablaRowMaximo);

                String cantResultados = (String) datos.get("cantidadResultados");
                TableRow tablaRowCantResultados = new TableRow(this);
                TextView cantResultadosText = new TextView(this);
                cantResultadosText.setText(cantResultados);
                tablaRowCantResultados.addView(cantResultadosText);
                tabla.addView(tablaRowCantResultados);
            }
        } catch (JSONException e) {e.printStackTrace();}
    }
}