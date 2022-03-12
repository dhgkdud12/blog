package com.hy.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"", "/"}) //blog/ 랑 blog
    public String index() {
        // /WEB-INF/views/index.jsp
        return "index";
    }
}
