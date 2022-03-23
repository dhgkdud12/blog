package com.hy.blog.model;

import com.hy.blog.dto.ReplySaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Reply { //답변
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne //여러개의 답변은 하나의 게시물에 존재
    @JoinColumn(name="boardId")
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER)
    //여러개의 답변 하나의 유저
    @JoinColumn(name="userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

//    public void update(User user, Board board, String content) {
//        setUser(user);
//        setBoard(board);
//        setContent(content);
//
//    }
}
