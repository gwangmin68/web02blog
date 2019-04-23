package kr.hs.dgsw.web02blog.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Attachment> pictures;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime modified;

    public Post(Long userId, String title, String content, List<Attachment> pictures){
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.pictures = pictures;
    }

    public Post(Post post){
        this.id = post.id;
        this.userId = post.userId;
        this.title = post.title;
        this.content = post.content;
        this.pictures = post.pictures;
        this.created = post.created;
        this.modified = post.modified;
    }
}
