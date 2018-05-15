package util;

import br.com.doacao.webapp.entity.Login;
import br.com.doacao.webapp.entity.SecretToken;
import br.com.doacao.webapp.entity.TokenData;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import util.exceptions.CipherHelperException;

/**
 *
 * @author Gabriela Santos
 */
public class CipherHelper {
    
    public static TokenData generateTokenData(Login login) throws CipherHelperException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                        SecretToken.KEY.toString().getBytes(), 
                        "AES"
                );

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            
            String userData = login.getUsuario() + "/@/" + login.getSenha();
            byte[] token = cipher.doFinal(userData.getBytes());
            
            return new TokenData(token.toString(), login.getInstituicao());
        } catch(Exception ex) {
            throw new CipherHelperException("Erro ao gerar token", ex.getCause());
        }
    }
    
    public static String getUserFromToken(String token) throws CipherHelperException {       
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                        SecretToken.KEY.toString().getBytes(), 
                        "AES"
                );

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);	 	 
		 
            byte[] tokenData = cipher.doFinal(token.getBytes());
            
            return new String(tokenData).split("/@/")[0];
        } catch(Exception ex) {
            throw new CipherHelperException("Erro ao extrair dados do token", ex.getCause());
        }
    }
}
