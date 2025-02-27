package project.MyOwnNews.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
        System.out.println("★ API URL : " + apiUrl);
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        System.out.println("📌 API 응답 상태 코드: " + response.getStatusCode());
//        System.out.println("📌 API 응답 본문: " + response.getBody());

        // JSON을 DTO로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        NewsDto.NewsResponse newsResponse;
        try {
            newsResponse = objectMapper.readValue(response.getBody(), NewsDto.NewsResponse.class);
            System.out.println("JSON 데이터 변환 성공~~~~~~!!");
        } catch (Exception e) {
            throw new RuntimeException("JSON 데이터 변환 실패: " + e.getMessage());
        }

        if (newsResponse == null || newsResponse.getNewsList() == null) {
            throw new NullPointerException("새로운 뉴스가 없습니다.");
        }
        List<News> newsList = newsResponse.getNewsList().stream()
                .map(newsDto -> new News(
                        newsDto.getTitle(),newsDto.getDescription(),newsDto.getUrl(),newsDto.getUrlToImage(),
                        newsDto.getPublishedDate(),newsDto.getAuthor(),newsDto.getContent(),
                        newsDto.getSource().getId(),newsDto.getSource().getName()))
                .collect(Collectors.toList());

        newsList.forEach(newsRepository::save);
        return newsList;
    }
    public List<News> showAllNews(){
        return newsRepository.findAll();
    }
}
