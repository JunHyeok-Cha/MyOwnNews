package project.MyOwnNews.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class NewsDto {
    private String title;
    private String description;
    private String url;
    private String urlToImage;

    private String author;
    private String content;

    @JsonProperty("publishedAt")
    private String publishedDate;

    @JsonProperty("source") // ✅ JSON에서 "source"는 객체이므로 새로운 클래스로 매핑
    private SourceDto source;

    @Getter @Setter
    public static class SourceDto{
        private String id;
        private String name;
    }

    @Getter
    @Setter
    public static class NewsResponse{
        private String status;
        private int totalResults;
        @JsonProperty("articles") //Json의 필드와 매칭
        private List<NewsDto> newsList;
    }


}
