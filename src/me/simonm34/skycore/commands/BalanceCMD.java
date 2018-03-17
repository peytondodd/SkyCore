package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class BalanceCMD extends Command {
    public BalanceCMD() {
        super("balance", "bal", "money");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (args.length == 0) {
            if (sender instanceof User) {
                User user = (User) sender;
                user.sendMsg("&7Balance: &e$" + user.getBalance());
                return;
            }
            sender.sendUsage(cmd, "<player>");
            return;
        }
        if (args.length == 1) {
            User target = Core.getCore().getUserManager().getUserByName(args[0]);
            if (target == null) {
                sender.sendMsg("&e" + args[0] + " &cis not online!");
                return;
            }
            sender.sendMsg("&7" + target.getName() + "'s Balance: &e$" + target.getBalance());
            return;
        }
        sender.sendUsage(cmd, "(player)");
    }
}
