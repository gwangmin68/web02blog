package kr.hs.dgsw.web02blog.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storedPath;

    private Long postId;

    public Attachment(String storedPath, Long postId){
        this.storedPath = storedPath;
        this.postId = postId;
    }
}
