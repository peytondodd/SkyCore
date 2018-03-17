package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.invite.Invite;
import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.api.events.IslandMemberChangeEvent;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;

import java.util.UUID;

public class IslandAcceptCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Invite invite = Core.getCore().getSkyblock().getInviteManager().getInvite(user.getUUID());
        if (invite == null) {
            user.sendMsg("&cYou do not have any current invites!");
            return;
        }
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(invite.getIslandUUID());
        if (island == null) {
            user.sendMsg("&cThe island you were invited to no longer exists!");
            Core.getCore().getSkyblock().getInviteManager().removeInvite(invite);
            return;
        }
        if (island.getMembers().size() >= island.getMembersUpgrade().getMembers()) {
            user.sendMsg("&cThis island has reached the max number of members!");
            Core.getCore().getSkyblock().getInviteManager().removeInvite(invite);
            return;
        }
        user.sendMsg("&eAccepted &7invite!");
        for (UUID uuid : island.getAllMembers()) {
            User target = Core.getCore().getUserManager().getUser(uuid);
            if (target != null)
                target.sendMsg("&e" + user.getName() + " &7joined your island!");
        }
        Bukkit.getServer().getPluginManager().callEvent(new IslandMemberChangeEvent(island));
        user.teleport(island.getSpawnPoint());
        island.addMember(user.getUUID());
        Core.getCore().getSkyblock().getInviteManager().removeInvite(invite);
    }
}
