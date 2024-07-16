Haz el mismo procedimiento que hice en clase, descargando y usando la biblioteca Gson en el proyecto.

Acceda al sitio web de MVN Repository, busque “Gson” y haga clic en el primer resultado.

Imagen Hlqh1-1.png de la carpeta Imagenes

Al abrir la página de Gson, se nos presenta el histórico de versiones. Seleccione la versión 2.10.1.

Imagen Hlqh1-2.png de la carpeta Imagenes

En Files, clica en la opción “jar” para descargar el archivo.

Imagen Hlqh1-3.png de la carpeta Imagenes

Después de descargar el archivo, es hora de agregar esa biblioteca a nuestro proyecto. En IntelliJ, clica en los tres puntitos de la esquina superior izquierda (si es que estan utilizando la version nueva de la interfaz de IntelliJ).

Imagen Hlqh1-4.png de la carpeta Imagenes

Clica en File > Project Structure.

Imagen Hlqh1-5.png de la carpeta Imagenes

En Project Structure, navegue hasta Modules > Dependencies, clique en + y seleccione la opción “JARs or Directories…”

Imagen Hlqh1-6.png de la carpeta Imagenes

Se abrirá una nueva ventana para seleccionar el archivo .jar. Seleccione el lugar donde está el Gson.

Imagen Hlqh1-7.png de la carpeta Imagenes

Clique en “OK” y listo! Se agregó su dependencia, y ahora podrás trabajar con las herramientas que Gson nos ofrece. Ahora crea un Record TituloOmdb con el siguiente código:

package com.alura.screenmatch.modelos;

public record TituloOmdb(String title, String year, String runtime) {
}


En la clase PrincipalConBusqueda, agregue el siguiente código:

        String json = response.body();
        System.out.println(json);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
        System.out.println(miTituloOmdb);
        Titulo miTitulo = new Titulo(miTituloOmdb);
        System.out.println(miTitulo);


La clase Titulo tendrá la siguiente forma:

package com.alura.screenmatch.modelos;

import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo>{
    @SerializedName("Title")
    private String nombre;
    @SerializedName("Year")
    private int fechaDeLanzamiento;
    private boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDeEvaluaciones;
    private int duracionEnMinutos;

    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Titulo(TituloOmdb miTituloOmdb) {
        this.nombre = miTituloOmdb.title();
        this.fechaDeLanzamiento = Integer.valueOf(miTituloOmdb.year());
        this.duracionEnMinutos = Integer.valueOf(miTituloOmdb.runtime().substring(0,2));
    }

    public String getNombre() {
        return nombre;
    }

    public int getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public boolean isIncluidoEnElPlan() {
        return incluidoEnElPlan;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public int getTotalDeEvaluaciones() {
        return totalDeEvaluaciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaDeLanzamiento(int fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public void setIncluidoEnElPlan(boolean incluidoEnElPlan) {
        this.incluidoEnElPlan = incluidoEnElPlan;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public void muestraFichaTecnica(){
        System.out.println("Nombre de la película: " + nombre);
        System.out.println("Año de lanzamiento: " + fechaDeLanzamiento);
    }

    public void evalua(double nota){
        sumaDeLasEvaluaciones += nota;
        totalDeEvaluaciones++;
    }

    public double calculaMediaEvaluaciones(){
        return sumaDeLasEvaluaciones / totalDeEvaluaciones;
    }

    @Override
    public int compareTo(Titulo otroTitulo) {
        return this.getNombre().compareTo(otroTitulo.getNombre());
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento+
                ", duracion="+duracionEnMinutos;
    }
}
