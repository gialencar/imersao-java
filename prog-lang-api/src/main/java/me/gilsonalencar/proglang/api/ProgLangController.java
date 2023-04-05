package me.gilsonalencar.proglang.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgLangController {

  @Autowired
  private LanguageRepository repository;

  @GetMapping(value = "/linguagens")
  public List<Language> getLanguages() {
    return repository.findAll();
  }

  @PostMapping(value = "/linguagens")
  public Language saveLanguage(@RequestBody Language language) {
    Language savedLanguage = repository.save(language);
    return savedLanguage;
  }

}
