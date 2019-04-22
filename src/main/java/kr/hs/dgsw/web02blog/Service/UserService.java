package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.User;

import java.util.List;

public interface UserService {
    List<User> list();
    User view(Long id);
    User add(User user);
    User update(Long id, User user);
    boolean delete(Long id);
}
