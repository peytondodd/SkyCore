package me.simonm34.skyblock.events;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {
    public EntityDamageByEntity() {
        Core.getCore().getServer().getPluginManager().registerEvents(this, Core.getCore());
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            User user = Core.getCore().getUserManager().getUser(e.getDamager().getUniqueId());
            Location loc = e.getEntity().getLocation();

            if (user.getLoc().getWorld().getName().equalsIgnoreCase("skyblock")) {
                Island island = Core.getCore().getSkyblock().getIslandManager().getIslandAtLocation(loc);
                if (island != null) {
                    if (island.getAllMembersCoop().contains(user.getUUID())) {
                        return;
                    }
                }
                user.sendMsg("&cYou can only hurt animals on your island!");
            }
        }
    }
}
