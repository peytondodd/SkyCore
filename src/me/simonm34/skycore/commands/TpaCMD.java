package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.scheduler.BukkitRunnable;

public class TpaCMD extends Command {
    public TpaCMD() {
        super("tpa", "tpask");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length == 1) {
                User target = Core.getCore().getUserManager().getUserByName(args[0]);
                if (target == null) {
                    user.sendMsg("&e" + args[0] + " &cis not online!");
                    return;
                }
                if (target == user) {
                    user.sendMsg("&cYou cannot teleport to yourself!");
                    return;
                }
                target.setTeleportRequest(user.getUUID());
                target.sendMsg("&e" + user.getName() + " &7has requested to teleport to you!");
                target.sendMsg("&7Type &e/tpaccept &7to accept the request.");
                target.sendMsg("&7Type &e/tpdeny &7to deny the request.");
                target.sendMsg("&7This request wll expire in &e120 &7seconds.");
                user.sendMsg("&7You sent a teleport request to &e" + target.getName() + "&7!");
                runDelay(user, target);
                return;
            }
            user.sendUsage(cmd, "<player>");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }

    private void runDelay(User user, User target) {
        new BukkitRunnable() {
            public void run() {
                if (target.getTeleportRequest() != null || target.getTeleportRequest() == user.getUUID()) {
                    target.setTeleportRequest(null);
                    user.sendMsg("&eTeleport request expired");
                }
            }
        }.runTaskLater(Core.getCore(), 2400);
    }
}
