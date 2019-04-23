package kr.hs.dgsw.web02blog.Repository;

import kr.hs.dgsw.web02blog.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long userId);
    Optional<Post> findTopByUserIdOrderByIdDesc(Long userId);//Top5 : 5개 읽어오기
}
