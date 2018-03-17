package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class TimeCMD extends Command {
    public TimeCMD() {
        super("time", "day", "sunset", "dusk", "night", "sunrise", "dusk");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            Long time = getTime(cmd);
            if (time == null) {
                if (args.length == 1) {
                    time = getTime(args[0]);
                    if (time == null) {
                        user.sendMsg("&cInvalid Time!");
                        return;
                    }
                    user.getLoc().getWorld().setFullTime(time);
                    user.sendMsg("&7Set the time in world &e" + user.getLoc().getWorld().getName() + " &7to &e" + args[0] + "&7!");
                    return;
                }
                user.sendUsage(cmd, "(day/sunset/dusk/night/sunrise/dusk)");
                return;
            }
            user.getLoc().getWorld().setFullTime(time);
            user.sendMsg("&7Set the time in world &e" + user.getLoc().getWorld().getName() + " &7to &e" + cmd + "&7!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }

    private Long getTime(String string) {
        Long time = null;
        switch (string.toLowerCase()) {
            case "day":
                return 0l;
            case "sunset":
            case "dusk":
                return 12000l;
            case "night":
                return 14000l;
            case "sunrise":
            case "dawn":
                return 23000l;
        }
        return time;
    }
}
