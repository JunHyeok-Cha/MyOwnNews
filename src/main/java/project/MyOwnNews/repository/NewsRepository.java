package project.MyOwnNews.repository;

import org.springframework.stereotype.Repository;
import project.MyOwnNews.entity.News;

import java.util.List;
import java.util.Optional;
@Repository
public interface NewsRepository {
    void save(News news);
    List<News> findAll();
    Optional<News> findById(Long id);
    void deleteById(Long id);
}
