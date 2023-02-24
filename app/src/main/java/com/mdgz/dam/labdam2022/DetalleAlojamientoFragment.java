package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DetalleAlojamientoFragment extends Fragment {
    private TextView titulo, descripcion, capacidad, precio;

    private FragmentDetalleAlojamientoBinding binding;
    private Alojamiento alojamiento;

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && !getArguments().isEmpty()) {
            alojamiento = getArguments().getParcelable("alojamiento");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment

        titulo = binding.tituloText;
        descripcion = binding.descripcionText;
        capacidad = binding.capacidadText;
        precio = binding.precio;

        /*
        TextView tituloText = binding.tituloText;
        TextView descripcionText = binding.descripcionText;thisView.findViewById(R.id.descripcionText);
        TextView capacidadText = binding.capacidadText;
        TextView precioText = binding.precioText;
         */


        /* TOREAD
        https://www.geeksforgeeks.org/how-to-implement-google-map-inside-fragment-in-android/
         */
        if (getArguments() != null && !getArguments().isEmpty()) { //TODO: Controlar esta comprobacion, capaz es innecesaria
            alojamiento = getArguments().getParcelable("alojamiento");
            titulo.setText(alojamiento.getTitulo());
            descripcion.setText(alojamiento.getDescripcion());
            capacidad.setText(String.valueOf(alojamiento.getCapacidad()));
            String titulo =" TITULOOO: " + alojamiento.getTitulo();
            System.out.println(titulo);
            binding.botonActualizar.setOnClickListener(e->{
                    if(Integer.valueOf(binding.cantOcup.getText().toString()) > alojamiento.getCapacidad()){
                        Toast.makeText(getContext(), "La cantidad de ocupantes es mayor a la del alojamiento", Toast.LENGTH_SHORT).show();
                } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate entrada = LocalDate.parse(binding.fechaEntrada.getText().toString(), formatter);
                        LocalDate salida = LocalDate.parse(binding.fechaSalida.getText().toString(), formatter);
                        if(entrada.isAfter(salida)){
                            Toast.makeText(getContext(), "La fecha de entrada es posterior a la de salida", Toast.LENGTH_LONG).show();
                        } else {
                            Long cantDias = ChronoUnit.DAYS.between(entrada, salida);
                            precio.setText(String.valueOf(cantDias*Integer.parseInt(binding.cantOcup.getText().toString())*alojamiento.getPrecioBase()));
                            Toast.makeText(getContext(), "Precio calculado correctamente", Toast.LENGTH_LONG).show();
                        }

                    }
            });


        }else{
            Context context = getContext();
            CharSequence text = "ERROR, NO RECIBE LOS ARGUMENTOS";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
        return binding.getRoot();
    }
}