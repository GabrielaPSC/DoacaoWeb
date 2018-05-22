package br.com.doacao.webapp.webcontroller;

import br.com.doacao.webapp.entity.Instituicao;
import br.com.doacao.webapp.entity.Login;
import br.com.doacao.webapp.entity.TokenData;
import br.com.doacao.webapp.repository.EnderecoRepository;
import br.com.doacao.webapp.repository.GeolocationRepository;
import br.com.doacao.webapp.repository.InstituicaoRepository;
import br.com.doacao.webapp.repository.LoginRepository;
import br.com.doacao.webapp.repository.TokenDataRepository;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.java.swing.plaf.windows.resources.windows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import util.View;

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
    private final TokenDataRepository tokenDataRepository;

    @Autowired
    public InstituicaoController(InstituicaoRepository instituicaoRepositor, EnderecoRepository enderecoRepository, GeolocationRepository geolocationRepository, LoginRepository loginRepository, TokenDataRepository tokenDataRepository) {
        this.instituicaoRepositor = instituicaoRepositor;
        this.enderecoRepository = enderecoRepository;
        this.geolocationRepository = geolocationRepository;
        this.loginRepository = loginRepository;
        this.tokenDataRepository = tokenDataRepository;
    }
    
    @RequestMapping("/cadastro")
    public String cadastroDeInstituicoes() {
        return "cadastroInstituicao";
    }
    
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cadastrar(@RequestBody Instituicao instituicao) {
        //return ResponseEntity.badRequest().body("qual campo esta errado");
        
       loginRepository.save(instituicao.getLogin());
       enderecoRepository.save(instituicao.getEndereco());
       geolocationRepository.save(instituicao.getGeolocation());
       
       Instituicao instituicaoSalva = instituicaoRepositor.save(instituicao);
        
       Login login = instituicaoSalva.getLogin();
       login.setInstituicao(instituicao);
       
       loginRepository.save(login);
               
       return ResponseEntity.ok("Cadastro realizado com sucesso!");
    }
    
    @JsonView(View.Instituicao.class)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok(instituicaoRepositor.findAll());
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao recuperar instituições");
        }
    }
    
    @JsonView(View.InstituicaoDetalhada.class)
    @RequestMapping(value = "/detalhes", method = RequestMethod.GET)
    public ResponseEntity findDetalhes(@RequestParam Integer instituicaoId) {
        try {
            return ResponseEntity.ok(instituicaoRepositor.findOne(instituicaoId));
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao recuperar instituições");
        }
    }
    
    @RequestMapping("/dash")
    public String dash() {
        return "dash";
    }
}
