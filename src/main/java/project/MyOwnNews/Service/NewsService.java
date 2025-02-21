package project.MyOwnNews.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.MyOwnNews.dto.NewsDto;
import project.MyOwnNews.entity.News;
import project.MyOwnNews.repository.NewsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final RestTemplate restTemplate;
    private final NewsRepository newsRepository;

    @Value("${newsapi.url}")
    private String newsApiUrl;

    @Value("${newsapi.key}")
    private String apiKey;

    public NewsService(RestTemplate restTemplate, NewsRepository newsRepository) {
        this.restTemplate = restTemplate;
        this.newsRepository = newsRepository;
    }

    public List<News> fetchNews(){
        String apiUrl = newsApiUrl + apiKey;

        NewsDto.NewsResponse newsResponse = restTemplate.getForObject(apiUrl, NewsDto.NewsResponse.class);
        System.out.println(newsResponse.getNewsList().stream().toString());
        if(newsResponse == null){
            throw new NullPointerException("새로운 뉴스가 없습니다.");
        }
        List<News> newsList = newsResponse.getNewsList().stream()
                .map(newsDto -> new News(newsDto.getTitle(), newsDto.getDescription(), newsDto.getUrl(), newsDto.getImageUrl(), newsDto.getSource()))
                .collect(Collectors.toList());

        newsList.forEach(newsRepository::save);
        return newsList;
    }
    public List<News> showAllNews(){
        return newsRepository.findAll();
    }
}
