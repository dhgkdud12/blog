package com.hy.blog.Controller;

import com.hy.blog.config.auth.PrincipalDetail;
import com.hy.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size=3, sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards",  boardService.글목록(pageable));
        return "index"; //viewResolver 작동
    }

    //게시글폼
    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model) { //model에 담음
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
    }

    //게시물수정폼
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/updateForm";
    }

    //글저장
    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/myWriteForm")
    public String myWriteForm(@AuthenticationPrincipal PrincipalDetail principal, Model model) {
        model.addAttribute("boards", boardService.내글가져오기(principal.getUser().getId()));
        return "board/myWriteForm";
    }

    @GetMapping("/board/myReplyForm")
    public String myReplyForm(@AuthenticationPrincipal PrincipalDetail principal, Model model) {
        model.addAttribute("replys", boardService.내댓글가져오기(principal.getUser().getId()));
        return "board/myReplyForm";
    }
}
