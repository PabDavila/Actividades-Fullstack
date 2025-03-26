package com.dsy2201.veterinario;

public class Mascota {
    private int id;
    private String nombre;
    private int edad;
    private String raza;
    private Dueno dueno;

    public Mascota(int id, String nombre, int edad, String raza, Dueno dueno){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
        this.dueno = dueno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEdad() {
        return edad;
    }

    public void setNEdad(int edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }
}
