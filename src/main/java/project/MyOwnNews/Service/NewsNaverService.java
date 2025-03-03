package project.MyOwnNews.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import project.MyOwnNews.dto.NewsDto;
import project.MyOwnNews.entity.News;
import project.MyOwnNews.repository.NewsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewsNaverService {
    private final RestTemplate restTemplate;
    private final NewsRepository newsRepository;

    public NewsNaverService(RestTemplate restTemplate, NewsRepository newsRepository) {
        this.restTemplate = restTemplate;
        this.newsRepository = newsRepository;
    }

    @Value("${clientId}")
    private String clientId;
    @Value("${clientSecret}")
    private String clientSecret;
    private String baseApiURL = "https://openapi.naver.com/v1/search/news?query=%s&display=30";


    public List<News> fetchNews(String text) {
        String apiURL = String.format(baseApiURL, text);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            //API요청(GET)
            ResponseEntity<String> response = restTemplate.exchange(apiURL, HttpMethod.GET, entity, String.class);
            System.out.println("📌 API 응답 상태 코드: " + response.getStatusCode());
//        System.out.println("📌 API 응답 본문: " + response.getBody());

            // JSON을 DTO로 변환
            ObjectMapper om = new ObjectMapper();
            NewsDto.NewsResponse newsResponse;
            try {
                newsResponse = om.readValue(response.getBody(), NewsDto.NewsResponse.class);
                System.out.println("JSON 데이터 변환 성공~~~~~~!!");
            } catch (Exception e) {
                throw new RuntimeException("JSON 데이터 변환 실패: " + e.getMessage());
            }

            if (newsResponse == null || newsResponse.getNewsList() == null) {
                throw new NullPointerException("새로운 뉴스가 없습니다.");
            }

            List<News> newsList = newsResponse.getNewsList().stream()
                    .map(newsDto -> new News(
                            newsDto.getTitle(), newsDto.getDescription(), newsDto.getUrl(), newsDto.getUrlToImage(),
                            newsDto.getPublishedDate(), newsDto.getAuthor(), newsDto.getContent(),
                            newsDto.getSource().getId(), newsDto.getSource().getName()))
                    .collect(Collectors.toList());

            newsList.forEach(newsRepository::save);
            return newsList;

        } catch (HttpClientErrorException e) {
            System.out.println("❌ API 요청 실패: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw new RuntimeException("API 요청 실패: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ 기타 오류 발생: " + e.getMessage());
            throw new RuntimeException("JSON 데이터 변환 실패: " + e.getMessage());
        }

    }
}




