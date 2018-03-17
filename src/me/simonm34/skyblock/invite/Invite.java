package me.simonm34.skyblock.invite;

import java.util.UUID;

public class Invite {
    private UUID uuid;
    private UUID island;

    public Invite(UUID uuid, UUID island) {
        this.uuid = uuid;
        this.island = island;
    }

    public UUID getUUID() {
        return uuid;
    }
    public UUID getIslandUUID() {
        return island;
    }
}
