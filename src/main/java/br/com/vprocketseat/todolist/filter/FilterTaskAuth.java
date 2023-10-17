package br.com.vprocketseat.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.vprocketseat.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

// ao inves de usar implements Filter, usar o acima, para na precisar converter 
// os request e reponse para http


@Autowired
private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                var servletPath = request.getServletPath();
                if(servletPath.startsWith("/tasks/")){

                    // Pegar a autenticacao (Usuário e Senha)

                    var authorization = request.getHeader("Authorization");
                    //System.out.println("Authorization");
                    //System.out.println(authorization);

                    var authEncoded = authorization.substring("Basic".length()).trim();
                    //System.out.println("Authorization");
                    //System.out.println(user_password);

                    byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
                    //System.out.println("Authorization");
                    //System.out.println(authDecoded);

                    var authString = new String(authDecoded);
                    //System.out.println("Authorization String");
                    //System.out.println(authString);

                    // ["usuario", "senha"]
                    String[] credentials = authString.split(":");
                    String username = credentials[0];
                    String password = credentials[1];
                    //System.out.println("usuario");
                    //System.out.println(username);
                    //System.out.println("senha");
                    //System.out.println(password);


                    //Validar usuário

                    var user = this.userRepository.findByUsername(username);

                    if(user == null){
                        response.sendError(401);
                    } else {

                        //validar senha
                        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(),user.getPassword());
                        
                        if( passwordVerify.verified){
                            request.setAttribute("idUser", user.getId());
                            filterChain.doFilter(request, response);    
                        }else{ 
                            response.sendError(401);
                        }
                        //segue viagem
                    
                    }
              
                } else {
                    filterChain.doFilter(request, response);   
                }
    }
   
    
}
