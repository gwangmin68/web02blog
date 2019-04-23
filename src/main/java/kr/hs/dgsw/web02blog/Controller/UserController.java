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
        try{
            return new ResponseFormat(ResponseType.USER_LIST ,userService.list());
        }catch (Exception e){
            return new ResponseFormat(ResponseType.USER_LIST_ERR);
        }
    }

    @GetMapping("/user/view/{id}")
    public ResponseFormat userView(@PathVariable Long id){
        try{
            User data = userService.view(id);
            return new ResponseFormat(ResponseType.USER_GET, data, data.getName());
        }catch (Exception e){
            return new ResponseFormat(ResponseType.USER_GET_ERR);
        }
    }

    @PostMapping("/user/add")
    public ResponseFormat userAdd(@RequestBody User user){

        try{
            User data = this.userService.add(user);
            return new ResponseFormat(ResponseType.USER_ADD, data, data.getId());
        }catch (Exception e){
            return new ResponseFormat(ResponseType.USER_ADD_ERR);
        }
    }

    @PutMapping("/user/update/{id}")
    public ResponseFormat userUpdate(@PathVariable Long id, @RequestBody User user){
        try{
            User data = this.userService.update(id, user);
            return new ResponseFormat(ResponseType.USER_UPDATE, data, data.getId());
        }catch (Exception e){
            return new ResponseFormat(ResponseType.USER_UPDATE_ERR);
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseFormat userDelete(@PathVariable Long id){
        try {
            return new ResponseFormat(ResponseType.USER_DELETE, userService.delete(id));
        }catch (Exception e){

            return new ResponseFormat(ResponseType.USER_DELETE_ERR);
        }
    }

//    @GetMapping("/context")
//    public String test(){
//        return "Hello";
//    }
}
