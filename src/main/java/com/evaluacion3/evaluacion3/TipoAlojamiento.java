/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.evaluacion3.evaluacion3;

/**
 *
 * @author Diego
 */
public class TipoAlojamiento {
    private int idTipoAlojamiento;
    private String descripcion;

    public TipoAlojamiento() {
    }

    public TipoAlojamiento(int idTipoAlojamiento, String descripcion) {
        this.idTipoAlojamiento = idTipoAlojamiento;
        this.descripcion = descripcion;
    }

    public int getIdTipoAlojamiento() {
        return idTipoAlojamiento;
    }

    public void setIdTipoAlojamiento(int idTipoAlojamiento) {
        this.idTipoAlojamiento = idTipoAlojamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
