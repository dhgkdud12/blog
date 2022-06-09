package com.hy.blog.Repository;

import com.hy.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    //영속화할 필요 x
    @Modifying
    @Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1,?2,?3,now())", nativeQuery = true)
    int mSave(int userId, int boardId, String content); //업데이트된 행의 개수를 리턴해줌.

    @Query(value="SELECT * FROM reply WHERE reply.userId = ?1", nativeQuery = true)
    List<Reply> myReplys(int userId);


}
