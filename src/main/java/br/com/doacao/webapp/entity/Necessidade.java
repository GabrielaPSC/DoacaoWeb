package br.com.doacao.webapp.entity;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import util.View;

/**
 *
 * @author Gabriela Santos
 */
@Entity
public class Necessidade {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonView(View.Necessidade.class)
    private Integer id;
    
    @JsonView(View.Necessidade.class)
    private String necessidade;
    
    @JsonView(View.Necessidade.class)
    private Integer quantidade;
    
    @ManyToOne
    private Instituicao instituicao;

    public Integer getId() {
        return id;
    }

    public String getNecessidade() {
        return necessidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

}
