package com.hy.blog.Repository;

import com.hy.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//User 테이블을 관리하는 레파지토리, User 테이블의 Primary key는 Integer임
//DAO
//자동으로 bean등록이 된다.
//@Repository //생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {
}
