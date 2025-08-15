package github.VTNMT2930.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.VTNMT2930.urlshortener.model.UrlMapping;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

  Optional<UrlMapping> findByShortCode(String shortCode);

  boolean existsByShortCode(String shortCode);
}
