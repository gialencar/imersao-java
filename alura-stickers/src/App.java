import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().uri(address).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();
        System.out.println(body);

        var parser = new JsonParser();
        List<Map<String, String>> movies = parser.parse(body);

        for (Map<String, String> movie : movies) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
            System.out.println();
        }
    }
}
