package br.com.doacao.webapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Gabriela Santos
 * 
 * Representação dos documentos enviados pela intituição
 */
@Entity
public class Documento {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private TipoDocumento tipo;
    private String image; //qual tipo usar?

    public Documento(TipoDocumento tipo, String image) {
        this.tipo = tipo;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public String getImage() {
        return image;
    }  
}
