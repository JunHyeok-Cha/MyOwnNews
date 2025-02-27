package project.MyOwnNews.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;           //뉴스 제목
    @Column(length = 3000)
    private String description;     //뉴스 내용 요약
    private String url;             //뉴스 원본 링크
    private String urlToImage;      //썸네일 이미지 링크
    private String publishedDate;   //작성 날짜
    private String author;          //글쓴이
    @Column(length = 3000)
    private String content;
    private String sourceId;        //뉴스 출처 ID
    private String sourceName;      //뉴스 출처 이름

    public News(String title, String description, String url, String urlToImage, String publishedDate, String author, String content, String sourceId, String sourceName) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedDate = publishedDate;
        this.author = author;
        this.content = content;
        this.sourceId = sourceId;
        this.sourceName = sourceName;
    }
}

