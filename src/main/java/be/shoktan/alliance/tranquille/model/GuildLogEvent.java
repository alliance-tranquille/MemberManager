package be.shoktan.alliance.tranquille.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class GuildLogEvent {
    private @Id
    @GeneratedValue
    Long id;


    @JsonProperty("id")
    private int inGameId;

    //2017-12-31T15:03:59.000Z
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private LocalDateTime time;
    private String user;
    private GuildLogEventType type;

    @JsonProperty("invited_by")
    private String invitedBy;

    @JsonProperty("kicked_by")
    private String kickedBy;

    @JsonProperty("changed_by")
    private String changedBy;

    @JsonProperty("declined_by")
    private String declinedBy;

    @JsonProperty("old_rank")
    private String oldRank;

    @JsonProperty("new_rank")
    private String newRank;

    @JsonProperty("item_id")
    private String itemId;

    private String count;
    private String operation;
    private int coins;
    private String motd;
    private String action;

    @JsonProperty("upgrade_id")
    private String upgradeId;

    @JsonProperty("recipe_id")
    private String recipeId;



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

    public String getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(String invitedBy) {
        this.invitedBy = invitedBy;
    }

    public String getKickedBy() {
        return kickedBy;
    }

    public void setKickedBy(String kickedBy) {
        this.kickedBy = kickedBy;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
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

    public String getDeclinedBy() {
        return declinedBy;
    }

    public void setDeclinedBy(String declinedBy) {
        this.declinedBy = declinedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActor() {
        if (StringUtils.isNotBlank(this.declinedBy)) {
            return declinedBy;
        }
        if (StringUtils.isNotBlank(this.changedBy)) {
            return changedBy;
        }
        if (StringUtils.isNotBlank(this.invitedBy)) {
            return this.invitedBy;
        }
        if (StringUtils.isNotBlank(this.kickedBy)) {
            return this.kickedBy;
        }
        return null;
    }

    public String getDetails(){
        String result;
        switch(type){
            case rank_change: result = String.format("%s -> %s", oldRank, newRank); break;
            default: result = "";
        }
        return result;
    }
}
