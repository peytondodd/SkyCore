package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;

public class AnnounceCMD extends Command {
    public AnnounceCMD() {
        super("announcement", "announce");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (args.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i != args.length; i++)
                sb.append(args[i]).append(" ");
            String message = sb.toString().substring(0, sb.toString().length() - 1);

            Core.getCore().getUtils().broadcastRawMsg("&2&lAnnouncement &8» &a" + message);
            return;
        }
        sender.sendUsage(cmd, "(message)");
    }
}
