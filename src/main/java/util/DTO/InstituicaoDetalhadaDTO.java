package util.DTO;

import br.com.doacao.webapp.entity.Endereco;
import br.com.doacao.webapp.entity.Instituicao;
import br.com.doacao.webapp.entity.Necessidade;
import br.com.doacao.webapp.entity.Telefone;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import util.View;

/**
 *
 * @author Gabriela Santos
 */
public class InstituicaoDetalhadaDTO {
    
    @JsonView(View.InstituicaoDetalhada.class)
    private Integer id;
    
    @JsonView(View.InstituicaoDetalhada.class)
    private String nome;
    
    @JsonView(View.InstituicaoDetalhada.class)
    private Endereco endereco;
    
    @JsonView(View.InstituicaoDetalhada.class)
    private List<Telefone> telefones;
    
    @JsonView(View.InstituicaoDetalhada.class)
    private List<Necessidade> necessidades;

    public InstituicaoDetalhadaDTO(Instituicao instituicao, List<Necessidade> necessidades) {
        this.id= instituicao.getId();
        this.nome = instituicao.getNome();
        this.endereco = instituicao.getEndereco();
        this.telefones = instituicao.getTelefones();
        this.necessidades = necessidades;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public List<Necessidade> getNecessidades() {
        return necessidades;
    }
}
