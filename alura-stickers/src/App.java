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

        // String apiKey = System.getenv("IMDB_API_KEY");
        // String url = "https://imdb-api.com/en/API/Top250Movies/" + apiKey;
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI address = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().uri(address).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> movies = parser.parse(body);

        String bold = "\u001b[1m";
        String greenTeal = "\u001b[32;1m";
        String blueBackground = "\u001b[44;1m";
        String greenBackground = "\u001b[42;1m";
        String star2Emoji = "\uD83C\uDF1F";
        String starEmoji = "\u2B50";
        String clear = "\u001b[m";

        System.out.printf("\n%s%s%s%s Filmes mais populares %s%s\n", bold, greenTeal, greenBackground, star2Emoji, starEmoji, clear);
        for (Map<String, String> movie : movies) {
            String title = movie.get("title");
            String poster = movie.get("image");
            String ImDbRatings = movie.get("imDbRating");

            System.out.printf("%s%s%s\n", blueBackground, title, clear);
            System.out.printf("%sNota:%s %s ", bold, clear, ImDbRatings);
            System.out.printf("%s\n", starEmoji.repeat(Math.round(Float.parseFloat(ImDbRatings))));
            System.out.printf("%sPoster:%s %s\n", bold, clear, poster);
        }
    }
}
