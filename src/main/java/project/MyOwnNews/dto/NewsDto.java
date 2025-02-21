package project.MyOwnNews.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class NewsDto {
    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private String source;

    @Getter
    @Setter
    public static class NewsResponse{
        private List<NewsDto> newsList;
    }
}
