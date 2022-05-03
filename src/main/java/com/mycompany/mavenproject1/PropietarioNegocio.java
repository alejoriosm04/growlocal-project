/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author alejo
 */
public class PropietarioNegocio extends Usuario{

    private String nombreNegocio;
    private String tipoServicio;
    private int numeroProductos;
    private String linkPagina;

    public PropietarioNegocio(String nombre, String id, String ubicacion, String tipoUsuario, String password, String nombreNegocio, String tipoServicio, int numeroProductos, String linkPagina) {
        super(nombre, id, ubicacion, tipoUsuario, password);
        this.nombreNegocio = nombreNegocio;
        this.tipoServicio = tipoServicio;
        this.numeroProductos = numeroProductos;
        this.linkPagina = linkPagina;
    }

    public String getNombreNegocio() {
        return this.nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getTipoServicio() {
        return this.tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
    
    public int getNumeroProductos() {
        return this.numeroProductos;
    }
    
    public void setNumeroProductos(int numeroProductos) {
        this.numeroProductos = numeroProductos;
    }
    
    public String getLinkPagina() {
        return this.linkPagina;
    }

    public void setLinkPagina(String linkPagina) {
        this.linkPagina = linkPagina;
    }

    @Override
    public String toString() {
        return super.toString() + "PropietarioNegocio{" + "nombreNegocio=" + nombreNegocio + ", tipoServicio=" + tipoServicio + '}';
    }
}
