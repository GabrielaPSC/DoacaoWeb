/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.doacao.webapp.webcontroller;

import br.com.doacao.webapp.entity.Instituicao;
import br.com.doacao.webapp.repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Gabriela Santos
 */
@Controller
public class InstituicaoController {
    
    private final InstituicaoRepository instituicaoRepositor;

    @Autowired
    public InstituicaoController(InstituicaoRepository instituicaoRepositor) {
        this.instituicaoRepositor = instituicaoRepositor;
    }
    
    @RequestMapping("/cadastroinstituicao")
    public String cadastroDeInstituicoes() {
        return "cadastroInstituicao";
    }
    
    @RequestMapping("/cadastrar")
    public ResponseEntity cadastrar(Instituicao instituicao) {
       instituicaoRepositor.save(instituicao);
       return ResponseEntity.ok(instituicao);
    }
    
}
