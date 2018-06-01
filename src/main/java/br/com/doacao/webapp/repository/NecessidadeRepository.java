package br.com.doacao.webapp.repository;

import br.com.doacao.webapp.entity.Necessidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Gabriela Santos
 */
public interface NecessidadeRepository extends JpaRepository<Necessidade, Integer> {
 
    public List<Necessidade> findAllByInstituicaoId(Integer instituicaoId);
}
