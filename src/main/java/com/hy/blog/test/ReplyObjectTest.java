package com.hy.blog.test;

import com.hy.blog.model.Reply;
import org.junit.Test;

public class ReplyObjectTest {

    @Test
    public void ToStringTest() {
        Reply reply = Reply.builder()
                .id(1)
                .user(null)
                .board(null)
                .content("안녕")
                .build();

        System.out.println(reply); //오브젝트 출력시에 toString()이 자동 호출됨.
    }
}
