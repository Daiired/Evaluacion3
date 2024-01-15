/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.evaluacion3.evaluacion3;

/**
 *
 * @author Diego
 */
public class TipoVehiculo {
    private int idTipoVehiculo;
    private String Descripcion;

    public TipoVehiculo() {
    }

    public TipoVehiculo(int id, String Descripcion) {
        this.idTipoVehiculo = id;
        this.Descripcion = Descripcion;
    }

    public int getId() {
        return idTipoVehiculo;
    }

    public void setId(int id) {
        this.idTipoVehiculo = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
}
