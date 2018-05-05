package br.com.doacao.webapp.webcontroller;

import br.com.doacao.webapp.entity.Instituicao;
import br.com.doacao.webapp.entity.Login;
import br.com.doacao.webapp.entity.Endereco;
import br.com.doacao.webapp.entity.Geolocation;
import br.com.doacao.webapp.repository.EnderecoRepository;
import br.com.doacao.webapp.repository.GeolocationRepository;
import br.com.doacao.webapp.repository.InstituicaoRepository;
import br.com.doacao.webapp.repository.LoginRepository;
import com.fasterxml.jackson.annotation.JsonView;
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
       Login login = new Login();
       Login usuario = loginRepository.findOne(login.getUsuario());
       if (instituicao.getLogin() == null){
 	     return ResponseEntity.badRequest().body("Credencial inválida.");
       }
       if(usuario.getUsuario() == null){
            return ResponseEntity.badRequest().body("Campo Usuário inválida.");            
        }
       if (login.getSenha().equals(login.getSenha())){
            return ResponseEntity.ok("ok");
        }      
       loginRepository.save(instituicao.getLogin()); 


       
       Endereco endereco = new Endereco();
       if (instituicao.getEndereco() == null){
           return ResponseEntity.badRequest().body("Credenciais Inválidas.");
       }
       if (endereco.getEndereco() == null){
           return ResponseEntity.badRequest().body("Endereço da instituição inválido."); 
       }
       if (endereco.getNumero() == null){
           return ResponseEntity.badRequest().body("Número da instituição inválido."); 
       }
       if (endereco.getBairro() == null){
           return ResponseEntity.badRequest().body("Bairro da instituição inválido."); 
       }
       if (endereco.getCidade() == null){
           return ResponseEntity.badRequest().body("Cidade da instituição inválida."); 
       }
       if (endereco.getEstado() == null){
           return ResponseEntity.badRequest().body("Estado da instituição inválido."); 
       }
       if (endereco.getPais() == null){
           return ResponseEntity.badRequest().body("País da instituição inválido."); 
       }
       enderecoRepository.save(instituicao.getEndereco()); 

       
       
       Geolocation geolocation = new Geolocation();
       if (instituicao.getGeolocation() == null){
 	   return ResponseEntity.badRequest().body("Localização da instituição inválido.");
       }
       if ((geolocation.getLatitude() == null)||(geolocation.getLongitude() == null)){
           return ResponseEntity.badRequest().body("Latitudde e/ou Longitude da instituição inválido.");
       }
       geolocationRepository.save(instituicao.getGeolocation()); 

      if (instituicao.getNome() == null){
           return ResponseEntity.badRequest().body("Nome da instituição inválido.");
       }
       if(instituicao.getTelefones() == null){
           return ResponseEntity.badRequest().body("Telefone da instituição inválido.");
       }
       if(instituicao.getTipo() == null){
           return ResponseEntity.badRequest().body("Tipo da instituição inválido.");
       }
       if(instituicao.getDocumentos() == null){
           return ResponseEntity.badRequest().body("Documentos da instituição inválidos.");
       }
       instituicaoRepositor.save(instituicao); 
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
