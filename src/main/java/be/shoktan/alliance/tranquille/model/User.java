package be.shoktan.alliance.tranquille.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "RegisteredUser")
public class User {
    private @Id
    @GeneratedValue
    Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    private String mainAccount;

    @OneToMany(mappedBy = "author")
    private List<GuildLogComment> comments;

    @Enumerated(EnumType.STRING)
    private UserRank rank;


    private String password;

    private Boolean enabled = false;

    private Boolean admin = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainAccount() {
        return mainAccount;
    }

    public void setMainAccount(String mainAccount) {
        this.mainAccount = mainAccount;
    }

    public List<GuildLogComment> getComments() {
        return comments;
    }

    public void setComments(List<GuildLogComment> comments) {
        this.comments = comments;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public UserRank getRank() {
        return rank;
    }

    public void setRank(UserRank rank) {
        this.rank = rank;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
