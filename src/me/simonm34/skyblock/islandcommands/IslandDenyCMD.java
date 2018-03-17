package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.invite.Invite;
import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;

public class IslandDenyCMD implements IslandCommand {
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

        User target = Core.getCore().getUserManager().getUser(invite.getIslandUUID());
        if (target != null) {
            target.sendMsg("&e" + user.getName() + " &7denied your invite!");
        }
        user.sendMsg("&eDeclined &7invite!");
        Core.getCore().getSkyblock().getInviteManager().removeInvite(invite);
    }
}
