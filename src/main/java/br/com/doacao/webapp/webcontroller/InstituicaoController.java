package br.com.doacao.webapp.webcontroller;

import br.com.doacao.webapp.entity.Instituicao;
import br.com.doacao.webapp.entity.Login;
import br.com.doacao.webapp.entity.Endereco;
import br.com.doacao.webapp.entity.Necessidade;
import br.com.doacao.webapp.entity.Proposta;
import br.com.doacao.webapp.entity.StatusProposta;
import br.com.doacao.webapp.repository.EnderecoRepository;
import br.com.doacao.webapp.repository.GeolocationRepository;
import br.com.doacao.webapp.repository.InstituicaoRepository;
import br.com.doacao.webapp.repository.LoginRepository;
import br.com.doacao.webapp.repository.NecessidadeRepository;
import br.com.doacao.webapp.repository.PropostaRepository;
import br.com.doacao.webapp.repository.TokenDataRepository;
import com.fasterxml.jackson.annotation.JsonView;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import util.DTO.GraficoPropostaDTO;
import util.DTO.InstituicaoDetalhadaDTO;
import util.View;

/**
 * Controller para as requisições referentes as instituições
 *
 * @author Gabriela Santos
 */
@RequestMapping("/instituicao")
@Controller
public class InstituicaoController {

    private final InstituicaoRepository instituicaoRepository;
    private final EnderecoRepository enderecoRepository;
    private final GeolocationRepository geolocationRepository;
    private final LoginRepository loginRepository;
    private final TokenDataRepository tokenDataRepository;
    private final PropostaRepository propostaRepository;
    private final NecessidadeRepository necessidadeRepository;

    public InstituicaoController(InstituicaoRepository instituicaoRepository, EnderecoRepository enderecoRepository, GeolocationRepository geolocationRepository, LoginRepository loginRepository, TokenDataRepository tokenDataRepository, PropostaRepository propostaRepository, NecessidadeRepository necessidadeRepository) {
        this.instituicaoRepository = instituicaoRepository;
        this.enderecoRepository = enderecoRepository;
        this.geolocationRepository = geolocationRepository;
        this.loginRepository = loginRepository;
        this.tokenDataRepository = tokenDataRepository;
        this.propostaRepository = propostaRepository;
        this.necessidadeRepository = necessidadeRepository;
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

        Instituicao instituicaoSalva = instituicaoRepository.save(instituicao);

        Login login = instituicaoSalva.getLogin();
        login.setInstituicao(instituicao);

        loginRepository.save(login);

        return ResponseEntity.ok("Cadastro realizado com sucesso!");
    }

