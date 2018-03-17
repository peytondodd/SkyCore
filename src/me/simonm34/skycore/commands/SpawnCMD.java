package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.warp.Warp;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCMD extends Command {
    public SpawnCMD() {
        super("spawn");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            Warp warp = Core.getCore().getWarpManager().getWarp("Spawn");
            if (warp == null) {
                user.sendMsg("&cThe spawn is not yet set!");
                return;
            }
            if (!user.hasPerm("skycore.teleport.cooldown.bypass")) {
                user.setTeleporting(true);
                user.sendMsg("&7You will be teleported in 3 seconds!");

                new BukkitRunnable() {
                    public void run() {
                        if (user.isTeleporting()) {
                            user.teleport(warp.getLocation());
                            user.sendMsg("&7You were warped to &eSpawn&7!");
                            user.setTeleporting(false);
                        }
                    }
                }.runTaskLater(Core.getCore(), 60);
                return;
            }
            user.teleport(warp.getLocation());
            user.sendMsg("&7You were warped to &eSpawn&7!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
