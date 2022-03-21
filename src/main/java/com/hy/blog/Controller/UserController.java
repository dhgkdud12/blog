package com.hy.blog.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.blog.config.auth.PrincipalDetail;
import com.hy.blog.model.KakaoProfile;
import com.hy.blog.model.OAuthToken;
import com.hy.blog.model.User;
import com.hy.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥 주소가 /이면 saveForm.jsp 허용
//static 이하에 있는 /js/**, /css/**, /image/**

@Controller
@RequiredArgsConstructor
public class UserController {
    @Value("${hy.key}")
    private String hyKey;


    final private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/kakao/callback") //@ResponseBody
    public String kakaoCallback(String code) { //데이터를 리턴해주는 컨트롤러 함수

        //        POST 방식으로 key=value 데이터를 요청 (카카오쪽으로)
//        HttpURLConnection url
//        Retrofit2
//        OkHttp
//        RestTemplate

//        RestTemplate rt = new RestTemplate();
//
//        //HttpHeader 오브젝트 생성
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); //key=value 형태라고 알려주는 것임
//
//        //HttpBody 오브젝트 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id ", "a56dbcf344f3ad8cfb7e49be52f19680"); //변수화
//        params.add("redirect_uri ", "http://localhost:9090/auth/kakao/callback");
//        params.add("code", code);
//
//        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
//        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
//                new HttpEntity<>(params, headers);
//        System.out.println(params);
//        System.out.println(headers);
//        System.out.println(kakaoTokenRequest);
//
//        //Http 요청하기 - Post 방식으로 - 그리고 response 변수에 응답 받음.
//        ResponseEntity<String> response = rt.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                kakaoTokenRequest,
//                String.class
//        );

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code&client_id=a56dbcf344f3ad8cfb7e49be52f19680&redirect_uri=http://localhost:9090/auth/kakao/callback&code=");
        sb.append(code);
        String body = sb.toString();

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        //Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response, OAuthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("카카오 액세스 토큰: "+oauthToken.getAccess_token());

        RestTemplate rest2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<String> requestEntity2 = new HttpEntity<String>(headers2);
        ResponseEntity<String> responseEntity2 = rest2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, requestEntity2, String.class);

        String response2 = responseEntity2.getBody();
        System.out.println(response2);

        //
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2, KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //User 오브젝트: username, password, email
        System.out.println("카카오 아이디(번호) : "+kakaoProfile.getId());
        System.out.println("카카오 이메일 : "+kakaoProfile.getKakao_account().getEmail());

        System.out.println("블로그서버 유저네임 : "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
        System.out.println("블로그서버 이메일: "+kakaoProfile.getKakao_account().getEmail());
        //UUID란->중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
        System.out.println("블로그서버 패스워드: "+hyKey);

        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
                .password(hyKey)
                .email(kakaoProfile.getKakao_account().getEmail())
                .oauth("kakao")
                .build();

        //가입자 혹은 비가입자 체크해서 처리
        User originUser = userService.회원찾기(kakaoUser.getUsername());

        if (originUser.getUsername() == null) {
            System.out.println("기존 회원이 아니기에 자동  회원가입을 진행합니다.");
            userService.회원가입(kakaoUser);
        }

        System.out.println("자동 로그인 진행합니다.");
        //로그인 처리
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), hyKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
        return "user/updateForm";
    }

}
