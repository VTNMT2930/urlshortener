package github.VTNMT2930.urlshortener.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ErrorResponse {
  private int statusCode;
  private String message;
  private LocalDate timestamp;

  public ErrorResponse(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
    this.timestamp = LocalDate.now();
  }
}
