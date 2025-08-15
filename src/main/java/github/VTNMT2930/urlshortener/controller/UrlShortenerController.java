package github.VTNMT2930.urlshortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.VTNMT2930.urlshortener.dto.ShortenUrlRequest;
import github.VTNMT2930.urlshortener.dto.ShortenUrlResponse;
import github.VTNMT2930.urlshortener.model.UrlMapping;
import github.VTNMT2930.urlshortener.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UrlShortenerController {

  private final UrlShortenerService urlShortenerService;

  public UrlShortenerController(UrlShortenerService urlShortenerService) {
    this.urlShortenerService = urlShortenerService;
  }

  @PostMapping("/shorten")
  public ResponseEntity<ShortenUrlResponse> createShortUrl(@Valid @RequestBody ShortenUrlRequest request,
      HttpServletRequest servletRequest) {
    UrlMapping urlMapping = urlShortenerService.createShortUrl(request.getLongUrl());

    // Xây dựng URL đầy đủ trả về cho người dùng (ví dụ:
    // http://localhost:8080/aB1xYz)
    String fullShortUrl = servletRequest.getRequestURL().toString().replace(servletRequest.getRequestURI(), "")
        + "/" + urlMapping.getShortCode();

    return new ResponseEntity<>(new ShortenUrlResponse(fullShortUrl), HttpStatus.CREATED);
  }
}
