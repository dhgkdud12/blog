package com.hy.blog.test;

//사용자가 요청 -> 응답(HTML 파일)
//@Controller

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpController {

    //select
    @GetMapping("/http/get") //Param으로(주소)
    public String getTest(Member m) { // id=1&username=ssa&password=122&email=sar@nate.com
        return "get 요청 "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }

    //insert
    @PostMapping("/http/post") //Body로, 다양한 것들을 넣을 수 있음./ text/plain, application/json
    public String postTest(@RequestBody Member m) { //MessageConverter (스프링부트)
        return "post 요청 "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }

    //update
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청 "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }

    //delete
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
