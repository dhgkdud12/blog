package com.hy.blog.service;

import com.hy.blog.Repository.UserRepository;
import com.hy.blog.model.RoleType;
import com.hy.blog.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@RequiredArgsConstructor
@Service
public class UserService {

    //    @Autowired
    final private UserRepository userRepository;

    //    @Autowired
    final private BCryptPasswordEncoder encoder;


    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); //1234 원문
        String encPassword = encoder.encode(rawPassword); //해쉬
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }
}
//    @Transactional(readOnly = true) //Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성)
//    public User 로그인(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
