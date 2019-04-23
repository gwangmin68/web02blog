package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web02blog.Protocol.ResponseType;
import kr.hs.dgsw.web02blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/post/list")
    public ResponseFormat list(){
        return new ResponseFormat(ResponseType.POST_LIST, postService.list());
    }

    @PostMapping("/post/add")
    public ResponseFormat add(@RequestBody Post post){
        return new ResponseFormat(ResponseType.POST_ADD, postService.add(post));
    }

    @GetMapping("/post/view/{id}")
    public ResponseFormat view(@PathVariable Long id){
        return new ResponseFormat(ResponseType.POST_GET, postService.view(id));
    }

    @PutMapping("/post/update/{id}")
    public ResponseFormat update(@PathVariable Long id, @RequestBody Post post){
        return new ResponseFormat(ResponseType.POST_UPDATE, postService.update(id, post));
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseFormat delete(@PathVariable Long id){
        return new ResponseFormat(ResponseType.POST_DELETE, postService.delete(id));
    }
}
