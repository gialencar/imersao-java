public enum API {
  IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json",
      new IMDBContentExtractor()),
  NASA_APOD("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json",
      new NasaContentExtractor());

  private String url;
  private ContentExtractor extractor;

  API(String url, ContentExtractor extractor) {
    this.url = url;
    this.extractor = extractor;
  }

  public String getUrl() {
    return url;
  }

  public ContentExtractor getExtractor() {
    return extractor;
  }
}
