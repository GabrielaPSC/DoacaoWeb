package br.com.doacao.webapp.webcontroller;

import br.com.doacao.webapp.entity.Login;
import br.com.doacao.webapp.entity.TokenData;
import br.com.doacao.webapp.repository.LoginRepository;
import br.com.doacao.webapp.repository.TokenDataRepository;
import java.util.HashMap;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.CipherHelper;
import util.exceptions.CipherHelperException;

/**
 *
 * @author Gabriela Santos
 */

@Controller
public class LoginController {
    
    private final LoginRepository loginRepository;
    private final TokenDataRepository tokenDataRepository;
            
    @Autowired
    public LoginController(LoginRepository loginRepository, TokenDataRepository tokenDataRepository) {
        this.loginRepository = loginRepository;
        this.tokenDataRepository = tokenDataRepository;
    }

    @RequestMapping(value = "/logar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logar(@RequestBody Login login) {
        if (login == null) {
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        }
        
        Login usuario = loginRepository.findOne(login.getUsuario());
        
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        }
        
        if (usuario.getSenha().equals(login.getSenha())) {           
            try {
                TokenData tokenData = CipherHelper.generateTokenData(usuario);
                tokenDataRepository.save(tokenData);
                
                Map<String, Object> data = new HashMap<>();
                data.put("token", tokenData.getToken());
                data.put("instituicaoId", tokenData.getInstituicao().getId());
                data.put("instituicaoNome", tokenData.getInstituicao().getNome());
                
                return ResponseEntity.ok(data);
            } catch (CipherHelperException ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        }
        
        return ResponseEntity.badRequest().body("Credenciais inválidas");
    }
    
    @Transactional
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity novaProposta(Integer instituicaoId) {
        try {
            
            tokenDataRepository.deleteAllByInstituicaoId(instituicaoId);
            
            return ResponseEntity.ok("ok");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao fazer logout");
        }
    }
    
}
