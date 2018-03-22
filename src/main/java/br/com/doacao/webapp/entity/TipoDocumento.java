package br.com.doacao.webapp.entity;

/**
 *
 * @author Gabriela Santos
 */
public enum TipoDocumento {
    
    CNPJ("CNPJ"),
    ESTATUTO_IGREJA("Estatuto da igreja");

    private String tipo;
    
    TipoDocumento(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
           
}
