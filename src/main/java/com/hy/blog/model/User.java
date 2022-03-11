package com.hy.blog.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴!!
//ORM -> Java(다른 언어) Object -> 테이블로 매핑해주는 기술
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다.
//@DynamicInsert //insert시에 null인 필드를 제외시켜준다.
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

    //@ColumnDefault("'user'")
    //DB는 RoleType이라는게 없다.
    @Enumerated(EnumType.STRING) //type이 String이라는 걸 알려줌
    private RoleType role; //Enum을 쓰는게 좋다. //ADMIN(관리자), USER, manager (managerrrr)
    //타입 강제 ADMIN, ROLE

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;


}
