package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.namestorage.NameUUID;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class IslandUncoopCMD implements IslandCommand {
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
            NameUUID target = Core.getCore().getNameStorage().getName(args[1]);
            if (user.getUUID() == target.getUUID()) {
                user.sendMsg("&cYou cannot coop yourself!");
                return;
            }
            if (!island.getCooped().contains(target.getUUID())) {
                user.sendMsg("&e" + target.getName() + " &7is not cooped!");
                return;
            }
            user.sendMsg("&e" + target.getName() + " &7was removed from the coop!");
            island.removeCoop(target.getUUID());

            User target1 = Core.getCore().getUserManager().getUser(target.getUUID());
            if (target1 != null) {
                target1.sendMsg("&7You were uncooped from &e" + user.getName() + "'s &7islands!");
            }
            return;
        }
        user.sendUsage(cmd, "uncoop (player)");
    }
}
