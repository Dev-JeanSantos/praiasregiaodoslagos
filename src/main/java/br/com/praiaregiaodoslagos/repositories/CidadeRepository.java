package br.com.praiaregiaodoslagos.repositories;

import br.com.praiaregiaodoslagos.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
