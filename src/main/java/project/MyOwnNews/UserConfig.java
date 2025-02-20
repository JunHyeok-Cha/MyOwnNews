package project.MyOwnNews;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.MyOwnNews.Controller.UserController;
import project.MyOwnNews.Service.UserPasswordEncoder;
import project.MyOwnNews.Service.UserService;
import project.MyOwnNews.repository.H2UserRepository;
import project.MyOwnNews.repository.UserRepository;

@Configuration
public class UserConfig {
    @Autowired
    private EntityManager em;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserRepository userRepository(){
        return new H2UserRepository(em);
    }
    @Bean
    public UserPasswordEncoder userPasswordEncoder(){
        return new UserPasswordEncoder(bCryptPasswordEncoder());
    }
    @Bean
    public UserService userService(){
        return new UserService(userRepository(), userPasswordEncoder());
    }
    @Bean
    public UserController userController(){
        return new UserController(userService());
    }
}
