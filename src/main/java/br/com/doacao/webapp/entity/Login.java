package br.com.doacao.webapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Gabriela Santos
 * 
 * Representa o login de um usuario
 */
@Entity
public class Login {
    
    @Id
    private String usuario;
    private String senha;
    
    @OneToOne
    private Instituicao instituicao;

    public Login() {
    }

    public Login(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
    
}
