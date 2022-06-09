package com.hy.blog.Repository;

import com.hy.blog.model.Board;
import com.hy.blog.model.Reply;
import com.hy.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query(value="SELECT * FROM board WHERE board.userId = ?1", nativeQuery = true)
    List<Board> myBoards(int userId);
}
