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
        API api = API.IMDB_TOP_MOVIES;

        String url = api.getUrl();
        ContentExtractor extractor = api.getExtractor();

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
