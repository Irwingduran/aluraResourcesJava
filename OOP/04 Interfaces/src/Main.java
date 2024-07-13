import com.aluracurso.screenmatch.calculos.CalculadoraDeTiempo;
import com.aluracurso.screenmatch.calculos.FiltroRecomendacion;
import com.aluracurso.screenmatch.modelos.Episodio;
import com.aluracurso.screenmatch.modelos.Pelicula;
import com.aluracurso.screenmatch.modelos.Serie;

public class Main {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula();
        miPelicula.setNombre("La familia del futuro");
        miPelicula.setFechaDeLanzamiento(2007);
        miPelicula.setDuracionEnMinutos(95);
        miPelicula.setIncluidoEnElPlan(true);

        miPelicula.muestraFichaTecnica();
        miPelicula.evalua(10);
        miPelicula.evalua(10);
        miPelicula.evalua(7.8);
        System.out.println("Media de evaluaciones de la película: " +miPelicula.calculaMedia());

        Serie casaDragon = new Serie();
        casaDragon.setNombre("La Casa del Dragon");
        casaDragon.setFechaDeLanzamiento(2022);
        casaDragon.setTemporadas(2);
        casaDragon.setMinutosPorEpisodio(50);
        casaDragon.setEpisodiosPorTemporada(10);
        casaDragon.muestraFichaTecnica();
        System.out.println(casaDragon.getDuracionEnMinutos());

        Pelicula otraPelicula = new Pelicula();
        otraPelicula.setNombre("Matrix");
        otraPelicula.setFechaDeLanzamiento(1998);
        otraPelicula.setDuracionEnMinutos(180);


       CalculadoraDeTiempo calculadora = new CalculadoraDeTiempo();
        calculadora.incluye(miPelicula);
        calculadora.incluye(casaDragon);
        calculadora.incluye(otraPelicula);
        System.out.println("Tiempo necesario para ver tus titulos fav: " + calculadora.getTiempoTotal());

        FiltroRecomendacion filtroRecomendacion = new FiltroRecomendacion();
        filtroRecomendacion.filtra(miPelicula);

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setNombre("La casa Targaryen");
        episodio.setSerie(casaDragon);
        episodio.setTotalVisualizaciones(300);

        filtroRecomendacion.filtra(episodio);

}
}