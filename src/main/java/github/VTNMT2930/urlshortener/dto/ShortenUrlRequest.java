package github.VTNMT2930.urlshortener.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShortenUrlRequest {
  @NotBlank(message = "URL không được để trống")
  @URL(message = "URL không hợp lệ")
  private String longUrl;
}
