/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.evaluacion3.evaluacion3;

import java.util.Date;

/**
 *
 * @author Diego
 */
public class Alojamiento {
    private String rut;
    private String nombreAgrupacion;
    private int idTipoVehiculo;
    private int idTipoAlojamiento;
    private Date FechaIngreso;
    private int dias;

    public Alojamiento() {
    }

    public Alojamiento(String rut, String nombreAgrupacion, int idTipoVehiculo, int idTipoAlojamiento, int dias) {
        this.rut = rut;
        this.nombreAgrupacion = nombreAgrupacion;
        this.idTipoVehiculo = idTipoVehiculo;
        this.idTipoAlojamiento = idTipoAlojamiento;
        this.FechaIngreso = new Date();
        this.dias = dias;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombreAgrupacion() {
        return nombreAgrupacion;
    }

    public void setNombreAgrupacion(String nombreAgrupacion) {
        this.nombreAgrupacion = nombreAgrupacion;
    }

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public int getIdTipoAlojamiento() {
        return idTipoAlojamiento;
    }

    public void setIdTipoAlojamiento(int idTipoAlojamiento) {
        this.idTipoAlojamiento = idTipoAlojamiento;
    }

    public Date getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(Date FechaIngreso) {
        this.FechaIngreso = FechaIngreso;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    
    
}
