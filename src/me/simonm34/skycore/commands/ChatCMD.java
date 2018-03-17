package me.simonm34.skycore.commands;

import me.simonm34.skycore.chat.ChatType;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class ChatCMD extends Command {
    public ChatCMD() {
        super("chat", "public", "publicchat",  "team", "teamchat", "staff", "staffchat");
    }

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            ChatType chatType = getChatType(cmd);
            if (chatType == null) {
                if (args.length == 1) {
                    chatType = getChatType(args[0]);
                    if (chatType == null) {
                        user.sendMsg("&cInvalid Chat Type!");
                        return;
                    }
                    if (user.hasPerm("skycore.command.chat." + chatType.getName().toLowerCase())) {
                        user.setChat(chatType);
                        user.sendMsg("&7Your chat was set to &e" + user.getChat().getName() + "&7!");
                        return;
                    }
                    user.sendMsg("&cYou do not have permission to set your chat to &e" + chatType.getName() + "&c!");
                    return;
                }
                user.sendUsage(cmd, "(public/team/staff)");
                return;
            }
            user.setChat(chatType);
            user.sendMsg("&7Your chat was set to &e" + user.getChat().getName() + "&7!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }

    private ChatType getChatType(String string) {
        switch (string.toLowerCase()) {
            case "public":
            case "publichat":
                return ChatType.PUBLIC;
            case "team":
            case "teamchat":
                return ChatType.TEAM;
            case "staff":
            case "staffchat":
                return ChatType.STAFF;
        }
        return null;
    }
}
