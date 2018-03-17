package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;

import java.util.UUID;

public class IslandLockCMD implements IslandCommand {
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
        island.setLocked(!island.isLocked());
        for (UUID uuid : island.getAllMembers()) {
            User target = Core.getCore().getUserManager().getUser(uuid);
            if (target != null)
                target.sendMsg("&7The island was &e" + (island.isLocked() ? "locked" : "unlocked") + "&7!");
        }
    }
}
