/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author BRAYAN
 */
public class Jugador {
    
    private String puestos;
    private String nombre;
    private int datos;

    public Jugador(String puestos, String nombre, int datos) {
        this.puestos = puestos;
        this.nombre = nombre;
        this.datos = datos;
    }

    

    public int getDatos() {
        return datos;
    }

    public void setDatos(int datos) {
        this.datos = datos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuestos() {
        return puestos;
    }

    public void setPuestos(String puestos) {
        this.puestos = puestos;
    }

    
    
}