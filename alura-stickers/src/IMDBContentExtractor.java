import java.util.List;
import java.util.Map;

public class IMDBContentExtractor implements ContentExtractor {

  public List<Content> extractContents(String json) {

    var parser = new JsonParser();
    List<Map<String, String>> attributesList = parser.parse(json);

    return attributesList.stream()
        .map((attributes) -> (new Content(attributes.get("title"),
            attributes.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg"))))
        .toList();
  }

}