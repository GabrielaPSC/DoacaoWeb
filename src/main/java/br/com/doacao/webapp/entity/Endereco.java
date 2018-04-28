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
public class Endereco {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @JsonView({View.InstituicaoDetalhada.class})
    private String endereco;
    
    @JsonView({View.InstituicaoDetalhada.class})
    private String numero;
    
    @JsonView({View.InstituicaoDetalhada.class})
    private String complemento;
    
    @JsonView({View.InstituicaoDetalhada.class})
    private String bairro;
    
    @JsonView({View.InstituicaoDetalhada.class})
    private String cidade;
    
    @JsonView({View.InstituicaoDetalhada.class})
    private String estado;
    
    private String pais;
    private String codigo_postal;

    public Endereco() {
    }

    public Endereco(String endereco, String numero, String complemento, String bairro, String cidade, String estado, String pais, String codigo_postal) {
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.codigo_postal = codigo_postal;
    }

    public Integer getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

}
