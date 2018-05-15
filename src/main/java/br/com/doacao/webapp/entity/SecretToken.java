/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.doacao.webapp.entity;

/**
 *
 * @author gabi_
 */
public enum SecretToken {
    KEY("G@l0571d0@c@0!F!");
    
    private String secretToken;
    
    SecretToken(String secretToken) {
        this.secretToken = secretToken;
    }

    @Override
    public String toString() {
        return secretToken;
    }
     
}
