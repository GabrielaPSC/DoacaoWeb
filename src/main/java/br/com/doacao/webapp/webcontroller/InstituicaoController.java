package br.com.doacao.webapp.webcontroller;

import br.com.doacao.webapp.entity.Instituicao;
import br.com.doacao.webapp.repository.EnderecoRepository;
import br.com.doacao.webapp.repository.GeolocationRepository;
import br.com.doacao.webapp.repository.InstituicaoRepository;
import br.com.doacao.webapp.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller para as requisições referentes as instituições
 * 
 * @author Gabriela Santos
 */
@RequestMapping("/instituicao")
@Controller
public class InstituicaoController {
    
    private final InstituicaoRepository instituicaoRepositor;
    private final EnderecoRepository enderecoRepository;
    private final GeolocationRepository geolocationRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public InstituicaoController(InstituicaoRepository instituicaoRepositor, EnderecoRepository enderecoRepository, GeolocationRepository geolocationRepository, LoginRepository loginRepository) {
        this.instituicaoRepositor = instituicaoRepositor;
        this.enderecoRepository = enderecoRepository;
        this.geolocationRepository = geolocationRepository;
        this.loginRepository = loginRepository;
    }
    
    @RequestMapping("/cadastro")
    public String cadastroDeInstituicoes() {
        return "cadastroInstituicao";
    }
    
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cadastrar(@RequestBody Instituicao instituicao) {
       loginRepository.save(instituicao.getLogin());
       enderecoRepository.save(instituicao.getEndereco());
       geolocationRepository.save(instituicao.getGeolocation());
       instituicaoRepositor.save(instituicao);
       return ResponseEntity.ok("Cadastro realizado com sucesso!");
    }
    
}
