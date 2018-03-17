package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class ShopCMD extends Command {
    public ShopCMD() {
        super("shop");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            user.openInv(Core.getCore().getShop().getShop());
            user.sendMsg("&7Opened the &eShops &7menu!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
