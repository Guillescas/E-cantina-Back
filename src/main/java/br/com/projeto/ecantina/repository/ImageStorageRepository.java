package br.com.projeto.ecantina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.ecantina.models.ImageStorage;

public interface ImageStorageRepository extends JpaRepository<ImageStorage, Long> {
    
}
