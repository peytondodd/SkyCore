package me.simonm34.skyblock.events;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {
    public BlockPlace() {
        Core.getCore().getServer().getPluginManager().registerEvents(this, Core.getCore());
    }

    @EventHandler
    public void BlockPlace(BlockPlaceEvent e) {
        User user = Core.getCore().getUserManager().getUser(e.getPlayer().getUniqueId());
        Block block = e.getBlock();

        if (user.getLoc().getWorld().getName().equalsIgnoreCase("skyblock")) {
            Island island = Core.getCore().getSkyblock().getIslandManager().getIslandAtLocation(block.getLocation());
            if (island != null) {
                if (island.getAllMembersCoop().contains(user.getUUID())) {
                    return;
                }
            }
            user.sendMsg("&cYou can only build on your island!");
            e.setCancelled(true);
        }
    }
}
