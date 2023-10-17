package br.com.vprocketseat.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
/**
 * com o lombok.Data não é necessário utilizar os getters e setters
 * se quiser só os getters é só utilizar o @Getter
 * se quiser só os setter é só utilziar o @Setters
 * 
 */
@Data
@Entity(name = "tb_users") //entidade ou tabela. Objeto que vai representar uma tabela (muitas vezes)

public class UserModel {

    @Id // informa ao banco de dados que o id é o abaixo
    @GeneratedValue(generator = "UUID")//geração do ID de forma automática pelo Spring JPA
    private UUID id;
    
    //Caso queria mudar o nome da coluna no banco de dados, basta adicionar @Column(name = "nnn")
    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // getters and setters
/**
 * com o Lombok, não precisa criar os getters e setters
 * 
 
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
*/


}
