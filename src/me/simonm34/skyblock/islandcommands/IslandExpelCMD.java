package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.warp.Warp;

public class IslandExpelCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        if (island.getOwner() != user.getUUID()) {
            user.sendMsg("&cOnly the island owner can expel players!");
            return;
        }
        if (args.length == 2) {
            User target = Core.getCore().getUserManager().getUserByName(args[1]);
            if (target == null) {
                user.sendMsg("&e" + args[1] + " &cis not online!");
                return;
            }
            if (user.getUUID() == target.getUUID()) {
                user.sendMsg("&cYou cannot expel yourself!");
                return;
            }
            Island island1 = Core.getCore().getSkyblock().getIslandManager().getIslandAtLocation(target.getLoc());
            if (island1 != null) {
                if (island == island1) {
                    target.sendMsg("&7You were expelled from &e" + user.getName() + "'s &7island!");
                    Warp warp = Core.getCore().getWarpManager().getWarp("spawn");
                    target.teleport(warp.getLocation());
                    return;
                }
            }
            user.sendMsg("&e" + target.getName() + " &cis not on your island!");
            return;
        }
        user.sendUsage(cmd, "expel (player)");
    }
}
