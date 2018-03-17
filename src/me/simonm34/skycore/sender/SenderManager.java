package me.simonm34.skycore.sender;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.HashSet;
import java.util.Set;

public class SenderManager {
    public SenderManager() {
        senders.add(new CoreSender(Bukkit.getServer().getConsoleSender()));
    }

    private Set<CoreSender> senders = new HashSet<>();

    public CoreSender getSender(CommandSender commandSender) {
        for (CoreSender sender : senders)
            if (sender.getSender() == commandSender)
                return sender;
        return null;
    }
    public CoreSender getConsole() {
        return getSender(Bukkit.getServer().getConsoleSender());
    }
    public Set<CoreSender> getSenders() {
        return senders;
    }

    public void addSender(CoreSender sender) {
        senders.add(sender);
    }
    public void removeSender(CoreSender sender) {
        senders.remove(sender);
    }
    public void loadSender(CommandSender commandSender) {
        CoreSender sender = new CoreSender(commandSender);
        addSender(sender);
    }
    public void saveSender(CommandSender commandSender) {
        CoreSender sender = getSender(commandSender);
        removeSender(sender);
    }
}
