package me.gilsonalencar.proglang.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgLangController {

  @Autowired
  private LanguageRepository repository;

  @GetMapping(value = "/linguagens")
  public List<Language> getLanguages() {
    return repository.findByOrderByRanking();
  }

  @PostMapping(value = "/linguagens")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Language saveLanguage(@RequestBody Language language) {
    Language savedLanguage = repository.save(language);
    return savedLanguage;
  }

  @PutMapping(value = "/linguagens")
  public Language updateLanguage(@RequestBody Language language, @RequestParam String id) {

    Language languageToUpdate = repository.findById(id).get();

    languageToUpdate.setTitle(language.getTitle());
    languageToUpdate.setImage(language.getImage());
    languageToUpdate.setRanking(language.getRanking());

    return repository.save(languageToUpdate);

  }

  @DeleteMapping(value = "/linguagens")
  public ResponseStatus deleteLanguage(@RequestParam String id) {
    repository.deleteById(id);
    return null;
  }
}
