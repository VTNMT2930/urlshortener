package github.VTNMT2930.urlshortener.service;

import java.util.Optional;

import github.VTNMT2930.urlshortener.model.UrlMapping;

public interface UrlShortenerService {
  UrlMapping createShortUrl(String longUrl);

  UrlMapping getLongUrl(String shortCode);
}
