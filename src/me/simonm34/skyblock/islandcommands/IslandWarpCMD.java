package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class IslandWarpCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        if (args.length == 2) {
            OfflinePlayer offlinePlayer = Bukkit.getServer().getOfflinePlayer(args[1]);
            Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(offlinePlayer.getUniqueId());
            if (island == null) {
                user.sendMsg("&e" + offlinePlayer.getName() + "'s &cisland could not be found!");
                return;
            }
            if (island.isLocked()) {
                user.sendMsg("&e" + offlinePlayer.getName() + "'s &cisland is locked!");
                return;
            }
            if (island.getWelcomePoint() == null) {
                user.sendMsg("&e" + offlinePlayer.getName() + "'s &cisland does not have a welcome sign!");
                return;
            }
            user.teleport(island.getWelcomePoint());
            user.sendMsg("&7You were warped to &e" + offlinePlayer.getName() + "'s &7island!");
            return;
        }
        user.sendUsage(cmd, "warp (player)");
    }
}
