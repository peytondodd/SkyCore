package me.simonm34.skycore.api.events;

import me.simonm34.skycore.user.User;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerBalanceChangeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private User user;

    public PlayerBalanceChangeEvent(User user) {
        this.user = user;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
    public User getUser() {
        return user;
    }
}
