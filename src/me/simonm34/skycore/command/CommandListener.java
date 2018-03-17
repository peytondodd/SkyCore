package me.simonm34.skycore.command;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.sender.Sender;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class CommandListener implements Listener {
    public CommandListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Core.getCore());
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage().substring(1);

        String cmd;
        String[] args = new String[]{};

        if (message.contains(" ")) {
            cmd = message.split(" ")[0];
            args = message.replace(cmd + " ", "").split(" ");
        } else {
            cmd = message;
        }

        Command command = Core.getCore().getCommandManager().getCommand(cmd);
        Sender sender = Core.getCore().getUserManager().getUser(player.getUniqueId());
        if (command != null) {
            e.setCancelled(true);
            if (!player.hasPermission("skycore.command." + command.getCommand())) {
                sender.sendMsg("&cYou do not have permission to use &b/" + cmd + "&c!");
                return;
            }
            command.execute(sender, cmd, args);
        }
    }

    @EventHandler
    public void onConsoleCommand(ServerCommandEvent e) {
        CommandSender sender = e.getSender();
        String message = e.getCommand();

        String cmd;
        String[] args = new String[]{};

        if (message.contains(" ")) {
            cmd = message.split(" ")[0];
            args = message.replace(cmd + " ", "").split(" ");
        } else {
            cmd = message;
        }

        Command command = Core.getCore().getCommandManager().getCommand(cmd);
        Sender sender1 = Core.getCore().getSenderManager().getSender(sender);
        if (command != null) {
            e.setCancelled(true);
            command.execute(sender1, cmd, args);
        }
    }
}
