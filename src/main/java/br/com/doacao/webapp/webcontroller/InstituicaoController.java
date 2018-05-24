package br.com.doacao.webapp.webcontroller;

import br.com.doacao.webapp.entity.Instituicao;
import br.com.doacao.webapp.entity.Login;
import br.com.doacao.webapp.entity.Endereco;
import br.com.doacao.webapp.entity.Proposta;
import br.com.doacao.webapp.repository.EnderecoRepository;
import br.com.doacao.webapp.repository.GeolocationRepository;
import br.com.doacao.webapp.repository.InstituicaoRepository;
import br.com.doacao.webapp.repository.LoginRepository;
import br.com.doacao.webapp.repository.PropostaRepository;
import br.com.doacao.webapp.repository.TokenDataRepository;
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
    private final TokenDataRepository tokenDataRepository;
    private final PropostaRepository propostaRepository;

    @Autowired
    public InstituicaoController(InstituicaoRepository instituicaoRepositor, EnderecoRepository enderecoRepository, GeolocationRepository geolocationRepository, LoginRepository loginRepository, TokenDataRepository tokenDataRepository, PropostaRepository propostaRepository) {
        this.instituicaoRepositor = instituicaoRepositor;
        this.enderecoRepository = enderecoRepository;
        this.geolocationRepository = geolocationRepository;
        this.loginRepository = loginRepository;
        this.tokenDataRepository = tokenDataRepository;
        this.propostaRepository = propostaRepository;
    }

    @RequestMapping("/cadastro")
    public String cadastroDeInstituicoes() {
        return "cadastroInstituicao";
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cadastrar(@RequestBody Instituicao instituicao) {
       
        if (loginRepository.exists(instituicao.getLogin().getUsuario())) {
            return ResponseEntity.badRequest().body("Já existe uma instituição cadastrada com esse endereço de email.");
        }
        
        Endereco endereco = instituicao.getEndereco();

        if (endereco == null) {
            return ResponseEntity.badRequest().body("Credenciais Inválidas.");
        }
        if (endereco.getEndereco() == null) {
            return ResponseEntity.badRequest().body("Endereço da instituição inválido.");
        }
        if (endereco.getNumero() == null) {
            return ResponseEntity.badRequest().body("Número da instituição inválido.");
        }
        if (endereco.getBairro() == null) {
            return ResponseEntity.badRequest().body("Bairro da instituição inválido.");
        }
        if (endereco.getCidade() == null) {
            return ResponseEntity.badRequest().body("Cidade da instituição inválida.");
        }
        if (endereco.getEstado() == null) {
            return ResponseEntity.badRequest().body("Estado da instituição inválido.");
        }
        if (endereco.getPais() == null) {
            return ResponseEntity.badRequest().body("País da instituição inválido.");
        }
        if (instituicao.getGeolocation() == null) {
            return ResponseEntity.badRequest().body("Localização da instituição inválido.");
        }
        if (instituicao.getGeolocation().getLatitude() == null || instituicao.getGeolocation().getLongitude() == null) {
            return ResponseEntity.badRequest().body("Latitudde e/ou Longitude da instituição inválido.");
        }
        if (instituicao.getNome() == null) {
            return ResponseEntity.badRequest().body("Nome da instituição inválido.");
        }
        if (instituicao.getTelefones() == null) {
            return ResponseEntity.badRequest().body("Telefone da instituição inválido.");
        }
        if (instituicao.getTipo() == null) {
            return ResponseEntity.badRequest().body("Tipo da instituição inválido.");
        }
        if (instituicao.getDocumentos() == null) {
            return ResponseEntity.badRequest().body("Documentos da instituição inválidos.");
        }
       
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
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao recuperar instituições");
        }
    }

    @JsonView(View.InstituicaoDetalhada.class)
    @RequestMapping(value = "/detalhes", method = RequestMethod.GET)
    public ResponseEntity findDetalhes(@RequestParam Integer instituicaoId) {
        try {
            return ResponseEntity.ok(instituicaoRepositor.findOne(instituicaoId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao recuperar instituições");
        }
    }

    @RequestMapping("/dash")
    public String dash() {
        return "dash";
    }
    
    @RequestMapping("/propostas")
    public String propostas() {
        return "propostas";
    }
    
    @JsonView(View.Proposta.class)
    @RequestMapping(value = "/dash/propostas", method = RequestMethod.GET)
    public ResponseEntity findAllPropostasByInstituicaoId(Integer instituicaoId) {
        try {
            return ResponseEntity.ok(propostaRepository.findAllByInstituicaoIdAndDataDeferimentoIsNull(instituicaoId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao recuperar propostas");
        }
    }
}
