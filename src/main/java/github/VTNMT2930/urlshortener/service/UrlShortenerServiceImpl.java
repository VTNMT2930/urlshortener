package github.VTNMT2930.urlshortener.service;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import github.VTNMT2930.urlshortener.model.UrlMapping;
import github.VTNMT2930.urlshortener.repository.UrlMappingRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

  private final UrlMappingRepository urlMappingRepository;

  // Sử dụng Constructor Injection, một best practice để inject dependency
  public UrlShortenerServiceImpl(UrlMappingRepository urlMappingRepository) {
    this.urlMappingRepository = urlMappingRepository;
  }

  @Override
  public UrlMapping createShortUrl(String longUrl) {
    // 1. Tạo một mã ngắn ngẫu nhiên gồm 6 ký tự chữ và số
    String shortCode;

    do {
      shortCode = RandomStringUtils.randomAlphanumeric(6);
    } while (urlMappingRepository.existsByShortCode(shortCode));

    // 2. Tạo một đối tượng UrlMapping mới
    UrlMapping urlMapping = new UrlMapping(longUrl, shortCode);

    // 3. Lưu vào cơ sở dữ liệu bằng repository và trả về đối tượng đã lưu
    return urlMappingRepository.save(urlMapping);
  }

  @Override
  public UrlMapping getLongUrl(String shortCode) {
    // Sử dụng phương thức đã tạo trong repository để tìm kiếm
    return urlMappingRepository.findByShortCode(shortCode)
        .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy mã rút gọn: " + shortCode));
  }
}
