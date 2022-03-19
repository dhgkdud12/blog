package com.hy.blog.Controller.api;

import com.hy.blog.config.auth.PrincipalDetail;
import com.hy.blog.config.auth.PrincipalDetailService;
import com.hy.blog.dto.ResponseDto;
import com.hy.blog.model.RoleType;
import com.hy.blog.model.User;
import com.hy.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserApiController {

//    @Autowired
//    private HttpSession session;

final private UserService userService;

final PrincipalDetailService principalDetailService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) { //username, password, email
        System.out.println("UserApiController: save 호출됨.");
        //실제로 DB에 insert를 하고 아래에서 retrun이 되면 됨.
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user,
                                       @AuthenticationPrincipal PrincipalDetail principal,
                                       HttpSession session) { //key=value, x-www-form-urlencoded, JSON 데이터 받으려면 @RequestBody 써야됨
        userService.회원수정(user);
        // 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
        // 하지만 세션값은 변경되지 않은 상태이기 때문에 직접 세션값을 변경해줘야함.

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    //스프링 시큐리티 이용해서 로그인!!!
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) { // HttpSession session) 함수 매개변수로도 가능
//        System.out.println("UserApiController : login 호출됨");
//        User principal = userService.로그인(user); //principal (접근주체)
//
//        if (principal != null) {
//            session.setAttribute("principal", principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
//    }


}
