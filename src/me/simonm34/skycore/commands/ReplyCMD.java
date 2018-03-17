package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class ReplyCMD extends Command {
    public ReplyCMD() {
        super("reply", "r");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length > 0) {
                User target = Core.getCore().getUserManager().getUser(user.getUUID());
                if (target == null) {
                    user.sendMsg("&cYou have no one to reply to, loner!");
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i != args.length; i++)
                    sb.append(args[i]).append(" ");
                String message = sb.toString();

                user.sendRawMsg("&8[&eme &8» &e" + target.getName() + "&8] &f" + message);
                user.setReply(target.getUUID());
                target.sendRawMsg("&8[&e" + user.getName() + " &8» &eme&8] &f" + message);
                target.setReply(user.getUUID());
                return;
            }
            user.sendUsage(cmd, "(message)");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
