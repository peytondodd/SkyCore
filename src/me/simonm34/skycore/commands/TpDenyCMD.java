package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class TpDenyCMD extends Command {
    public TpDenyCMD() {
        super("tpdeny", "tpno");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (user.getTeleportRequest() == null) {
                user.sendMsg("&cYou do not have any teleport requests!");
                return;
            }
            User target = Core.getCore().getUserManager().getUser(user.getTeleportRequest());
            if (target == null) {
                user.sendMsg("&cYou do not have any teleport requests!");
                user.setTeleportRequest(null);
                return;
            }
            user.setTeleportRequest(null);
            user.sendMsg("&7You denied &e" + target.getName() + "'s &7teleport request!");
            target.sendMsg("&7Your teleport request was denied by &e" + user.getName() + "&7!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
