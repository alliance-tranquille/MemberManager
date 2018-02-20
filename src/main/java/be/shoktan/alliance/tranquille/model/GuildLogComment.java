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
    @JoinColumn(name = "event")
    private GuildLogEvent event;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
