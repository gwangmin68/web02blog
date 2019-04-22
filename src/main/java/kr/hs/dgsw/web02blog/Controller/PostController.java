package kr.hs.dgsw.web02blog.Controller;

import javafx.geometry.Pos;
import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.PostUserNameProtocol;
import kr.hs.dgsw.web02blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/post/list")
    public List<PostUserNameProtocol> list(){
        return postService.list();
    }

    @PostMapping("/post/add")
    public PostUserNameProtocol add(@RequestBody Post post){
        return postService.add(post);
    }

    @GetMapping("/post/view/{id}")
    public PostUserNameProtocol view(@PathVariable Long id){
        return postService.view(id);
    }

    @PutMapping("/post/update/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post){
        return postService.update(id, post);
    }

    @DeleteMapping("/post/delete/{id}")
    public boolean delete(@PathVariable Long id){
        return postService.delete(id);
    }
}
