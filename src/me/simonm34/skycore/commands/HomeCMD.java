package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.utils.InventoryBuilder;
import me.simonm34.skycore.utils.ItemBuilder;
import me.simonm34.skycore.warp.Warp;

public class HomeCMD extends Command {
    public HomeCMD() {
        super("home", "homes");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;

            InventoryBuilder invb = new InventoryBuilder();
            invb.setSize(27);
            invb.setName("&6&lSkyTreasure &8» &eHomes");
            for (Warp warp : user.getHomes()) {
                ItemBuilder ib = new ItemBuilder();
                ib.setMaterial(warp.getItem());
                ib.setName("&b" + warp.getName());
                ib.addLore("&7&o(Right click to teleport!)");
                invb.addItem(ib.build());
            }
            user.openInv(invb.build());
            user.sendMsg("&7Opened your &eHomes &7menu!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
