package br.com.doacao.webapp.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Id;

/**
 *
 * @author Gabriela Santos
 */
@Entity
public class Instituicao {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private String nome;
    private TipoInstituicao tipo;
    
    @OneToOne
    private Login login;
    
    @OneToOne
    private Endereco endereco;
    
    @OneToOne
    private Geolocation geolocation;
    
    @OneToMany
    private List<Telefone> telefones;
    
    @OneToMany
    private List<Documento> documentos;

    public Instituicao(Login login, String nome, Endereco endereco, TipoInstituicao tipo, Geolocation geolocation, List<Telefone> telefones, List<Documento> documentos) {
        this.login = login;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.geolocation = geolocation;
        this.telefones = telefones;
        this.documentos = documentos;
    }

    public Integer getId() {
        return id;
    }

    public Login getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public TipoInstituicao getTipo() {
        return tipo;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }
    
    
}
