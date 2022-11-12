package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.repo.CiudadRepository;

public class BusquedaFragment extends Fragment {

    private Spinner tipoAlojamiento, cantOcupantes, ciudades;
    private CheckBox wifi;
    private EditText precioMinimo, precioMaximo;
    private Button btnBuscar, btnLimpiar;

    public interface OnBuscarListener{
        public void buscar();
    }
    private OnBuscarListener listenerBuscar;

    @Override
    public void onAttach(@NonNull Context activity) {
        super.onAttach(activity);
        try {
            listenerBuscar = (OnBuscarListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement interface");
        }
    }

    public BusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_busqueda, container,false);

        CiudadRepository repoCiudad = new CiudadRepository();
        ciudades = v.findViewById(R.id.spinner_ciudad);

        ArrayAdapter<Ciudad> adapter = new ArrayAdapter<Ciudad>(getActivity(), android.R.layout.simple_spinner_item, repoCiudad.listaCiudades());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ciudades.setAdapter(adapter);

        btnBuscar = v.findViewById(R.id.btn_Buscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerBuscar.buscar();//A buscar hay que pasarle un conjunto de info a buscar
            }
        });
        return v;
    }
}