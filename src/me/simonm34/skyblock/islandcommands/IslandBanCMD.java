package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.warp.Warp;

public class IslandBanCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        if (island.getOwner() != user.getUUID()) {
            user.sendMsg("&cOnly the island owner can ban players!");
            return;
        }
        if (args.length == 2) {
            User target = Core.getCore().getUserManager().getUserByName(args[1]);
            if (target == null) {
                user.sendMsg("&e" + args[1] + " &cis not online!");
                return;
            }
            if (user.getUUID() == target.getUUID()) {
                user.sendMsg("&cYou cannot ban yourself!");
                return;
            }
            if (island.getAllMembers().contains(target.getUUID())) {
                user.sendMsg("&e" + target.getName() + " &cis a member in your island!");
                return;
            }
            if (island.getBanned().contains(target.getUUID())) {
                user.sendMsg("&e" + target.getName() + " &cis already banned from your island!");
                return;
            }
            island.addBan(target.getUUID());
            user.sendMsg("&e" + target.getName() + " &cwas banned from your island!");
            Island island1 = Core.getCore().getSkyblock().getIslandManager().getIslandAtLocation(target.getLoc());
            if (island1 != null) {
                if (island1.getBanned().contains(target.getUUID())) {
                    target.sendMsg("&cYou were banned from this island!");
                    Warp warp = Core.getCore().getWarpManager().getWarp("spawn");
                    if (warp != null) {
                        target.teleport(warp.getLocation());
                    }
                    return;
                }
                target.sendMsg("&cYou were banned from &e" + user.getName() + "'s &7island!");
            }
            return;
        }
        user.sendUsage(cmd, "ban (player)");
    }
}
