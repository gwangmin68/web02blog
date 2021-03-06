package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Protocol.PostUserNameProtocol;
import kr.hs.dgsw.web02blog.Repository.PostRepository;
import kr.hs.dgsw.web02blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void init(){
        this.userRepository.save(new User(
                "1","1","gwangmin","gwangmin@naver","94224076",null
        ));
        this.postRepository.save(new Post(
                1L,"test1","test",null
        ));
    }

    @Override
    public List<PostUserNameProtocol> list() {

        List<Post> postList = this.postRepository.findAll();
        List<PostUserNameProtocol> pupList = new ArrayList<>();
        postList.forEach(post -> {
            Optional<User> found = this.userRepository.findById(post.getUserId());
            String username = found.isPresent() ? found.get().getName() : null;
            pupList.add(new PostUserNameProtocol(username, post));
        });
        return pupList;
    }

    @Override
    public PostUserNameProtocol view(Long id) {
        Post post = this.postRepository.findById(id).get();
        return new PostUserNameProtocol(this.userRepository.findById(post.getUserId()).get().getName(), post);
    }

    @Override
    public PostUserNameProtocol add(Post post) {
        Optional<User> found = this.userRepository.findById(post.getUserId());
        if(!found.isPresent()) return null;
        return new PostUserNameProtocol(this.userRepository.findById(post.getUserId())
                .map(name -> name.getName())
                .orElse(null), this.postRepository.save(post));
//        return new PostUserNameProtocol(this.userRepository.findById(post.getUserId()).get().getName(), this.postRepository.save(post));
    }

    @Override
    public Post get(Long userId) {
        return this.postRepository.findTopByUserIdOrderByIdDesc(userId).orElse(null);
    }

    @Override
    public Post update(Long id, Post post) {
        return this.postRepository.findById(id)
                .map(found -> {
                    found.setTitle(Optional.ofNullable(post.getTitle()).orElse(found.getTitle()));
                    found.setContent(Optional.ofNullable(post.getContent()).orElse(found.getContent()));
                    found.setPictures(Optional.ofNullable(post.getPictures()).orElse(found.getPictures()));
                    return this.postRepository.save(found);
                })
                .orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        try {
            this.postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
