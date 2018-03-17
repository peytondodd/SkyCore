package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemCMD extends Command {
    public ItemCMD() {
        super("item", "i");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length > 0) {
                String name = String.join(" ", args);
                Material material = Core.getCore().getUtils().parseMaterial(name.replace(" ", "_"));
                if (material == null) {
                    user.sendMsg("&cInvalid Item!");
                    return;
                }
                user.getInv().addItem(new ItemStack(material, material.getMaxStackSize()));
                user.sendMsg("&7You were given &e" + material.getMaxStackSize() + " &7of &e" + material.name().toLowerCase().replace("_", " ") + "&7!");
                return;
            }
            user.sendUsage(cmd, "(item)");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
