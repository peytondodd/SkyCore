package me.simonm34.skyblock.events;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    public PlayerMove() {
        Core.getCore().getServer().getPluginManager().registerEvents(this, Core.getCore());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getTo().getBlockX() == e.getFrom().getBlockX() && e.getTo().getBlockZ() == e.getFrom().getBlockZ())
            return;
        if (!e.getTo().getWorld().getName().equals(Core.getCore().getSkyblock().getIslandManager().getWorld().getName()))
            return;

        User user = Core.getCore().getUserManager().getUser(e.getPlayer().getUniqueId());
        Island island = Core.getCore().getSkyblock().getIslandManager().getIslandAtLocation(e.getTo());
        if (island != null) {
            if (island.getAllMembers().contains(user.getUUID())) return;
            if (island.isLocked()) {
                e.setCancelled(true);
                user.sendMsg("&cThis island is locked!");
                return;
            }
            if (island.getBanned().contains(user.getUUID())) {
                user.sendMsg("&cYou are banned from this island!");
            }
        }
    }
}
