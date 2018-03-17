package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.utils.SkullBuilder;
import org.bukkit.inventory.ItemStack;

public class SkullCMD extends Command {
    public SkullCMD() {
        super("skull", "head");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length == 0) {
                ItemStack item = new SkullBuilder().setOwner(user.getName()).setName("&f" + user.getName() + "'s Skull").build();
                user.getInv().addItem(item);
                user.sendMsg("&7You were given your skull!");
                return;
            }
            if (args.length == 1) {
                if (user.hasPerm("skycore.skull.others")) {
                    ItemStack item = new SkullBuilder().setOwner(args[0]).setName("&f" + args[0] + "'s Skull").build();
                    user.getInv().addItem(item);
                    user.sendMsg("&7You were given &e" + args[0] + "'s &7skull!");
                    return;
                }
                user.sendMsg("&cYou do not have permission to use this!");
                return;
            }
            user.sendUsage(cmd, "(player)");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
