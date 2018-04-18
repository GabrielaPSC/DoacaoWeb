/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.doacao.webapp.repository;

import br.com.doacao.webapp.entity.Geolocation;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Gabriela Santos
 */
public interface GeolocationRepository extends CrudRepository<Geolocation, Integer> {
    
}
