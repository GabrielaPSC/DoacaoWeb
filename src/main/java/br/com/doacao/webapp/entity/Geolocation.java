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
public class Geolocation {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @JsonView({View.Instituicao.class, View.InstituicaoDetalhada.class})
    private Integer latitude;
    
    @JsonView({View.Instituicao.class, View.InstituicaoDetalhada.class})
    private Integer longitude;

    public Geolocation() {
    }

    public Geolocation(Integer latitude, Integer longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }
    
}
