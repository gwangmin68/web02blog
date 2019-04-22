package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/list")
    public List<User> list(){
        return userService.list();
    }

    @GetMapping("/user/view/{id}")
    public User userView(@PathVariable Long id){
        return userService.view(id);
    }

    @PostMapping("/user/add")
    public User userAdd(@RequestBody User user){
        return userService.add(user);
    }

    @PutMapping("/user/update/{id}")
    public User userUpdate(@PathVariable Long id, @RequestBody User user){
        return userService.update(id, user);
    }

    @DeleteMapping("/user/delete/{id}")
    public boolean userDelete(@PathVariable Long id){
        return userService.delete(id);
    }
}
