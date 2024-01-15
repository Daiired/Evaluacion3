/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.evaluacion3.evaluacion3;

/**
 *
 * @author Diego
 */
public class Usuario {
    private String username;
    private String password;
    private String Nombres;
    private String Apellidos;
    
    public Usuario(){
    }

    public Usuario(String username, String password, String Nombres, String Apellidos) {
        this.username = username;
        this.password = password;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }
    
    
}
