package com.hy.blog.Repository;

import com.hy.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//User 테이블을 관리하는 레파지토리, User 테이블의 Primary key는 Integer임
//DAO
//자동으로 bean등록이 된다.
//@Repository //생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {

    //JPA Naming 쿼리
    //SELECT * FROM user WHERE username = ?1 AND password = ?2;
//    User findByUsernameAndPassword(String username, String Password);

//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);
}
