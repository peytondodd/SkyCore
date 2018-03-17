package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.GameMode;

public class GamemodeCreativeCMD extends Command {
    public GamemodeCreativeCMD() {
        super("gamemodecreative", "gmc", "gm1");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            user.setGamemode(GameMode.CREATIVE);
            user.sendMsg("&7Your gamemode was set to &eCreative&7!");
        }
    }
}