package be.shoktan.alliance.tranquille.model;

public enum GuildLogEventType {
    invite_declined("a refusé(e) l'invitation"),
    invited("a été invité(e)"),
    influence("influence"),
    kick("a été expulsé(e)"),
    stash("a interragi avec le stock"),
    rank_change("a changé(e) de rang"),
    joined("a rejoint"),
    upgrade("a lancé une upgrade"),
    treasury("a stocké dans le trésor"),
    motd("a changé le message du jour"),
    left("a quitté");


    private String prettyPrint;

    GuildLogEventType(String prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    public String getPrettyPrint() {
        return prettyPrint;
    }
}
