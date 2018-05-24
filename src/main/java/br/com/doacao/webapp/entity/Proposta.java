package br.com.doacao.webapp.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;
import util.View;
import util.ZonedDateTimeToIso8601Serializer;

/**
 *
 * @author Gabriela Santos
 */
@Entity
public class Proposta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonView(View.Proposta.class)
    private Integer id;
    
    @JsonView(View.Proposta.class)
    private String nomeDoador;
    
    @JsonView(View.Proposta.class)
    private String telefone;
    
    @JsonView(View.Proposta.class)
    private String email;
    
    @JsonView(View.Proposta.class)
    private String nomeDoacao;
    
    @JsonView(View.Proposta.class)
    private Integer quantidade;
    
    @JsonSerialize(using = ZonedDateTimeToIso8601Serializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXX")
    @JsonView(View.Proposta.class)
    private ZonedDateTime dataProposta = ZonedDateTime.now();
    
    @JsonSerialize(using = ZonedDateTimeToIso8601Serializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXX")
    private ZonedDateTime dataDeferimento;
    
    @ManyToOne
    private Instituicao instituicao;

    private Proposta() {
    }

    public Proposta(String nomeDoador, String telefone, String email, String nomeDoacao, Integer quantidade, Instituicao instituicao) {
        this.nomeDoador = nomeDoador;
        this.telefone = telefone;
        this.email = email;
        this.nomeDoacao = nomeDoacao;
        this.quantidade = quantidade;
        this.instituicao = instituicao;
    }

    public Integer getId() {
        return id;
    }

    public String getNomeDoador() {
        return nomeDoador;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getNomeDoacao() {
        return nomeDoacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public ZonedDateTime getDataProposta() {
        return dataProposta;
    }

    public ZonedDateTime getDataDeferimento() {
        return dataDeferimento;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }
    
}
