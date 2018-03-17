package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class GodCMD extends Command {
    public GodCMD() {
        super("god");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            user.setGodMode(user.isGodMode());
            user.sendMsg("&7God mode was &e" + (user.isGodMode() ? "enabled" : "disabled") + "&7!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
