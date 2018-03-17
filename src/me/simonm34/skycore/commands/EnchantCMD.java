package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class EnchantCMD extends Command {
    public EnchantCMD() {
        super("enchant", "enchantment");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            ItemStack item = user.getInv().getItemInMainHand();
            if (item == null) {
                user.sendMsg("&cYou cannot enchant air!");
                return;
            }
            if (args.length > 0) {
                List<String> list = Arrays.asList(args);
                Integer integer = Core.getCore().getUtils().parseInteger(list.get(list.size() - 1));
                list.remove(list.get(list.size() - 1));
                String enchant = String.join("_", list);
                Enchantment enchantment = Core.getCore().getUtils().parseEnchantment(args[0]);
                if (integer == null || enchant == null) {
                    user.sendUsage(cmd, "(enchantment) (level)");
                    return;
                }
                if (!enchantment.canEnchantItem(item)) {
                    user.sendMsg("&cThis enchantment cannot be given to that item!");
                    return;
                }
                item.addUnsafeEnchantment(enchantment, integer);
                user.sendMsg("&eThis item was enchanted!");
                return;
            }
            user.sendUsage(cmd, "(enchantment) (level)");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}