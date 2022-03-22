package com.hy.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data //Getter, Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨.

    private int count; //조회수

    @ManyToOne //Many = Board, User = One, 한 명의 유저는 여러개의 게시물, One to One이면 하나의 유저는 하나의 게시글
    @JoinColumn(name="userId")
    private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board" ,fetch=FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다 (난 FK가 아니예요) DB에 칼럼을 만들지 마세요.
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc") // 내림차순
//    @JoinColumn(name="replyId") 필요없음
    private List<Reply> replys; //조인문을 통해 값을 얻기 위함.

    @CreationTimestamp
    private Timestamp createDate;

}
