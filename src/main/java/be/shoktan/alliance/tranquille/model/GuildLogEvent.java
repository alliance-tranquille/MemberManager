package be.shoktan.alliance.tranquille.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class GuildLogEvent {
    private int id;

    //2017-12-31T15:03:59.000Z
    @JsonFormat(pattern="yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    private LocalDateTime time;
    private String user;
    private String type;
    private String invitedBy;
    private String kickedBy;
    private String changedBy;
    private String declinedBy;
    private String oldRank;
    private String newRank;
    private String itemId;
    private String count;
    private String operation;
    private int coins;
    private String motd;
    private String action;
    private String upgradeId;
    private String recipeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getActor(){
        if(this.declinedBy != null){
            return declinedBy;
        }
        if(this.changedBy != null){
            return changedBy;
        }
        if(this.invitedBy != null){
            return this.invitedBy;
        }
        if(this.kickedBy != null){
            return this.kickedBy;
        }
        return null;
    }
}
