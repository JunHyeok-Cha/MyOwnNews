package project.MyOwnNews.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import project.MyOwnNews.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class H2UserRepository implements UserRepository {
    private final EntityManager em;

    public H2UserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        if(user.getId() == null){
            em.persist(user);
            return user;
        }
        else {
            return em.merge(user);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u",User.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        User user = em.find(User.class, id);
        if(user != null){
            em.remove(user);
        }
    }
}
