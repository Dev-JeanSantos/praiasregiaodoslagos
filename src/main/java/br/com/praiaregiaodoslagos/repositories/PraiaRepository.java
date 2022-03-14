package br.com.praiaregiaodoslagos.repositories;

import br.com.praiaregiaodoslagos.entities.Cidade;
import br.com.praiaregiaodoslagos.entities.Praia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraiaRepository extends JpaRepository<Praia, Long> {
}
