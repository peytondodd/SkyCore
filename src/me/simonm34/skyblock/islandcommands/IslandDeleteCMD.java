package me.simonm34.skyblock.islandcommands;

import com.boydti.fawe.util.task.TaskBuilder;
import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.api.events.IslandLevelChangeEvent;
import me.simonm34.skycore.api.events.IslandMemberChangeEvent;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.UUID;

public class IslandDeleteCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        if (island.getOwner() != user.getUUID()) {
            user.sendMsg("&cOnly the island owner can delete the island!");
            return;
        }
        if (island.confirmRemove()) {
            for (UUID uuid : island.getAllMembers()) {
                User target = Core.getCore().getUserManager().getUser(uuid);
                if (target != null) {
                    Warp warp = Core.getCore().getWarpManager().getWarp("spawn");
                    if (warp != null) {
                        target.teleport(warp.getLocation());
                    }
                    target.sendMsg("&7Your island was deleted by &e" + user.getName() + "&7!");
                    target.getInv().clear();
                }
            }
            Bukkit.getServer().getPluginManager().callEvent(new IslandMemberChangeEvent(island));
            Bukkit.getServer().getPluginManager().callEvent(new IslandLevelChangeEvent(island));
            new TaskBuilder().syncWhenFree(new TaskBuilder.SplitTask(20) {
                public Object exec(Object previous) {
                    for (int x = island.getMinProtectedX(); x <= island.getMaxProtectedX(); x++) {
                        for (int z = island.getMinProtectedZ(); z <= island.getMaxProtectedZ(); z++) {
                            for (int y = 0; y <= Core.getCore().getSkyblock().getIslandManager().getWorld().getMaxHeight(); y++) {
                                Block block = island.getCenter().getWorld().getBlockAt(x, y, z);
                                if (block.getType() != Material.AIR)
                                    block.setType(Material.AIR);
                            }
                            split();
                        }
                        split();
                    }
                    return null;
                }
            }).build();
            Core.getCore().getSkyblock().getIslandManager().removeIsland(island);
            return;
        }
        user.sendMsg("&7Type &e/" + cmd + " delete &7again to delete the island!");
        user.sendMsg("&7All upgrades and blocks will be removed!");
        island.setRemove(true);
    }
}
