package me.simonm34.skyblock.islandcommands;

import me.simonm34.skycore.user.User;

public interface IslandCommand {
    void execute(User user, String cmd, String[] args);
}
