package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.chat.ChatType;
import me.simonm34.skycore.user.User;

public class IslandTeamChatCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        if (user.getChat() == ChatType.TEAM)
            user.setChat(ChatType.PUBLIC);
        if (user.getChat() == ChatType.PUBLIC || user.getChat() == ChatType.STAFF)
            user.setChat(ChatType.TEAM);
        user.sendMsg("&7Your chat was set to &e" + user.getChat().getName() + "&7!");
    }
}
