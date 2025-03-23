package io.github.fogeid.testeSeplag.repositories;

import io.github.fogeid.testeSeplag.entities.ServidorTemporario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, Long> {
}
