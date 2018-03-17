package me.simonm34.skycore.commands;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.chat.ChatType;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class SpectateCMD extends Command {
    public SpectateCMD() {
        super("spectate", "spec");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (user.getSpectating() != null) {
                User target = Core.getCore().getUserManager().getUser(user.getSpectating());
                if (target != null)
                    target.setSpectator(null);
                if (user.hasPerm("skycore.command.chat.staff"))
                    user.setChat(ChatType.PUBLIC);
                user.setSpectating(null);
                user.sendMsg("&7You are no longer spectating anyone!");
                showUsers(user);
                return;
            }
            if (args.length == 1) {
                User target = Core.getCore().getUserManager().getUserByName(args[0]);
                if (target == null) {
                    user.sendMsg("&e" + args[0] + " &cis not online!");
                    return;
                }
                if (user == target) {
                    user.sendMsg("&cYou cannot spectate yourself!");
                    return;
                }
                user.setSpectating(target.getUUID());
                target.setSpectating(user.getUUID());
                user.teleport(target.getLoc());
                user.sendMsg("&7You are now spectating &e" + target.getName() + "&7!");
                if (user.hasPerm("skycore.command.chat.staff"))
                    user.setChat(ChatType.STAFF);
                hideUsers(user);
                return;
            }
            user.sendUsage(cmd, "<player>");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }

    private void hideUsers(User user) {
        Core.getCore().getUserManager().getUsers().stream()
                .filter(target -> !target.hasPerm("skycore.command.spectate.bypass"))
                .forEach(target -> target.hideUser(user));
    }
    private void showUsers(User user) {
        Core.getCore().getUserManager().getUsers()
                .forEach(target -> target.showUser(user));
    }
}
