package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;

public class CoreCMD extends Command {
    public CoreCMD() {
        super("core", "skycore");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPerm("skycore.reload")) {
                    Core.getCore().reloadConfig();
                    Core.getCore().getSettings().setSettings();
                    Core.getCore().getRankManager().loadRanks();
                    sender.sendMsg("&7SkyCore was successfully reloaded!");
                    return;
                }
                sender.sendMsg("&cYou do not have permission to use this!");
                return;
            }
        }
        sender.sendUsage(cmd, "(reload)");
    }
}
