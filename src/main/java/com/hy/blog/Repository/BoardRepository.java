package com.hy.blog.Repository;

import com.hy.blog.model.Board;
import com.hy.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
