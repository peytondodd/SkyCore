package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.invite.Invite;
import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import org.bukkit.scheduler.BukkitRunnable;

public class IslandInviteCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        if (island.getOwner() != user.getUUID()) {
            user.sendMsg("&cOnly the island owner can invite players!");
            return;
        }
        if (island.getMembers().size() >= island.getMembersUpgrade().getMembers()) {
            user.sendMsg("&cYou have reached the max number of members!");
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
            if (island.getBanned().contains(target.getUUID())) {
                user.sendMsg("&e" + target.getName() + " &cis banned from your island!");
                return;
            }
            Invite invite = new Invite(target.getUUID(), user.getUUID());
            Core.getCore().getSkyblock().getInviteManager().addInvite(invite);
            target.sendMsg("&e" + user.getName() + " &7has invited you to join their island!");
            target.sendMsg("&7Type &e/island (accept/deny) &7to join!");
            user.sendMsg("&7Sent an invite to &e" + target.getName());

            new BukkitRunnable() {
                public void run() {
                    if (Core.getCore().getSkyblock().getInviteManager().getInvite(target.getUUID()) != null) {
                        Core.getCore().getSkyblock().getInviteManager().removeInvite(invite);
                        user.sendMsg("&7Invite expired!");
                        target.sendMsg("&7Invite expired!");
                    }
                }
            }.runTaskLater(Core.getCore(), 1200);
            return;
        }
        user.sendUsage(cmd, "invite (player)");
    }
}
