package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;

import java.util.UUID;

public class IslandTeamCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        if (island.getMembers().size() == 0) {
            user.sendMsg("&cYour island does not have any team members!");
            return;
        }
        user.sendMsg("&7Island Team Members:");
        for (UUID uuid : island.getMembers()) {
            User target = Core.getCore().getUserManager().getUser(uuid);
            if (target == null)
                user.sendMsg("&c(Offline) &e" + Core.getCore().getNameStorage().getName(uuid).getName());
            else
                user.sendMsg("&a(Online) &e" + target.getName());
        }
    }
}
