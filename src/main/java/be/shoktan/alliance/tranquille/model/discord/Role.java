package be.shoktan.alliance.tranquille.model.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {
    private Long id;

    private String name;

    /**
     * integer representation of hexadecimal color code
     */
    private int color;

    /**
     * if this role is pinned in the user listing
     */
    private boolean hoist;


    /**
     * position of this role
     */
    private int position;

    /**
     * permission bit set
     */
    private int permissions;


    /**
     * whether this role is managed by an integration
     */
    private boolean managed;

    /**
     * whether this role is mentionable
     */
    private boolean mentionable;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isHoist() {
        return hoist;
    }

    public void setHoist(boolean hoist) {
        this.hoist = hoist;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public boolean isManaged() {
        return managed;
    }

    public void setManaged(boolean managed) {
        this.managed = managed;
    }

    public boolean isMentionable() {
        return mentionable;
    }

    public void setMentionable(boolean mentionable) {
        this.mentionable = mentionable;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", hoist=" + hoist +
                ", position=" + position +
                ", permissions=" + permissions +
                ", managed=" + managed +
                ", mentionable=" + mentionable +
                '}';
    }

    public String getHexColor(){
        return Integer.toHexString(color);
    }
}
