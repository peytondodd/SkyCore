package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;

import java.util.UUID;

public class IslandMakeLeaderCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        if (island.getOwner() != user.getUUID()) {
            user.sendMsg("&cOnly the island owner can give up ownership!");
            return;
        }
        if (args.length == 2) {
            User target = Core.getCore().getUserManager().getUserByName(args[1]);
            if (target == null) {
                user.sendMsg("&e" + args[1] + " &cis not online!");
                return;
            }
            if (user.getUUID() == target.getUUID()) {
                user.sendMsg("&cYou are aready the owner!");
                return;
            }
            if (island.getAllMembers().contains(target.getUUID())) {
                island.addMember(user.getUUID());
                island.removeMember(target.getUUID());
                island.setOwner(target.getUUID());
                for (UUID uuid : island.getAllMembers()) {
                    User target1 = Core.getCore().getUserManager().getUser(uuid);
                    if (target1 != null)
                        target1.sendMsg("&e" + target.getName() + " &7was given ownership of your island!");
                }
                return;
            }
            user.sendMsg("&e" + target.getName() + " &cis not apart of your island!");
            return;
        }
        user.sendUsage(cmd, "makeleader (player)");
    }
}
