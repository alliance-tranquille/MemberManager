package be.shoktan.alliance.tranquille.model;

public class Member {
    private String name;
    private String rank;
    private String joinDate;

    public Member(String name, String rank, String joinDate) {
        this.name = name;
        this.rank = rank;
        this.joinDate = joinDate;
    }

    public Member() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
