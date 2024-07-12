public class Main {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula();
        miPelicula.nombre = "La familia del futuro";
        miPelicula.fechaDeLanzamiento= 2007;
        miPelicula.duracionEnMinutos = 95;
        miPelicula.incluidoEnElPlan = true;

        miPelicula.muestraFichaTecnica();
        miPelicula.evalua(10);
        miPelicula.evalua(10);
        miPelicula.evalua(7.8);
        System.out.println("Média de evaluaciones de la película: " +miPelicula.calculaMedia());
    }
}