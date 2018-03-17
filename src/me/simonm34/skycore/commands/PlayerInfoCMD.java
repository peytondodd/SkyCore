package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;

public class PlayerInfoCMD extends Command {
    public PlayerInfoCMD() {
        super("playerinfo", "pinfo");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (args.length == 1) {
            User user = Core.getCore().getUserManager().getUserByName(args[0]);
            if (user == null) {
                sender.sendMsg("&e" + args[0] + " &cis not online!");
                return;
            }
            sender.sendMsg("&e" + user.getName() + "'s Information");
            sender.sendMsg("&7Rank: &e" + user.getRank().getName());
            sender.sendMsg("&7Subrank: &e" + (user.getSubrank() == null ? "None" : user.getSubrank().getName()));
            sender.sendMsg("&7Tag: &eNone");
            sender.sendMsg("&7Ping: &e" + ((CraftPlayer) user.getPlayer()).getHandle().ping + "ms");
            sender.sendMsg("&7Type &e/baninfo " + user.getName() + " &7for more info!");
            return;
        }
        sender.sendUsage(cmd, "(player)");
    }
}
