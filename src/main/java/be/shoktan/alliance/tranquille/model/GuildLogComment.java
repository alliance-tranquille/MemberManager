package be.shoktan.alliance.tranquille.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class GuildLogComment {
    private @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @NotNull
    private GuildLogEvent event;

    @Lob
    private String content;

    @Column(name = "author")
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuildLogEvent getEvent() {
        return event;
    }

    public void setEvent(GuildLogEvent event) {
        this.event = event;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
