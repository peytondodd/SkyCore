package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class WorkbenchCMD extends Command {
    public WorkbenchCMD() {
        super("workbench", "craft");
    }
    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            user.openWorkbench();
            user.sendMsg("&7Opened a workbench!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
