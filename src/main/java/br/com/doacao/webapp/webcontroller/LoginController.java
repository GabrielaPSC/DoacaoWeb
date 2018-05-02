package br.com.doacao.webapp.webcontroller;

import br.com.doacao.webapp.entity.Login;
import br.com.doacao.webapp.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Gabriela Santos
 */

@Controller
public class LoginController {
    
    private final LoginRepository loginRepository;

    @Autowired
    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
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
            return ResponseEntity.ok("ok");
        }
        
        return ResponseEntity.badRequest().body("Credenciais inválidas");
    }
    
}
