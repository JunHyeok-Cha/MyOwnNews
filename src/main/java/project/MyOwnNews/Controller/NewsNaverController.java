package project.MyOwnNews.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import project.MyOwnNews.Service.NewsNaverService;
import project.MyOwnNews.entity.News;

import java.util.List;

@Controller
public class NewsNaverController {
    @Autowired
    private HttpSession session;
    private final NewsNaverService newsNaverService;

    public NewsNaverController(NewsNaverService newsNaverService) {
        this.newsNaverService = newsNaverService;
    }

    @GetMapping("/api/news")
    @ResponseBody
    public ResponseEntity<List<News>> getNews(){
        try{
            List<News> newsList = newsNaverService.fetchNews("최신 인기 뉴스");
            return ResponseEntity.ok(newsList);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
