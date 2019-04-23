package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.PostUserNameProtocol;

import java.util.List;

public interface PostService {
    List<PostUserNameProtocol> list();
    PostUserNameProtocol view(Long id);
    PostUserNameProtocol add(Post post);
    Post update(Long id, Post post);
    boolean delete(Long id);
    Post get(Long userId);
}
