//package project.MyOwnNews.Controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import project.MyOwnNews.Service.NewsService;
//import project.MyOwnNews.entity.News;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/news")
//public class NewsRestController {
//    private final NewsService newsService;
//    public NewsRestController(NewsService newsService) {
//        this.newsService = newsService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<News>> getNews(){
//        try{
//            List<News> newsList = newsService.fetchNews();
//            return ResponseEntity.ok(newsList);
//        }
//        catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//
//}
