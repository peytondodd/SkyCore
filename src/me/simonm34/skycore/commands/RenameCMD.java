package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCMD extends Command {
    public RenameCMD() {
        super("rename");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length > 0) {
                ItemStack item = user.getInv().getItemInMainHand();
                if (item == null || item.getType() == Material.AIR) {
                    user.sendMsg("&cYou cannot rename air!");
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i != args.length; i++)
                    sb.append(args[i]).append(i+1 == args.length ? "" : " ");
                String name = sb.toString();
                for (String word : Core.getCore().getSettings().getSwears()) {
                    if (name.toLowerCase().contains(word.toLowerCase())) {
                        user.sendMsg("&cYou cannot use swears when naming an item!!");
                        return;
                    }
                }
                for (String word : Core.getCore().getSettings().getBlacklistedNames()) {
                    if (name.toLowerCase().contains(word.toLowerCase())) {
                        user.sendMsg("&cThis item name is not allowed!!");
                        return;
                    }
                }
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                item.setItemMeta(meta);

                user.sendMsg("&7Your item's name was set to &e" + name + "&7!");
                return;
            }
            user.sendUsage(cmd, "(name)");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
