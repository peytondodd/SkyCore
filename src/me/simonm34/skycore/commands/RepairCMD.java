package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Repairable;

public class RepairCMD extends Command {
    public RepairCMD() {
        super("repair", "fix");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length == 0) {
                repairHand(user);
                return;
            }
            if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case "hand":
                        repairHand(user);
                        return;
                    case "all":
                        repairAll(user);
                        return;
                }
            }
            user.sendUsage(cmd, "(hand/all)");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }

    private void repairHand(User user) {
        ItemStack item = user.getInv().getItemInMainHand();
        if (item == null) {
            user.sendMsg("&cYou cannot fix anything!");
            return;
        }
        if (!(item instanceof Repairable)) {
            user.sendMsg("&cThis item cannot be repaired!");
            return;
        }
        if (item.hasItemMeta()) {
            if (item.getItemMeta().hasEnchants()) {
                if (!user.hasPerm("skycore.repair.enchant")) {
                    user.sendMsg("&cYou cannot repair enchanted items!");
                    return;
                }
            }
        }
        item.setDurability((short) 0);
        user.sendMsg("&7Your item in your hand was repaired!");
    }
    private void repairAll(User user) {
        int repaired = 0;
        for (ItemStack item : user.getItems()) {
            if (!(item instanceof Repairable)) continue;
              if (item.hasItemMeta())
                  if (item.getItemMeta().hasEnchants())
                     if (!user.hasPerm("skycore.repair.enchant"))
                         continue;
            item.setDurability((short) 0);
            repaired++;
        }
        user.sendMsg("&7Repaired &e" + repaired + " &7items!");
    }
}