    @JsonView(View.Instituicao.class)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok(instituicaoRepository.findAll());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao recuperar instituições");
        }
    }

    @JsonView(View.InstituicaoDetalhada.class)
    @RequestMapping(value = "/detalhes", method = RequestMethod.GET)
    public ResponseEntity findDetalhes(@RequestParam Integer instituicaoId) {
        try {
            return ResponseEntity.ok(
                    new InstituicaoDetalhadaDTO(
                        instituicaoRepository.findOne(instituicaoId),
                        necessidadeRepository.findAllByInstituicaoId(instituicaoId)
                    )
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao recuperar instituição");
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

    @RequestMapping("/graficos")
    public String graficos() {
        return "graficos";
    }
    
    @RequestMapping("/necessidades")
    public String necessidades() {
        return "necessidades";
    }
    
    @JsonView(View.Proposta.class)
    @RequestMapping(value = "/dash/propostas", method = RequestMethod.GET)
    public ResponseEntity findAllPropostasByInstituicaoId(Integer instituicaoId) {
        try {
            return ResponseEntity.ok(propostaRepository.findAllByInstituicaoIdAndDataDeferimentoIsNullOrderByDataPropostaAsc(instituicaoId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao recuperar propostas");
        }
    }
    
    @RequestMapping(value = "/dash/quantidadePropostas", method = RequestMethod.GET)
    public ResponseEntity quantidadePropostas(Integer instituicaoId) {
        try {
           
            List<Proposta> propostas = propostaRepository.findAllByInstituicaoIdOrderByDataPropostaAsc(instituicaoId);
            
            List<GraficoPropostaDTO> quantidadeList = new ArrayList<>();
            Map<String, Integer> data = new HashMap<>();
            String dataPropostaFormatada;
            
            if (propostas == null || propostas.isEmpty()) {
                return ResponseEntity.ok(quantidadeList);
            }
            
            for (Proposta proposta : propostas) {
                
                dataPropostaFormatada = proposta.getDataPropostaFormatada();
                
                if (data.containsKey(dataPropostaFormatada)) {
                    data.put(
                            dataPropostaFormatada, 
                            data.get(dataPropostaFormatada) + 1
                    );
                } else {
                    data.put(dataPropostaFormatada, 1);
                }
                
            }  
            
            data.keySet().forEach((key) -> {
                quantidadeList.add(new GraficoPropostaDTO(
                                key, 
                                data.get(key)
                        )
                );
            });
            
            Collections.sort(quantidadeList, 
                        (o1, o2) -> o1.getData().compareTo(o2.getData()));
            
            return ResponseEntity.ok(quantidadeList);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao recuperar propostas");
        }
    }
    
    @RequestMapping(value = "/dash/propostasAceitas", method = RequestMethod.GET)
    public ResponseEntity propostasAceitas(Integer instituicaoId) {
        try {
            List<Proposta> propostas = propostaRepository.findAllByInstituicaoIdAndDeferimentoTrueOrderByDataDeferimentoAsc(instituicaoId);

            List<GraficoPropostaDTO> quantidadeList = new ArrayList<>();
            
            if (propostas == null || propostas.isEmpty()) {
                return ResponseEntity.ok(quantidadeList);
            }
            
            Map<String, Integer> data = new HashMap<>();
            String mes;
            
            for (Proposta proposta : propostas) {
            
                if (proposta.getDataDeferimento().getYear() != ZonedDateTime.now().getYear()){
                    continue;
                }
                
                mes = proposta.getDataDeferimento().getMonth().toString();
                
                if (data.containsKey(proposta.getDataDeferimento().getMonth().toString())) {
                    data.put(
                            mes, 
                            data.get(mes) + 1
                    );
                } else {
                    data.put(mes, 1);
                }
                
            }  
            
            data.keySet().forEach((key) -> {
                quantidadeList.add(new GraficoPropostaDTO(
                                key, 
                                data.get(key)
                        )
                );
            });
            
            Collections.sort(quantidadeList, 
                        (o1, o2) -> o1.getData().compareTo(o2.getData()));
            
            return ResponseEntity.ok(quantidadeList);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao recuperar propostas");
        }
    }
    
    @RequestMapping(value = "/dash/statusPropostas", method = RequestMethod.GET)
    public ResponseEntity statusPropostas(Integer instituicaoId) {
        try {
           
            List<Proposta> propostas = propostaRepository.findAllByInstituicaoId(instituicaoId);

            List<GraficoPropostaDTO> quantidadeList = new ArrayList<>();
            
            if (propostas == null || propostas.isEmpty()) {
                return ResponseEntity.ok(quantidadeList);
            }
            
            Map<String, Integer> data = new HashMap<>();
            StatusProposta statusProposta;
            
            for (Proposta proposta : propostas) {
            
                if (proposta.getDeferimento() == null) {
                    statusProposta = StatusProposta.AGUARDANDO_PROCESSAMENTO;
                }
                else if (proposta.getDeferimento()){
                    statusProposta = StatusProposta.DEFERIDA;
                } else {
                    statusProposta = StatusProposta.INDEFERIDA;
                }
                
                if (data.containsKey(statusProposta.toString())) {
                    data.put(
                            statusProposta.toString(), 
                            data.get(statusProposta.toString()) + 1
                    );
                } else {
                    data.put(statusProposta.toString(), 1);
                }
            }  
            
            data.keySet().forEach((key) -> {
                quantidadeList.add(new GraficoPropostaDTO(
                                key, 
                                data.get(key)
                        )
                );
            });
            
            return ResponseEntity.ok(quantidadeList);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao recuperar propostas");
        }
    }
    
    @RequestMapping(value = "/dash/deferirProposta", method = RequestMethod.GET)
    public ResponseEntity deferirProposta(Boolean deferido, Integer propostaId) {
        try {
            Proposta proposta = propostaRepository.findOne(propostaId);
            
            if(proposta == null) {
                return ResponseEntity.badRequest().body("Proposta não encontrada");
            }

            proposta.setDataDeferimento(ZonedDateTime.now());
            proposta.setDeferimento(deferido);
            
            propostaRepository.save(proposta);
            
            return ResponseEntity.ok("Status da proposta alterado com sucesso");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao alterar status da proposta");
        }
    }
    
    @JsonView(View.Necessidade.class)
    @RequestMapping(value = "/dash/registrarNecessidade", method = RequestMethod.POST)
    public ResponseEntity registrarNecessidade(@RequestBody Necessidade necessidade) {
        try {
            necessidade.setInstituicao(instituicaoRepository.findOne(necessidade.getInstituicao().getId()));
            Necessidade necessidadeSalva = necessidadeRepository.save(necessidade);
            return ResponseEntity.ok(necessidadeSalva);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao salvar necessidade");
        }
    }
    
    @JsonView(View.Necessidade.class)
    @RequestMapping(value = "/baixarNecessidades", method = RequestMethod.GET)
    public ResponseEntity baixarNecessidades(Integer instituicaoId) {
        try {
            return ResponseEntity.ok(necessidadeRepository.findAllByInstituicaoId(instituicaoId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao salvar necessidade");
        }
    }
    
    @RequestMapping(value = "/dash/removerNecessidade", method = RequestMethod.GET)
    public ResponseEntity removerNecessidade(Integer necessidadeId) {
        try {
            necessidadeRepository.delete(necessidadeId);
            
            return ResponseEntity.ok("Necessidade removida com sucesso");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao remover necessidade");
        }
    }
}
