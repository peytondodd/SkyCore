package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class SuicideCMD extends Command {
    public SuicideCMD() {
        super("suicide");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            user.setHealth(0.0);
            Core.getCore().getUserManager().getUsers().forEach(target -> target.sendMsg("&e" + user.getName() + " &7killed them self... &e☹ &7Rip"));
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
