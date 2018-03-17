package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.utils.InventoryBuilder;
import me.simonm34.skycore.utils.ItemBuilder;
import me.simonm34.skycore.warp.Warp;

public class DelWarpCMD extends Command {
    public DelWarpCMD() {
        super("delwarp", "deletewarp");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;

            InventoryBuilder invb = new InventoryBuilder();
            invb.setSize(27);
            invb.setName("&3&lSkyCore &8» &eDelete Warp");
            for (Warp warp : Core.getCore().getWarpManager().getWarps()) {
                if (warp.getName().equalsIgnoreCase("warp")) continue;
                ItemBuilder ib = new ItemBuilder();
                ib.setMaterial(warp.getItem());
                ib.setName("&a" + warp.getName());
                ib.addLore("&7&o(Right click to delete!)");
                invb.addItem(ib.build());
            }
            user.openInv(invb.build());
            user.sendMsg("&7Opened the &eWarp Delete &7menu!");
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
