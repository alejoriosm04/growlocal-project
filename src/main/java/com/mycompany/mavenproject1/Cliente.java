/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author alejo
 */
public class Cliente extends Usuario{

    private String interes;

    public Cliente(String nombre, String id, String ubicacion, String tipoUsuario, String password, String interes){
        super(nombre, id, ubicacion, tipoUsuario, password);
        this.interes = interes;
    }

    public String getInteres(){
        return this.interes;
    }

    public void setInteres(String interes){
        this.interes = interes;
    }

    @Override
    public String toString(){
        return super.toString() + "Interes: " + interes;
    }
}
