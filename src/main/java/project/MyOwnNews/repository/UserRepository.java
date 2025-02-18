package project.MyOwnNews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.MyOwnNews.entity.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository {
    User save(User user);
    Optional<User> findByUsername(String username); //JpaRepository 상속 시 네이밍 규칙으로 자동으로 구현해줌
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
}
