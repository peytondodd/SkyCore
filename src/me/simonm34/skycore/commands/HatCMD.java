package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HatCMD extends Command {
    public HatCMD() {
        super("hat", "head");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            ItemStack hand = user.getInv().getItemInMainHand();
            if (hand == null || hand.getType() == Material.AIR) {
                user.sendMsg("&cYou cannot set your hat to nothing!");
                return;
            }
            ItemStack helmet = user.getInv().getHelmet();
            user.getInv().setHelmet(hand);
            if (!(helmet == null || helmet.getType() == Material.AIR))
                user.getInv().setItemInMainHand(helmet);
            user.sendMsg("&7Your hat was set!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
