package me.simonm34.skyblock.events;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChange implements Listener {
    public SignChange() {
        Core.getCore().getServer().getPluginManager().registerEvents(this, Core.getCore());
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        User user = Core.getCore().getUserManager().getUser(e.getPlayer().getUniqueId());
        Island island = Core.getCore().getSkyblock().getIslandManager().getIslandAtLocation(user.getLoc());

        if (island == null) return;
        if (island.getOwner() != user.getUUID()) return;

        String line1 = e.getLine(0);
        if (line1.equalsIgnoreCase("[welcome]")) {
            island.setWelcomePoint(e.getBlock().getLocation());
            e.setLine(0, ChatColor.GREEN + "[Welcome]");
        }
    }
}
