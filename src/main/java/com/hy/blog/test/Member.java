package com.hy.blog.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;

}
