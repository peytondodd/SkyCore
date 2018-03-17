package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public class PunishCMD extends Command {
    public PunishCMD() {
        super("punish", "p");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length == 1) {
                User target = Core.getCore().getUserManager().getUserByName(args[0]);
                if (target == null) {
                    OfflinePlayer target1 = Bukkit.getServer().getOfflinePlayer(args[0]);

                }
            }
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }

    private void buildGUI(User user, User target) {
        ItemStack pinfo = new ItemBuilder().setMaterial(Material.BOOK).setName("&bPlayer Info").addLore("&7Rank Required: &eHelper").build();
        ItemStack warns = new ItemBuilder().setMaterial(Material.BOOK).setName("&bPlayer Warns").addLore("&7Rank Required: &eHelper").build();
        ItemStack binfo = new ItemBuilder().setMaterial(Material.BOOK).setName("&bPlayer Ban Info").addLore("&7Rank Required: &eModerator").build();
    }
}
