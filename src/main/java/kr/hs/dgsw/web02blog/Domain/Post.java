package kr.hs.dgsw.web02blog.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String content;
    private String imgPath = null;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-dd-MM HH:mm:ss")
    private LocalDateTime created;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime modified;

    public Post(Long userId, String content, String imgPath){
        this.userId = userId;
        this.content = content;
        this.imgPath = imgPath;
    }

    public Post(Post post){
        this.id = post.id;
        this.userId = post.userId;
        this.content = post.content;
        this.imgPath = post.imgPath;
        this.created = post.created;
        this.modified = post.modified;
    }
}
