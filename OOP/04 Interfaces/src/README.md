**Crea la interfaz "Classificable" en el paquete "com.aluracursos.screenmatch.calculos":**

*********** Código ***********
package com.aluracursos.screenmacth.calculos;

public interface Clasificable {
    int getClasificacion();
}
*********** Código ***********

**En la clase "Pelicula", debes implementar la interfaz "Clasificable" y también implementar el método "getClasificacion":**

*********** Código ***********
public class Pelicula extends Titulo implements Clasificable {

@Override
    public int getClasificacion() {
        return (int) (calculaMedia() / 2);
    }
*********** Código ***********

**En la clase "Episodio", debes implementar la interfaz "Clasificable", implementar el método "getClasificacion" y agregar el nuevo atributo "totalVisualizaciones":**

*********** Código ***********
public class Episodio implements Clasificable {

@Override
    public int getClasificacion() {
        if (totalVisualizaciones > 100){
            return 4;
        }else {
            return 2;
        }
    }
*********** Código ***********
**
Ahora, necesitas crear la clase "FiltroRecomendacion":**

*********** Código ***********
package com.aluracursos.screenmacth.calculos;

public class FiltroRecomendacion {

    public void filtra(Clasificable clasificacion){
        if (clasificacion.getClasificacion() >= 4){
            System.out.println("Muy bien evaluado en el momento");
        } else if (clasificacion.getClasificacion() >= 2){
            System.out.println("popular en el momento");
        } else {
            System.out.println("Colocálo en tu lista para verlo después");
        }
    }
}
*********** Código ***********

**Finalmente, para probar el nuevo código, modifica la clase "Principal" creando un objeto "FiltroRecomendacion" al final del código existente en el método "main()":**

*********** Código ***********
        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setNombre("La casa Targaryen");
        episodio.setSerie(casaDragon);
        episodio.setTotalVisualizaciones(50);

FiltroRecomendacion filtroRecomendacion = new FiltroRecomendacion();
        filtroRecomendacion.filtra(miPelicula);

        filtroRecomendacion.filtra(episodio);
*********** Código ***********
