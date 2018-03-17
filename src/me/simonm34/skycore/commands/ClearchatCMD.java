package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class ClearchatCMD extends Command {
    public ClearchatCMD() {
        super ("clearchat", "cc");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        boolean silent = args.length == 1 && args[0].equalsIgnoreCase("-s");

        for (User target : Core.getCore().getUserManager().getUsers())
            if (!target.hasPerm("skycore.clearchat.bypass"))
                for (int i = 0; i != 75; i++)
                    target.sendRawMsg(" ");
        Core.getCore().getUtils().broadcastMsg("&7The chat was cleared by &e" + (silent ? "a Staff Member" : sender.getName()) + "&7!");
    }
}
