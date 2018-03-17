package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.Location;

public class TopCMD extends Command {
    public TopCMD() {
        super("skycore.top", "top");
    }
    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            Location loc = user.getLoc().getWorld().getHighestBlockAt(user.getLoc()).getLocation().add(0.0, 1.0, 0.0);
            user.teleport(loc);
            user.sendMsg("&7Warped to the top!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
