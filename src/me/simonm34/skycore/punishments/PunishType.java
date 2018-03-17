package me.simonm34.skycore.punishments;

public enum PunishType {
    WARN("Warned", true),
    TEMP_MUTE("Temporary Muted", true),
    MUTE("Muted", true),
    TEMP_BAN("Temporary Banned", false),
    BAN("Banned", false),
    IP_BAN("IP Banned", false);

    private String name;
    private boolean canConnect;

    PunishType(String name, boolean canConnect) {
        this.name = name;
        this.canConnect = canConnect;
    }

    public String getName() {
        return name;
    }
    public boolean canConnect() {
        return canConnect;
    }
}
