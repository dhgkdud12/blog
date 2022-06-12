package com.hy.blog.Controller;

import com.hy.blog.config.auth.PrincipalDetail;
import com.hy.blog.model.Board;
import com.hy.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //내가 쓴 글목록
    @GetMapping("/board/myWriteForm")
    public String myWriteForm(@AuthenticationPrincipal PrincipalDetail principal, Model model) {
        model.addAttribute("boards", boardService.내글가져오기(principal.getUser().getId()));
        return "board/myWriteForm";
    }

    // 내가 댓글 작성한 글 목록
    @GetMapping("/board/myReplyForm")
    public String myReplyForm(@AuthenticationPrincipal PrincipalDetail principal, Model model) {
        model.addAttribute("replys", boardService.내댓글가져오기(principal.getUser().getId()));
        return "board/myReplyForm";
    }

    // 게시물 제목, 내용, 작성자 검색
    @GetMapping({"/board/search"})
    public String search(String type, String keyword, Model model) {
        System.out.println("=============================================");
//        System.out.println(board);
        if (type.equals("제목")) {
            model.addAttribute("boards", boardService.글제목검색(keyword));
        } else if (type.equals("내용")) {
            model.addAttribute("boards", boardService.글내용검색(keyword));
        } else if (type.equals("글쓴이")) {
            model.addAttribute("boards", boardService.글쓴이검색(keyword));
        }
        return "board/searchForm";
    }
}
