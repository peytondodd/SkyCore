package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.api.events.PlayerRankChangeEvent;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.rank.Rank;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;

public class RankCMD extends Command {
    public RankCMD() {
        super("rank");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (args.length == 2) {
            User target = Core.getCore().getUserManager().getUserByName(args[0]);
            if (target == null) {
                sender.sendMsg("&e" + args[0] + " &cis not online!");
                return;
            }
            Rank rank = Core.getCore().getRankManager().getRank(args[1]);
            if (rank == null) {
                sender.sendMsg("&e" + args[1] + " &crank does not exist!");
                return;
            }
            if (target.getRank() == rank) {
                sender.sendMsg("&e" + target.getName() + " &calready has the rank &e" + rank.getName() + "&c!");
                return;
            }
            if (target.getSubrank() != null)
                if (target.getSubrank() == rank)
                    target.setSubrank(null);
            target.setRank(rank);
            sender.sendMsg("&e" + target.getName() + "'s &7rank was set to &e" + rank.getName() + "&7!");
            target.sendMsg("&7Your rank was set to &e" + rank.getName() + "&7!");

            Bukkit.getServer().getPluginManager().callEvent(new PlayerRankChangeEvent(target));
            return;
        }
        sender.sendUsage(cmd, "(player) (rank)");
    }
}
