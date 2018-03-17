package me.simonm34.skycore.api.events;

import me.simonm34.skyblock.island.Island;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandMemberChangeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Island island;

    public IslandMemberChangeEvent(Island island) {
        this.island = island;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
    public Island getIsland() {
        return island;
    }
}
