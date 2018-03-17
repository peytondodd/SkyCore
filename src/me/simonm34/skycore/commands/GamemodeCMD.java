package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.GameMode;

public class GamemodeCMD extends Command {
    public GamemodeCMD() {
        super("gamemode", "gm");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length == 1) {
                GameMode gameMode;
                switch (args[0].toLowerCase()) {
                    case "survival":
                    case "s":
                    case "0":
                        gameMode = GameMode.SURVIVAL;
                        break;
                    case "creative":
                    case "c":
                    case "1":
                        gameMode = GameMode.CREATIVE;
                        break;
                    case "adventure":
                    case "a":
                    case "2":
                        gameMode = GameMode.ADVENTURE;
                        break;
                    case "spectator":
                    case "spec":
                    case "3":
                        gameMode = GameMode.SPECTATOR;
                        break;
                    default:
                        user.sendMsg("&2Invalid gamemode!");
                        return;
                }
                user.setGamemode(gameMode);
                user.sendMsg("&7Your gamemode was set to &e" + gameMode.name().substring(0, 1).toUpperCase() + gameMode.name().substring(1).toLowerCase() + "&7!");
                return;
            }
            user.sendUsage(cmd, "(gamemode)");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
