package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.warp.Warp;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SetWarpCMD extends Command {
    public SetWarpCMD() {
        super("setwarp");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length == 1) {
                Warp warp = Core.getCore().getWarpManager().getWarp(args[0]);
                if (warp != null) {
                    user.sendMsg("&e" + args[0] + " &cwarp already exists!");
                    return;
                }
                ItemStack item = user.getInv().getItemInMainHand();
                if (item == null || item.getType() == Material.AIR) {
                    user.sendMsg("&cThe warp icon cannot be air!");
                    return;
                }
                warp = new Warp(args[0].substring(0, 1).toUpperCase() + args[0].substring(1).toLowerCase(), user.getLoc(), item.getType());
                Core.getCore().getWarpManager().addWarp(warp);
                user.sendMsg("&e" + warp.getName() + " &7warp was created and set to your location!");
                return;
            }
            user.sendUsage(cmd, "(warp)");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
