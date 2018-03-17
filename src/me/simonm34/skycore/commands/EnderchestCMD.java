package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class EnderchestCMD extends Command {
    public EnderchestCMD() {
        super("enderchest", "ender", "ec");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            user.openInv(user.getEnderchest());
            user.sendMsg("&7Your enderchest was opened!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
