package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.api.events.IslandLevelChangeEvent;
import me.simonm34.skycore.api.events.IslandMemberChangeEvent;
import me.simonm34.skycore.namestorage.NameUUID;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class IslandKickCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        if (island.getOwner() != user.getUUID()) {
            user.sendMsg("&cOnly the island owner can kick players!");
            return;
        }
        if (args.length == 2) {
            NameUUID target = Core.getCore().getNameStorage().getName(args[1]);
            if (target == null || !island.getAllMembers().contains(target.getUUID())) {
                user.sendMsg("&e" + args[1] + " &7is not apart of your island!");
                return;
            }
            if (user.getUUID() == target.getUUID()) {
                user.sendMsg("&cYou cannot kick yourself!");
                return;
            }
            island.removeMember(target.getUUID());
            Bukkit.getServer().getPluginManager().callEvent(new IslandMemberChangeEvent(island));
            for (UUID uuid : island.getAllMembers()) {
                User target1 = Core.getCore().getUserManager().getUser(uuid);
                if (target1 != null)
                    target1.sendMsg("&e" + target.getName() + " &7was kicked from your island!");
            }
            User target1 = Core.getCore().getUserManager().getUser(target.getUUID());
            if (target1 != null) {
                Warp warp = Core.getCore().getWarpManager().getWarp("spawn");
                if (warp != null) {
                    target1.teleport(warp.getLocation());
                }
                target1.sendMsg("&7You were kicked from your island!");
            }
            return;
        }
        user.sendUsage(cmd, "kick (player)");
    }
}
