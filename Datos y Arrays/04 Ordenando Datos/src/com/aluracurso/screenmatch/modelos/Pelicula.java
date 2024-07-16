package com.aluracurso.screenmatch.modelos;

import com.aluracurso.screenmatch.calculos.Clasificacion;

public class Pelicula extends Titulo implements Clasificacion {
    private String Director;

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public Pelicula(String nombre, int fechaDeLanzamiento) {
        super(nombre, fechaDeLanzamiento);
    }

    @Override
    public int getClasificacion() {
        return (int) (calculaMedia() / 2);
    }

    @Override
    public String toString() {
        return "Pelicula: " + this.getNombre() + "(" + getFechaDeLanzamiento() + ")";
    }
}











