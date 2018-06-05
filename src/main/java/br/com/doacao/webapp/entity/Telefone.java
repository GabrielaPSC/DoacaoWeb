package br.com.doacao.webapp.entity;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import util.View;

/**
 *
 * @author Gabriela Santos
 */
@Entity
public class Telefone {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @JsonView({View.InstituicaoDetalhada.class})
    private String telefone;

    public Telefone() {
    }
    
    public Telefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public String getTelefone() {
        return telefone;
    }
    
}
