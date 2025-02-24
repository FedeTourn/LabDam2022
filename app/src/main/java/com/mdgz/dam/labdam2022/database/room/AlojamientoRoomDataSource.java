package com.mdgz.dam.labdam2022.database.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.database.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.AppDatabase;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.DepartamentoDao;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.HabitacionDao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlojamientoRoomDataSource implements AlojamientoDataSource {

    private HabitacionDao habitacionDao;
    private DepartamentoDao departamentoDao;

    public AlojamientoRoomDataSource(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        departamentoDao = db.departamentoDao();
        habitacionDao = db.habitacionDao();
    }

    @Override
    public void guardarAlojamiento(Alojamiento entidad, GuardarAlojamientoCallback callback) {
        try {
            if (entidad instanceof Departamento) {
                departamentoDao.insert((Departamento) entidad);
            } else {
                habitacionDao.insert((Habitacion) entidad);
            }
            callback.resultado(true);
        }
        catch (Exception e) {
            callback.resultado(false);
        }
    }

    @Override
    public void recuperarAlojamiento(RecuperarAlojamientoCallback callback) {
        List<Alojamiento> alojamientos;
        try {
            alojamientos = new ArrayList<>(departamentoDao.obtenerDepartamentos());
            alojamientos.addAll(new ArrayList<Alojamiento>(habitacionDao.obtenerHabitaciones()));
            System.out.println("AAA" + alojamientos);
            callback.resultado(true, alojamientos);
        }
        catch (Exception e) {
            throw e;
            //callback.resultado(false, null);
        }
    }
    
    public void recuperarAlojamientoId(RecuperarAlojamientoCallback callback, UUID uuid) {
        Alojamiento alojamiento;
        
        alojamiento = departamentoDao.obtenerDepartamento(uuid);
        if (alojamiento == null) {
            alojamiento = habitacionDao.obtenerHabitacion(uuid);
        }
        
        List<Alojamiento> alojamientos = new ArrayList<>();
        alojamientos.add(alojamiento);
        callback.resultado(true, alojamientos);
    }
}
