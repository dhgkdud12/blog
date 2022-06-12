package com.hy.blog.Repository;

import com.hy.blog.model.Board;
import com.hy.blog.model.Reply;
import com.hy.blog.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query(value="SELECT * FROM board WHERE board.userId = ?1", nativeQuery = true)
    List<Board> myBoards(int userId);

    @Query(value="SELECT * FROM board WHERE board.title LIKE CONCAT('%',?1,'%')", nativeQuery = true)
    List<Board> searchBoardByTitle(String title);

    @Query(value="SELECT * FROM board WHERE board.content LIKE CONCAT('%',?1,'%')", nativeQuery = true)
    List<Board> searchBoardByContent(String content);

    @Query(value="select * from board inner join (select * from user where username LIKE CONCAT('%',?1,'%')) a on board.userId = a.id", nativeQuery = true)
    List<Board> searchBoardByUser(String user);
}
