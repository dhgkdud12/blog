package com.hy.blog.test;

import com.hy.blog.Repository.UserRepository;
import com.hy.blog.model.RoleType;
import com.hy.blog.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
//html 파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {

//    @Autowired //의존성 주입
    private final UserRepository userRepository;

    //save 함수는 id를 전달하지 않으면 insert를 해주고
    //save 함수를 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
    //save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 함.

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {

        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다. id: "+id;
    }

    //email, password 수정
    @Transactional //함수 종료시에 자동 commit이 됨.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json 데이터를 요청 => Java Object (MessageConverter의 Jackson라이브러리가  변환해서 받아줌
        System.out.println("id: "+id);
        System.out.println("password:  " + requestUser.getPassword());
        System.out.println("email:  "+requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
                return new IllegalArgumentException("수정에 실패하였습니다.");
    });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(user); // 대신 @Transactional

//        requestUser.setId(id);
//        requestUser.setUsername("ssar");
//        userRepository.save(requestUser);

        //더티 체킹
        return user;
    }

    //http://localhost:9090/blog/dummy/user/3
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

// 한 페이지당 2건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public Page<User> pageList(@PageableDefault(size=2, sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser =  userRepository.findAll(pageable);

//        if (pagingUser.isFirst()) { //isLast()
//
//        }
        List<User> users = pagingUser.getContent();
        return pagingUser;
    }


    //{id}주소로 파라미터를 전달받을 수 있음.
    //http://localhost:9090/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public  User detail(@PathVariable int id) {
        // user/4를 찾으면 내가 DB에서 못찾아오게 되면 user가 null이 될 것 아냐?
        //그럼 return null이 리턴이 되잖아. 그럼 프로그램에 문제가 있지 않겠니?
        //Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해

        //람다식
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자는 없습니다.");
//        });

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 사용자가 없습니다.");
            }
        }); //3이면 맞지만 5는 잘못됨

//                .orElseGet(new Supplier<User>() {
//            @Override //Supplier 인터페이스
//            public User get() { //추상메서드
//                return new User();
//            }
//        });
        //1.get
//        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//            @Override
//        public User get () {
//            return new User();
//        }
//    });

        //orElseGet

        // 요청:  웹브라우저
        // user 객체 = 자바 오브젝트
        // 변환(웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
        // 스프링 부트 = MessageConverter라는 애가 응답시에 자동 작동
        // 만약에 자바 오브젝트를 리턴하게되면 MessageConverter가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져줌.
        return user;
    }

    //http://localhost:8080/blog/dummy/join (요청)
    //http의 body에 username, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user) { //key=value (약속된 규칙), object로
        System.out.println("id: "+user.getId());
        System.out.println("username: "+user.getUsername());
        System.out.println("password: "+user.getPassword());
        System.out.println("email: "+user.getEmail());
        System.out.println("role: "+user.getRole());
        System.out.println("createDate: "+user.getCreateDate());

        user.setRole(RoleType.USER); //role에 USER
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
