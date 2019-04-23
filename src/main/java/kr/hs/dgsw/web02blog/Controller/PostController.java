package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.PostUserNameProtocol;
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
        try{
            return new ResponseFormat(ResponseType.POST_LIST, postService.list());
        }catch (Exception e){
            return new ResponseFormat(ResponseType.POST_LIST_ERR);
        }
    }

    @PostMapping("/post/add")
    public ResponseFormat add(@RequestBody Post post){
        try {
            PostUserNameProtocol data = this.postService.add(post);
            return new ResponseFormat(ResponseType.POST_ADD, data, data.getId());
        }catch (Exception e){
            return new ResponseFormat(ResponseType.POST_ADD_ERR);
        }
    }

    @GetMapping("/post/view/{id}")
    public ResponseFormat view(@PathVariable Long id){
        try {
            PostUserNameProtocol data = postService.view(id);
            return new ResponseFormat(ResponseType.POST_GET, data, data.getTitle());
        }catch (Exception e){
            return new ResponseFormat(ResponseType.POST_GET_ERR);
        }
    }

    @PutMapping("/post/update/{id}")
    public ResponseFormat update(@PathVariable Long id, @RequestBody Post post){
        try {
            Post data = postService.update(id, post);
            return new ResponseFormat(ResponseType.POST_UPDATE, data, data.getId());
        }catch (Exception e){
            return new ResponseFormat(ResponseType.POST_UPDATE_ERR);
        }
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseFormat delete(@PathVariable Long id){
        try {
            return new ResponseFormat(ResponseType.POST_DELETE, postService.delete(id));
        }catch (Exception e){
            return new ResponseFormat(ResponseType.POST_DELETE_ERR);
        }
    }
}
