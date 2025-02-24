package com.mdgz.dam.labdam2022.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.UUID;

public class ReservaEntity {
    @SerializedName("alojamientoId")
    @Expose
    private UUID alojamientoId;
    @SerializedName("usuarioId")
    @Expose
    private UUID usuarioId;
    @SerializedName("fechaIngreso")
    @Expose
    private Date fechaIngreso;
    @SerializedName("fechaSalida")
    @Expose
    private Date fechaSalida;

    public ReservaEntity(UUID alojamientoId, UUID usuarioId, Date fechaIngreso, Date fechaSalida) {
        this.alojamientoId = alojamientoId;
        this.usuarioId = usuarioId;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public UUID getAlojamientoId() {
        return alojamientoId;
    }

    public void setAlojamientoId(UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
