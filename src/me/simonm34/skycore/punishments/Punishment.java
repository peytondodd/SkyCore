package me.simonm34.skycore.punishments;

public class Punishment {
    private PunishType punishType;
    private String reason;
    private String by;
    private boolean active;
    private Long expire;

    public Punishment(PunishType punishType, String reason, String by, boolean active, Long expire) {
        this.punishType = punishType;
        this.reason = reason;
        this.by = by;
        this.active = active;
        this.expire = expire;
    }

    public PunishType getPunishType() {
        return punishType;
    }
    public String getReason() {
        return reason;
    }
    public String getBy() {
        return by;
    }
    public boolean isActive() {
        return active;
    }
    public Long getExpire() {
        return expire;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public void setExpire(long expire) {
        this.expire = expire;
    }
}
