package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.warp.Warp;
import org.bukkit.Material;

public class SetSpawnCMD extends Command {
    public SetSpawnCMD() {
        super("setspawn");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            Warp warp = new Warp("Spawn", user.getLoc(), Material.AIR);
            Core.getCore().getWarpManager().addWarp(warp);
            user.sendMsg("&7You set the spawn to your location!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
