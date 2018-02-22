package be.shoktan.alliance.tranquille.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(indexes = {@Index(columnList = "targetUser")})
public class GuildLogEvent {
    private @Id
    @GeneratedValue
    Long id;


    @JsonProperty("id")
    private int inGameId;

    //2017-12-31T15:03:59.000Z
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private LocalDateTime time;


    @Column(name = "targetUser")
    private String user;

    private GuildLogEventType type;

    private String actor;

    @JsonProperty("old_rank")
    private String oldRank;

    @JsonProperty("new_rank")
    private String newRank;

    @JsonProperty("item_id")
    private String itemId;

    private String count;

    private String operation;

    private int coins;

    @Column(columnDefinition="TEXT")
    private String motd;

    private String action;

    @JsonProperty("upgrade_id")
    private String upgradeId;

    @JsonProperty("recipe_id")
    private String recipeId;

    @OneToMany(mappedBy = "event")
    private List<GuildLogComment> comments;


    public int getInGameId() {
        return inGameId;
    }

    public void setInGameId(int inGameId) {
        this.inGameId = inGameId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public GuildLogEventType getType() {
        return type;
    }

    public void setType(GuildLogEventType type) {
        this.type = type;
    }

    @JsonGetter("invited_by")
    public String getInvitedBy() {
        return actor;
    }

    @JsonSetter( "invited_by")
    public void setInvitedBy(String invitedBy) {
        this.actor = invitedBy;
    }

    @JsonGetter("kicked_by")
    public String getKickedBy() {
        return actor;
    }

    @JsonSetter("kicked_by")
    public void setKickedBy(String kickedBy) {
        this.actor = kickedBy;
    }

    @JsonGetter("changed_by")
    public String getChangedBy() {
        return actor;
    }

    @JsonSetter("changed_by")
    public void setChangedBy(String changedBy) {
        this.actor = changedBy;
    }

    public String getOldRank() {
        return oldRank;
    }

    public void setOldRank(String oldRank) {
        this.oldRank = oldRank;
    }

    public String getNewRank() {
        return newRank;
    }

    public void setNewRank(String newRank) {
        this.newRank = newRank;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(String upgradeId) {
        this.upgradeId = upgradeId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    @JsonGetter("declined_by")
    public String getDeclinedBy() {
        return actor;
    }

    @JsonSetter("declined_by")
    public void setDeclinedBy(String declinedBy) {
        this.actor = declinedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor){
        this.actor = actor;
    }

    public String getDetails(){
        String result;
        switch(type){
            case rank_change: result = String.format("%s -> %s", oldRank, newRank); break;
            default: result = action;
        }
        return result;
    }

    public List<GuildLogComment> getComments() {
        return comments;
    }

    public void setComments(List<GuildLogComment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getUser(), getType().getPrettyPrint());
    }
}
