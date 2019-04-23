package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web02blog.Protocol.ResponseType;
import kr.hs.dgsw.web02blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/list")
    public ResponseFormat list(){
        return new ResponseFormat(ResponseType.USER_LIST ,userService.list());
    }

    @GetMapping("/user/view/{id}")
    public ResponseFormat userView(@PathVariable Long id){
        return new ResponseFormat(ResponseType.USER_GET, userService.view(id));
    }

    @PostMapping("/user/add")
    public ResponseFormat userAdd(@RequestBody User user){
        return new ResponseFormat(ResponseType.USER_ADD, userService.add(user));
    }

    @PutMapping("/user/update/{id}")
    public ResponseFormat userUpdate(@PathVariable Long id, @RequestBody User user){
        return new ResponseFormat(ResponseType.USER_UPDATE, userService.update(id, user));
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseFormat userDelete(@PathVariable Long id){
        return new ResponseFormat(ResponseType.USER_DELETE, userService.delete(id));
    }

//    @GetMapping("/context")
//    public String test(){
//        return "Hello";
//    }
}
