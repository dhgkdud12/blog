package com.hy.blog.Controller.api;

import com.hy.blog.config.auth.PrincipalDetail;
import com.hy.blog.dto.ResponseDto;
import com.hy.blog.model.Board;
import com.hy.blog.model.Reply;
import com.hy.blog.model.User;
import com.hy.blog.service.BoardService;
import com.hy.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.글수정하기(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.댓글쓰기(principal.getUser(), boardId, reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
    }
}
