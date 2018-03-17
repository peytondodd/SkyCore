package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.scheduler.BukkitRunnable;

public class TpAcceptCMD extends Command {
    public TpAcceptCMD() {
        super("tpaccept", "tpyes");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (user.getTeleportRequest() == null) {
                user.sendMsg("&cYou do not have any teleport requests!");
                return;
            }
            User target = Core.getCore().getUserManager().getUser(user.getTeleportRequest());
            if (target == null) {
                user.sendMsg("&cYou do not have any teleport requests!");
                user.setTeleportRequest(null);
                return;
            }
            teleport(user, target);
            user.sendMsg("&7You will be teleported to &e" + target.getName() + " &7in &e3 &7seconds!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }

    private void teleport(User user, User target) {
        long expire = System.currentTimeMillis() + 3000;
        int x = user.getLoc().getBlockX();
        int y = user.getLoc().getBlockY();
        int z = user.getLoc().getBlockZ();

        new BukkitRunnable() {
            public void run() {
                if (x != user.getLoc().getBlockX() ||
                        y != user.getLoc().getBlockY() ||
                        z != user.getLoc().getBlockZ()) {
                    user.sendMsg("&cTeleportation canceled. Don't move!");
                    user.setTeleportRequest(null);
                    cancel();
                    return;
                }
                if (expire - System.currentTimeMillis() < 0) {
                    user.teleport(target.getLoc());
                    user.sendMsg("&7You were teleported to &e" + target.getName());
                    target.sendMsg("&e" + user.getName() + " &7was teleported to you!");
                    user.setTeleportRequest(null);
                    cancel();
                }
            }
        }.runTaskTimer(Core.getCore(), 0, 10);
    }
}
