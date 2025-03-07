package project.MyOwnNews.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private String title;   //기사 제목
    private String originallink;    //원문 기사 url
    private String link;    //네이버 기사 url
    private String description; //내용 요약
    private String pubDate; //작성 시간

    @Getter @Setter
    public static class NewsResponse{
        private String lastBuildDate;   //검색 결과 생성시간
        private int total;  //검색 결과 갯수
        private int start;  //검색 시작 위치
        private int display;    //한 번에 표시할 검색 결과 갯수
        @JsonProperty("items")
        private List<NewsDto> newsList;
    }

}
