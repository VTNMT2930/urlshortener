package github.VTNMT2930.urlshortener.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "url_mappings")
@Data // Lombok: Tự động tạo getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Tự động tạo constructor không tham số
public class UrlMapping {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 1024)
  private String longUrl;

  @Column(nullable = false, unique = true)
  private String shortCode;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  // Trước khi lưu một đối tượng mới, tự động gán ngày giờ hiện tại
  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  public UrlMapping(String longUrl, String shortCode) {
    this.longUrl = longUrl;
    this.shortCode = shortCode;
  }
}
