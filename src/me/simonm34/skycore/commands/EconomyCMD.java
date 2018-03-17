package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.api.events.PlayerBalanceChangeEvent;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;

public class EconomyCMD extends Command {
    public EconomyCMD() {
        super("economy", "eco");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (args.length == 3) {
            User target = Core.getCore().getUserManager().getUserByName(args[0]);
            if (target == null) {
                sender.sendMsg("&e" + args[0] + " &cis not online!");
                return;
            }
            Double amount = Core.getCore().getUtils().parseDouble(args[2]);
            if (amount == null) {
                sender.sendMsg("&cInvalid Amount!");
                return;
            }
            switch (args[1].toLowerCase()) {
                case "give":
                    target.setBalance(target.getBalance() + amount);
                    target.sendMsg("&7You were given &e$" + amount + "&7!");
                    sender.sendMsg("&e" + target.getName() + " &7was given &e$" + amount + "&7!");
                    Bukkit.getServer().getPluginManager().callEvent(new PlayerBalanceChangeEvent(target));
                    return;
                case "take":
                    if (target.getBalance() - amount < 0) {
                        sender.sendMsg("&e" + target.getName() + " &ccannot have a negative balance!");
                        return;
                    }
                    target.setBalance(target.getBalance() - amount);
                    target.sendMsg("&7You had &e$" + amount + " &7taken away!");
                    sender.sendMsg("&e" + target.getName() + " &7had &e$" + amount + " &7taken away!");
                    Bukkit.getServer().getPluginManager().callEvent(new PlayerBalanceChangeEvent(target));
                    return;
                case "set":
                    if (amount < 0) {
                        sender.sendMsg("&e" + target.getName() + " &ccannot have a negative balance!");
                        return;
                    }
                    target.setBalance(amount);
                    target.sendMsg("&7Your balance was set to &e$" + amount + "&7!");
                    sender.sendMsg("&e" + target.getName() + "'s &7balance was set to &e$" + amount + "&7!");
                    Bukkit.getServer().getPluginManager().callEvent(new PlayerBalanceChangeEvent(target));
                    return;
            }
        }
        sender.sendUsage(cmd, "<player> <give/take/set> <amount>");
    }
}
