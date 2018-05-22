package br.com.doacao.webapp;

import br.com.doacao.webapp.entity.TokenData;
import br.com.doacao.webapp.repository.TokenDataRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import util.CipherHelper;
import util.exceptions.CipherHelperException;


/**
 *
 * @author Gabriela Santos
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CrossOriginRequestFilter implements Filter {

    @Autowired
    private TokenDataRepository tokenDataRepository;
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        //AuthenticationRequestWrapper request = new AuthenticationRequestWrapper((HttpServletRequest) req);
        HttpServletResponse response = (HttpServletResponse) res;
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
        
        
        HttpServletRequest httpServletRequest = (HttpServletRequest)req;
        if(httpServletRequest.getRequestURL().toString().contains("/dash")){
        
            String token = httpServletRequest.getHeader("Authorization");
            if (token != null && tokenDataRepository.exists(token)) {
                TokenData tokenData = tokenDataRepository.findOne(token);
                req.setAttribute("instituicao", tokenData.getInstituicao());
                chain.doFilter(req, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            
            
        } else {
            chain.doFilter(req, response);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }
}
