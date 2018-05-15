package br.com.doacao.webapp.repository;

import br.com.doacao.webapp.entity.TokenData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Persistencia para o token
 * @author Gabriela Santos
 */
public interface TokenDataRepository extends JpaRepository<TokenData, String>{
    
}
