 Haz el mismo procedimiento que hice en clase, declarando y usando constructores en las clases del proyecto. En la clase Titulo, declara el constructor que recibe el nombre y la fecha de lanzamiento:

package com.alura.screenmatch.modelos;
public class Titulo {
    //atributos omitidos
    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }
    //metodos omitidos
}


En la clase Serie, será necesario declarar el mismo constructor, y llamará al constructor de la clase madre usando la palabra reservada super:

pacakge com.alura.screenmatch.modelos;

public class Serie extends Titulo {
    //atributos omitidos
    public Serie(String nombre, int fechaDeLanzamiento) {
        super(nombre, fechaDeLanzamiento);
    }
    //metodos omitidos
}


Lo mismo se debe hacer con la clase Película:

pacakge com.alura.screenmatch.modelos;

public class Pelicula extends Titulo implements Clasificable{
    //atributos omitidos
    public Pelicula(String nombre, int fechaDeLanzamiento) {
        super(nombre, fechaDeLanzamiento);
    }
    //metodos omitidos
}


Finalmente, en la clase Principal, corrije los errores de compilación que están sucediendo al instanciar los objetos Pelicula y Serie, pasando el nombre y fecha de lanzamiento en el constructor. Por ejemplo:

Pelicula favorita = new pelicula(“The Matrix”, 1999);
