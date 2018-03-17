package me.simonm34.skycore.commands.blocked;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PluginCMD extends Command {
    public PluginCMD() {
        super("plugin", "plugins", "pl", "bukkit:plugin", "bukkit:plugins", "bukkit:pl");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        List<String> plugins = new ArrayList<>();
        Arrays.stream(
                Core.getCore()
                        .getServer()
                        .getPluginManager()
                        .getPlugins())
                .forEach(plugin -> plugins.add((plugin.isEnabled() ? ChatColor.YELLOW : ChatColor.RED) + plugin.getName()));
        sender.sendMsg("&7Plugins: " + String.join("&7, ", plugins));
    }
}
