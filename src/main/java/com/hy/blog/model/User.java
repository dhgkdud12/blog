package com.hy.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM -> Java(다른 언어) Object -> 테이블로 매핑해주는 기술
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다.
public class User {

    @Id//Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; //오라클-시퀀스, MySQL- auto_increment

    @Column(nullable = false, length = 30) //null 여부, 최대 길이
    private String username; //아이디

    @Column(nullable = false, length = 100) //123456 => 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; //Enum을 쓰는게 좋다. //admin(관리자), user, manager (managerrrr)

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;


}
