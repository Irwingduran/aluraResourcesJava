Haz el mismo procedimiento que hice en clase, escribiendo tu lista de películas en json.

Vas a necesitar modificar la clase PrincipalConBusqueda. Haremos que lea las películas y las almacene en una lista. Para hacer esto, vamos a crear una lista de títulos antes del loop. Ese loop podría ser con un while.

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();

        while(true){
    //código omitido por ahora
        }
    }
}

Dentro de ese while, si el usuario escribe salir vamos a interrumpir la búsqueda. Debido a esto es que vamos a comparar si el usuario escribió la palabra salir, después de escribir el nombre de la película.

//código omitido
while(true){
            System.out.println("Escriba el nombre de una película: ");
            var busqueda = lectura.nextLine();

            if(busqueda.equalsIgnoreCase("salir")){
                break;
            }
    //código omitido por ahora
}


Después de darle valor a la variable direccion concatenando la url base con el nombre del título que se va a buscar, haremos la request http, que está dentro de un bloque try, ya que podría ocurrir algún error y lanzarse una excepción, como vimos en el aula:

//código omitido
if(busqueda.equalsIgnoreCase("salir")){
                break;
            }

            String direccion = "https://www.omdbapi.com/?t="+
                    busqueda.replace(" ", "+") +
                    "&apikey=coloque_su_apikey";

            try{
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
        //código omitido
            }catch (NumberFormatException e){
                System.out.println("Ocurrió un error: ");
                System.out.println(e.getMessage());
            }catch(IllegalArgumentException e){
                System.out.println("Error en la URI, verifique la dirección.");
            }catch (ErrorEnConversionDeDuracionException e){
                System.out.println(e.getMessage());
            }
        }
//código omitido


Nuestra variable json está en este momento con el contenido devolvido en el body de la respuesta, es decir, los datos encontrados referentes a la película buscada.

El próximo paso es convertir ese contenido textual en un objeto del tipo Titulo, y para esto usaremos la biblioteca Gson. Antes de utilizarla, vamos a instanciar una variable a partir de ella:

//código omitido
List<Titulo> titulos = new ArrayList<>();
Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
//código omitido


Ahora utilizaremos esa instancia en la transformación de los datos:

//código omitido
                String json = response.body();
                System.out.println(json);

                TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(miTituloOmdb);

                Titulo miTitulo = new Titulo(miTituloOmdb);
                System.out.println("Titulo ya convertido: " + miTitulo);
//código omitido


Finalmente incluiremos el título, representado en la variable miTitulo, en la lista de títulos, con el siguiente código:

//código omitido
                Titulo miTitulo = new Titulo(miTituloOmdb);
                System.out.println("Titulo ya convertido: " + miTitulo);
                titulos.add(miTitulo);
//código omitido


Este flujo ocurre hasta que el usuario escriba salir cuando se le pregunta el nombre de la película que desea buscar. Al finalizar las búsquedas y salir del loop, solo faltará escribir el archivo, con el siguiente código:

while(true){
//código omitido
}
FileWriter escritura = new FileWriter("titulos.json");
escritura.write(gson.toJson(titulos));
escritura.close();
System.out.println("Finalizó la ejecución del programa!");
}
}


Con esto tendremos un archivo llamado peliculas.json con todas las películas buscadas. Es posible que ese archivo quede en un formato extraño, con todos los datos en la misma línea. Gson permite que escribamos ese archivo con una formatación más amigable. Para esto solo es necesario incluir el siguiente código, informando que queremos el “pretty printing”.

Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();


Con esto conseguimos guardar nuestras películas favoritas en un archivo json, que podrá ser leído por otras aplicaciones y, futuramente, persistidos en una base de datos. ¡Espero que les haya gustado esta actividad!
