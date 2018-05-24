package br.com.doacao.webapp.repository;

import br.com.doacao.webapp.entity.Proposta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Gabriela Santos
 */
public interface PropostaRepository extends JpaRepository<Proposta, Integer> {

    public List<Proposta>findAllByInstituicaoIdAndDataDeferimentoIsNull(Integer instituicaoId);
    
}
