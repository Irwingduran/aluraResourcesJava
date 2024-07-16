Haz el mismo procedimiento que hice en aula, creando la clase PrincipalConBusqueda, que contiene el código que se integra con la API de OMDb.

La clase debe crearse con el siguiente código:

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        System.out.println("Escriba el nombre de una pelicula: ");
        var busqueda = lectura.nextLine();

        String clave = "COLOQUE AQUI SU CLAVE DE OMDb";
        String direccion = "https://www.omdbapi.com/?t="+busqueda+**"&apikey=”**+clave;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
