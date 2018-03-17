package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.GameMode;

public class GamemodeSurvivalCMD extends Command {
    public GamemodeSurvivalCMD() {
        super("gamemodesurvival", "gms", "gm0");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            user.setGamemode(GameMode.SURVIVAL);
            user.sendMsg("&7Your gamemode was set to &eSurvival&7!");
        }
    }
}
