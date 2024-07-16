Sobrescribe el método toString() en la clase Pelicula, y cambiando la clase Principal para crear una lista de películas.

En la clase Pelicula, necesitarás sobreescribir el método toString():

@Override
public String toString() {
return "Pelicula: " +this.getNombre() + " (" +this.getFechaDeLanzamiento() + ")"; 
}


Y en el método main de la clase Principal, crea un ArrayList, agrega las películas e imprime la lista:

public class Principal { 
    public static void main(String[] args) {
         // código anterior omitido 
        ArrayList listaDePeliculas = new ArrayList<>();
        listaDePeliculas.add(favorito);
        listaDePeliculas.add(otro);
        System.out.println(“Tamaño de la lista: “ + listaDePeliculas.size());
        System.out.println(“Primera película: “ + listaDePeliculas.get(0));
        System.out.println(listaDePelículas); 
    }
}
