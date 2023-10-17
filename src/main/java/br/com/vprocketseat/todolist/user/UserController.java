package br.com.vprocketseat.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Convenção - Classe começa com letra Maiúscula e cada nova palavra também
 */

/**
 * Modificadores
 * public - qualquer um pode acessar
 * private - tem uma restrição um pouco maior, somente alguns atributos 
 * protected - quando está dentro da mesma estrutura do pacote
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired //Spring gerencia todo o ciclo de vida 
    private IUserRepository userRepository;


    /**
     * String - texto
     * Integer (int) - números inteiros
     * Double (double) - Números 0.0000
     * Float (float) - Número de caracteres 0.0000
     * char - A B 
     * Date (data)
     * void
     */

    @PostMapping("/")
    public ResponseEntity create (@RequestBody UserModel userModel){
        //trocou UserModel por ResponseEntity
        //System.out.println(userModel.getName());
        
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null){
            //System.out.println("Usuário já existe!");
            // Mensagem de erro
            // Status code
            //return null;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var passwordHashed = BCrypt.withDefaults().
        hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashed);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }


    
}
