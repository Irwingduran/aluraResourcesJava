solucionando los errores que puedan ocurrir al utiliza la API de OMDb. Crea un nuevo paquete llamado excepcion, dentro del paquete com.alura.screenmatch, y dentro de él crea la clase ErrorEnConversionDeDuracionException.java:

package com.alura.screenmatch.excepcion;

public class ErrorEnConversionDeDuracionException extends RuntimeException {
    private String mensaje;

    public ErrorEnConversionDeDuracionException(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}


Necesitarás cambiar el constructor de la clase Título para validar la duración:

public Titulo(TituloOmdb miTituloOmdb) {
        this.nombre = miTituloOmdb.title();
        this.fechaDeLanzamiento = Integer.valueOf(miTituloOmdb.year());
        if (miTituloOmdb.runtime().contains("N/A")){
            throw new ErrorEnConversionDeDuracionException("No pude convertir la duracion, porque contiene un N/A");
        }
        this.duracionEnMinutos = Integer.valueOf(
                miTituloOmdb.runtime().substring(0,3).replace(" ","")
        );
    }


Finalmente, cambie el código de la clase PrincipalConBusqueda para hacer el tratamiento de errores y buscar películas que contengan espacios en blanco en el nombre:

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        System.out.println("Escriba el nombre de una pelicula: ");
        var busqueda = lectura.nextLine();

        String direccion = "https://www.omdbapi.com/?t="+
                busqueda.replace(" ", "+") +
                "&apikey=d4d0bf92";

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            System.out.println(miTituloOmdb);

            Titulo miTitulo = new Titulo(miTituloOmdb);
            System.out.println("Titulo ya convertido: " + miTitulo);
        }catch (NumberFormatException e){
            System.out.println("Ocurrió un error: ");
            System.out.println(e.getMessage());
        }catch(IllegalArgumentException e){
            System.out.println("Error en la URI, verifique la dirección.");
        }catch (ErrorEnConversionDeDuracionException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Finalizó la ejecución del programa!");
    }
}
