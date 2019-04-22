package kr.hs.dgsw.web02blog.Protocol;

import kr.hs.dgsw.web02blog.Domain.Post;

public class PostUserNameProtocol extends Post{
    private String username;

    public PostUserNameProtocol(String username, Post post){
        super(post);
        this.username = username;
    }
}
