package me.gilsonalencar.proglang.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "principais-linguagens")
public class Language {

  @Id
  private String id;
  private String title;
  private String image;
  private int ranking;
  // private String description;
  // private String paradigm;
  // private String year;

  public Language() {
  }

  public Language(String name, String image, int ranking) {
    this.title = name;
    this.image = image;
    this.ranking = ranking;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getImage() {
    return image;
  }

  public int getRanking() {
    return ranking;
  }

}
