package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class IslandSetSpawnCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        if (island.getOwner() != user.getUUID()) {
            user.sendMsg("&cOnly the island owner can set the spawn!");
            return;
        }
        island = Core.getCore().getSkyblock().getIslandManager().getIslandAtLocation(user.getLoc());
        if (island != null) {
            if (island.getOwner() == user.getUUID()) {
                Location loc = user.getLoc().subtract(0.0, 1.0, 0.0);
                Block block = loc.getBlock();
                if (!block.getType().isSolid()) {
                    user.sendMsg("&cYou cannot set your island spawn point to this block!");
                    return;
                }
                island.setSpawnPoint(loc.add(0.0, 1.0, 0.0));
                user.sendMsg("&7Your islands spawn point was set!");
                return;
            }
        }
        user.sendMsg("&cYou have to be on your island to set the spawn point!");
    }
}
