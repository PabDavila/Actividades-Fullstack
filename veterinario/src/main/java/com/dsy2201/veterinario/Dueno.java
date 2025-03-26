package com.dsy2201.veterinario;

public class Dueno {
    private int id;
    private String rut;
    private String nombre;
    private String apellido;

    public Dueno(int id, String rut, String nombre, String apellido){
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut){
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
