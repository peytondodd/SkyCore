package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class FlyCMD extends Command {
    public FlyCMD() {
        super("fly", "flight");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            user.setCanFly(!user.canFly());
            user.sendMsg("&7Flight mode has been &e" + (user.canFly() ? "enabled" : "disabled") + "&7!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
