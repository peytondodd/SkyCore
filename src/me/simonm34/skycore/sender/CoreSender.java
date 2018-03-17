package me.simonm34.skycore.sender;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CoreSender implements Sender {
    private CommandSender sender;

    public CoreSender(CommandSender sender) {
        this.sender = sender;
    }

    public CommandSender getSender() {
        return sender;
    }
    public String getName() {
        return sender.getName();
    }
    public boolean hasPerm(String string) {
        return sender.hasPermission(string);
    }

    public void sendMsg(String string) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lSkyTreasure &8» " + string));
    }
    public void sendMsg(BaseComponent[] baseComponents) {
        sender.spigot().sendMessage(baseComponents);
    }
    public void sendRawMsg(String string) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
    }
    public void sendUsage(String cmd, String usage) {
        sendMsg("&7Usage: /" + cmd + " " + usage);
    }
}
