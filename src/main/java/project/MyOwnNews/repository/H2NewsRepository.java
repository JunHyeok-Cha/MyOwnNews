package project.MyOwnNews.repository;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import project.MyOwnNews.entity.News;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class H2NewsRepository implements NewsRepository{
    @PersistenceContext
    private EntityManager em;

    public H2NewsRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(News news) {
        em.persist(news);
    }

    @Override
    public List<News> findAll() {
        return em.createQuery("SELECT n FROM News n", News.class)
                .getResultList();
    }

    @Override
    public Optional<News> findById(Long id) {
        return Optional.ofNullable(em.find(News.class, id));
    }

    @Override
    public void deleteById(Long id) {
        News news = em.find(News.class, id);
        if(news != null){
            em.remove(news);
        }
    }
}
