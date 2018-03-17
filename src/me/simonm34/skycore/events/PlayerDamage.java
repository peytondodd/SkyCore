package me.simonm34.skycore.events;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamage implements Listener {
    public PlayerDamage() {
        Bukkit.getServer().getPluginManager().registerEvent(EntityDamageEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            EntityDamageEvent e = (EntityDamageEvent) event;

            if (e.getEntity() instanceof Player) {
                User user = Core.getCore().getUserManager().getUser(e.getEntity().getUniqueId());
                EntityDamageEvent.DamageCause damage = e.getCause();
                if (damage == EntityDamageEvent.DamageCause.VOID) {
                    if (user.getLoc().getWorld().getName().equalsIgnoreCase("world")) {
                        Warp warp = Core.getCore().getWarpManager().getWarp("spawn");
                        if (warp != null) {
                            Location location = warp.getLocation().clone().add(0.0, 1.0, 0.0);
                            user.teleport(location);
                            e.setCancelled(true);
                            return;
                        }
                    }
                }
                if (user.isGodMode())
                    e.setCancelled(true);
                if (user.getSpectating() != null)
                    e.setCancelled(true);
            }
        }, Core.getCore());
    }
}
