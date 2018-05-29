package br.com.doacao.webapp.entity;

/**
 *
 * @author Gabriela Santos
 */
public enum StatusProposta {
    
    DEFERIDA("Deferida"),
    INDEFERIDA("Indeferida"),
    AGUARDANDO_PROCESSAMENTO("Aguardado processamento");
    
    String status;
    
    StatusProposta(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
   
}
