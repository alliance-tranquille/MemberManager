package be.shoktan.alliance.tranquille.model.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String id;
    private String username;
    private String discriminator;
    private String avatar;
    private boolean bot;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", discriminator='" + discriminator + '\'' +
                ", avatar='" + avatar + '\'' +
                ", bot='" + bot + '\'' +
                '}';
    }

    public String getAccountName(){
        return String.format("%s.%s", username, discriminator);
    }

    public String getAvatarUrl(){
        if(StringUtils.isBlank(avatar)){
            return "";
        }else{
            return String.format("https://cdn.discordapp.com/avatars/%s/%s.png", id, avatar);
        }
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }
}
