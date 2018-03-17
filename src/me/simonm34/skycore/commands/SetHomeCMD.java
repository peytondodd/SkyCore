package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.warp.Warp;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class SetHomeCMD extends Command {
    public SetHomeCMD() {
        super("sethome");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length == 1) {
                if (getMaxHomes(user) != -1 && user.getHomes().size() >= getMaxHomes(user)) {
                    user.sendMsg("&cYou cannot have more than &e" + user.getHomes().size() + " &chomes!");
                    return;
                }
                if (user.getHome(args[0]) != null) {
                    user.sendMsg("&cHome &e" + args[0] + " &calready exists!");
                    return;
                }
                Warp warp = new Warp(args[0], user.getLoc(), Material.BOOK);
                user.addHome(warp);
                user.sendMsg("&7Set home &e" + args[0] + " &7to your location!");
                return;
            }
            user.sendUsage(cmd, "(home)");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }

    private int getMaxHomes(User user) {
        int homes = 1;
        if (user.hasPerm("skycore.homes.unlimited"))
            return -1;
        for (PermissionAttachmentInfo permission : user.getPlayer().getEffectivePermissions()) {
            String perm = permission.getPermission().toLowerCase();
            if (perm.startsWith("skycore.homes.multiple.")) {
                String maxString = perm.replaceAll("[^0-9]", "");
                int current = Core.getCore().getUtils().parseInteger(maxString);
                if (maxString != null)
                    if (current > homes)
                        homes = current;
            }
        }
        return homes;
    }
}
