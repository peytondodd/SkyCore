package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class MessageCMD extends Command {
    public MessageCMD() {
        super("message", "msg");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length > 1) {
                User target = Core.getCore().getUserManager().getUserByName(args[0]);
                if (target == null) {
                    user.sendMsg("&e" + args[0] + " &cis not online!");
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i != args.length; i++)
                    sb.append(args[i]).append(" ");
                String message = sb.toString();

                user.sendRawMsg("&8[&eme &8» &e" + target.getName() + "&8] &f" + message);
                user.setReply(target.getUUID());
                target.sendRawMsg("&8[&e" + user.getName() + " &8» &eme&8] &f" + message);
                target.setReply(user.getUUID());
                return;
            }
            user.sendUsage(cmd, "(player) (message)");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
