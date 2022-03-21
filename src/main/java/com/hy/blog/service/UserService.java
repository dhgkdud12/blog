package com.hy.blog.service;

import com.hy.blog.Repository.UserRepository;
import com.hy.blog.model.RoleType;
import com.hy.blog.model.User;
import lombok.RequiredArgsConstructor;
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

    @Transactional(readOnly = true)
    public User 회원찾기(String username) {
        User user = userRepository.findByUsername(username).orElseGet(()->{
            return new User(); //회원 없으면 빈객체 반환 User("aa","aa")로 강제로 만들어도됨.
        });
        return user;
    }

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); //1234 원문
        String encPassword = encoder.encode(rawPassword); //해쉬
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void 회원수정(User user) {
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        // select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌.
        User persistance = userRepository.findById(user.getId()).orElseThrow(()-> {
            return new IllegalArgumentException("회원 찾기 실패");
        });

        //Validate 체크 => oauth에 값이 없으면 수정 가능
        if (persistance.getOauth()==null || persistance.getOauth().equals("")){
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword); //비밀번호 암호화
            persistance.setPassword(encPassword); //암호화된 비밀번호 저장
            persistance.setEmail(user.getEmail()); //이메일 저장
        }


        // 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 됩니다.
        // 영속화된 persistence 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.

    }
}
//    @Transactional(readOnly = true) //Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성)
//    public User 로그인(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
