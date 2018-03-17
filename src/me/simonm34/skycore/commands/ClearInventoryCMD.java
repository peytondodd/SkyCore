package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class ClearInventoryCMD extends Command {
    public ClearInventoryCMD() {
        super("clearinventory", "clearinv", "clear", "ci");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            user.clearInv();
            user.sendMsg("&7Your inventory was cleared!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
