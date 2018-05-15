package br.com.doacao.webapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gabriela Santos
 */
@Entity
public class TokenData {
    
    @Id
    private String token;
    
    @ManyToOne
    private Instituicao instituicao;

    public TokenData() {
    }

    public TokenData(String token, Instituicao instituicao) {
        this.token = token;
        this.instituicao = instituicao;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
    
    
}
