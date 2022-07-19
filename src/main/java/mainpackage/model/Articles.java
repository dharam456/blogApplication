package mainpackage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Articles {
    @Id
    private long articleId;
    private long userId;
    @Column(nullable = false,length = 30)
    private String title;
    @Column(nullable = false,length = 200)
    private String body;
}
