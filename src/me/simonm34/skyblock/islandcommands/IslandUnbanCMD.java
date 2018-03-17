package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.namestorage.NameUUID;
import me.simonm34.skycore.user.User;

public class IslandUnbanCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        if (island.getOwner() != user.getUUID()) {
            user.sendMsg("&cOnly the island owner can unban players!");
            return;
        }
        if (args.length == 2) {
            NameUUID target = Core.getCore().getNameStorage().getName(args[1]);
            if (target == null || !island.getBanned().contains(target.getUUID())) {
                user.sendMsg("&e" + target.getName() + " &7is not banned from your island!");
                return;
            }
            if (user.getUUID() == target.getUUID()) {
                user.sendMsg("&cYou cannot unban yourself!");
                return;
            }
            island.removeBan(target.getUUID());
            user.sendMsg("&e" + target.getName() + " &7was unbanned from your island!");

            User target1 = Core.getCore().getUserManager().getUser(target.getUUID());
            if (target1 != null) {
                target1.sendMsg("&7You were unbanned from &e" + user.getName() + "'s &7island!");
            }
            return;
        }
        user.sendUsage(cmd, "unban (user)");
    }
}
