package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;

public class IslandCoopCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        if (island.getOwner() != user.getUUID()) {
            user.sendMsg("&cOnly the island owner can lock the island!");
            return;
        }
        if (args.length == 2) {
            User target = Core.getCore().getUserManager().getUserByName(args[1]);
            if (target == null) {
                user.sendMsg("&e" + args[1] + " &cis not online!");
                return;
            }
            if (user.getUUID() == target.getUUID()) {
                user.sendMsg("&cYou cannot invite yourself!");
                return;
            }
            if (island.getCooped().contains(target.getUUID())) {
                user.sendMsg("&e" + target.getName() + " &cis already cooped!");
                return;
            }
            user.sendMsg("&e" + target.getName() + " &7was cooped!");
            target.sendMsg("&7You were cooped on &e" + user.getName() + "'s &7island!");
            island.addCoop(target.getUUID());
            return;
        }
        user.sendUsage(cmd, "coop (player)");
    }
}
