package br.com.doacao.webapp.entity;

/**
 *
 * @author Gabriela Santos
 * 
 * Representação do tipo da instituição de caridade
 */
public enum TipoInstituicao {
    IGREJA("Igreja"),
    ENTIDADE_CARIDADE("Entidade de caridade"),
    CENTRO_COMUNITARIO("Centro comunitário"),
    LAR_IDOSOS("Lar de idosos"),
    ORFANATO("Orfanato");

    private String tipo;
    
    private TipoInstituicao(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
        
}
