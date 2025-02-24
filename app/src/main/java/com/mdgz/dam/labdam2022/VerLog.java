package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdgz.dam.labdam2022.databinding.FragmentVerLogBinding;
import com.mdgz.dam.labdam2022.model.BusquedaDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerLog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerLog extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentVerLogBinding binding;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VerLog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerLog.
     */
    // TODO: Rename and change types and number of parameters
    public static VerLog newInstance(String param1, String param2) {
        VerLog fragment = new VerLog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVerLogBinding.inflate(inflater, container, false);
        String json = "";
        try {
            FileInputStream fis = getContext().openFileInput("busquedas.json");
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<BusquedaDTO> listaBusquedas = null;
        try {
            listaBusquedas = objectMapper.readValue(json, new TypeReference<List<BusquedaDTO>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (listaBusquedas != null) {
            BusquedaAdapter adaptador = new BusquedaAdapter(listaBusquedas);
           binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recyclerView.setAdapter(adaptador);
        }


        return binding.getRoot();
    }
}