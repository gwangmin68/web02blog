package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Repository.PostRepository;
import kr.hs.dgsw.web02blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User view(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User add(User user) {
        Optional<User> found = this.userRepository.findByAccount(user.getAccount());
        if(found.isPresent()) return null;
        return this.userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        return this.userRepository.findById(id)
                .map(found -> {
                    found.setName(Optional.ofNullable(user.getName()).orElse(found.getName()));
                    found.setPassword(Optional.ofNullable(user.getPassword()).orElse(found.getPassword()));
                    found.setPhone(Optional.ofNullable(user.getPhone()).orElse(found.getPhone()));
                    found.setEmail(Optional.ofNullable(user.getEmail()).orElse(found.getEmail()));
                    found.setProfilePath(Optional.ofNullable(user.getProfilePath()).orElse(found.getProfilePath()));
                    return this.userRepository.save(found);
                })
                .orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        try{
            this.userRepository.deleteById(id);
            List<Post> posts = this.postRepository.findAllByUserId(id);
            for (Post post : posts) {
                this.postRepository.delete(post);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
