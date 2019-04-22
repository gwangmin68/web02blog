package kr.hs.dgsw.web02blog.Repository;

import kr.hs.dgsw.web02blog.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long userId);
}
