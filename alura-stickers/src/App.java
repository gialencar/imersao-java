import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    /**
     * @param args
     * @throws Exception - caso não consiga fazer a requisição
     */
    public static void main(String[] args) throws Exception {

        // String apiKey = System.getenv("IMDB_API_KEY");

        // String url =
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // ContentExtractor extractor = new IMDBContentExtractor();

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        ContentExtractor extractor = new NasaContentExtractor();

        String json = new HTTPClient().fetchData(url);

        List<Content> contents = extractor.extractContents(json);

        var geradora = new GeradorDeFigurinhas();

        for (Content content : contents) {

            InputStream iStream = new URL(content.imageURL()).openStream();
            String fileName = content.title() + ".png";

            geradora.cria(iStream, fileName);
        }
    }
}
