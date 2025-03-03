package project.MyOwnNews;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import project.MyOwnNews.Controller.NewsNaverController;
import project.MyOwnNews.Service.NewsNaverService;
import project.MyOwnNews.repository.H2NewsRepository;
import project.MyOwnNews.repository.H2UserRepository;
import project.MyOwnNews.repository.NewsRepository;
import project.MyOwnNews.repository.UserRepository;

@Configuration
public class UserConfig {
    @Autowired
    private EntityManager em;
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public UserRepository userRepository(){
        return new H2UserRepository(em);
    }

    @Bean
    public NewsRepository newsRepository(){
        return new H2NewsRepository(em);
    }
    @Bean
    public NewsNaverService newsNaverService(){
        return new NewsNaverService(restTemplate(), newsRepository());
    }
    @Bean
    public NewsNaverController newsNaverController(){
        return new NewsNaverController(newsNaverService());
    }


}

