/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

public class Usuario {

    private String nombre;
    private String id;
    private String ubicacion;
    private String tipoUsuario;
    private String password;

    public Usuario(String nombre, String id, String ubicacion, String tipoUsuario, String password){
        this.nombre = nombre;
        this.id = id;
        this.ubicacion = ubicacion;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getId(){
        return this.id;
    }

    public String getUbicacion(){
        return this.ubicacion;
    }

    public String getTipoUsuario(){
        return this.tipoUsuario;
    }

    public String getPassword(){
        return this.password;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }

    public void setTipoUsuario(String tipoUsuario){
        this.tipoUsuario = tipoUsuario;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return "Nombre: " + nombre + "\n" + "ID: " + id + "\n" + "Ubicacion: " + ubicacion + "\n" + "Tipo de usuario: " + tipoUsuario + "\n" + "Password: " + password;
    }
}