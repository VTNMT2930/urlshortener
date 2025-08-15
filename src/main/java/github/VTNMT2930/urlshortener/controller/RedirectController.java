package github.VTNMT2930.urlshortener.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import github.VTNMT2930.urlshortener.model.UrlMapping;
import github.VTNMT2930.urlshortener.service.UrlShortenerService;

@RestController
public class RedirectController {

  private final UrlShortenerService urlShortenerService;

  public RedirectController(UrlShortenerService urlShortenerService) {
    this.urlShortenerService = urlShortenerService;
  }

  @GetMapping("/{shortCode}")
  public ResponseEntity<Void> redirectToLongUrl(@PathVariable String shortCode) {
    UrlMapping urlMapping = urlShortenerService.getLongUrl(shortCode);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(URI.create(urlMapping.getLongUrl()));
    return new ResponseEntity<>(headers, HttpStatus.FOUND);
  }
}
