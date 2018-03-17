package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.api.events.PlayerBalanceChangeEvent;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;

public class PayCMD extends Command {
    public PayCMD() {
        super("pay");
    }

    //pay user amount
    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length == 2) {
                User target = Core.getCore().getUserManager().getUserByName(args[0]);
                if (target == null) {
                    user.sendMsg("&e" + args[0] + " &cis not online!");
                    return;
                }
                if (user == target) {
                    user.sendMsg("&cYou cannot pay yourself!");
                    return;
                }
                Double amount = Core.getCore().getUtils().parseDouble(args[1]);
                if (amount == null) {
                    user.sendMsg("&cInvalid Amount!");
                    return;
                }
                if (user.getBalance() - amount < 0) {
                    user.sendMsg("&cYou have insufficient funds!");
                    return;
                }
                user.setBalance(user.getBalance() - amount);
                target.setBalance(target.getBalance() + amount);
                user.sendMsg("&7You sent &e$" + amount + " &7to &e" + target.getName() + "&7!");
                target.sendMsg("&7You received &e$" + amount + " &7from &e" + user.getName() + "&7!");

                Bukkit.getServer().getPluginManager().callEvent(new PlayerBalanceChangeEvent(user));
                Bukkit.getServer().getPluginManager().callEvent(new PlayerBalanceChangeEvent(target));
                return;
            }
            user.sendUsage(cmd, "<player> <amount>");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
