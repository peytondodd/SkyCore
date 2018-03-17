package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;

public class AnvilCMD extends Command {
    public AnvilCMD() {
        super("anvil");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            user.openInv(Bukkit.createInventory(null, InventoryType.ANVIL));
            user.sendMsg("&eOpened an anvil!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
