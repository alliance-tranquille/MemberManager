package be.shoktan.alliance.tranquille.model;

public enum GuildLogEventType {
    invite_declined("a refusé(e) l'invitation"),
    invited("invité(e)"),
    influence("influence"),
    kick("a été expulsé(e)"),
    stash("stock"),
    rank_change("a changé(e) de rang"),
    joined("a rejoint"),
    upgrade("upgrade"),
    treasury("a stocké dans le trésor"),
    motd("message du jour");

    private String prettyPrint;

    GuildLogEventType(String prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    public String getPrettyPrint() {
        return prettyPrint;
    }
}
