package com.hy.blog.service;

import com.hy.blog.Repository.BoardRepository;
import com.hy.blog.Repository.UserRepository;
import com.hy.blog.model.Board;
import com.hy.blog.model.RoleType;
import com.hy.blog.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user) { //title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public List<Board> 글목록() {
        return boardRepository.findAll();
    }
}
