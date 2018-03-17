package me.simonm34.skycore.scoreboard;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.rank.Rank;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

public class ScoreboardsManager {
    public void getNewScoreboard(User user) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("scoreboard", "SkyTreasures");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(Core.getCore().getUtils().getColor("&6&lSkyTreasure"));

        /* Header */
        Score header = obj.getScore(Core.getCore().getUtils().getColor("&8&m--------------------"));
        header.setScore(10);

        /* Account */
        Score playerInfo = obj.getScore(Core.getCore().getUtils().getColor("&8» &e&lPlayer Info:"));
        playerInfo.setScore(9);

        /* Rank */
        Team rank = board.registerNewTeam("rank");
        rank.addEntry(Core.getCore().getUtils().getColor("&1"));
        rank.setPrefix(Core.getCore().getUtils().getColor("&7 Rank: "));
        rank.setSuffix(Core.getCore().getUtils().getColor("&f" + user.getRank().getName()));
        obj.getScore(Core.getCore().getUtils().getColor("&1")).setScore(8);

        /* Balance */
        Team balance = board.registerNewTeam("balance");
        balance.addEntry(Core.getCore().getUtils().getColor("&2"));
        balance.setPrefix(Core.getCore().getUtils().getColor("&7 Balance: "));
        balance.setSuffix(Core.getCore().getUtils().getColor("&f$" + user.getBalance()));
        obj.getScore(Core.getCore().getUtils().getColor("&2")).setScore(7);

        /* Tag */
        Team tag = board.registerNewTeam("tag");
        tag.addEntry(Core.getCore().getUtils().getColor("&3"));
        tag.setPrefix(Core.getCore().getUtils().getColor("&7 Tag: &f"));
        tag.setSuffix(Core.getCore().getUtils().getColor("&f" + "None"));
        obj.getScore(Core.getCore().getUtils().getColor("&3")).setScore(6);


        /* Blank */
        Score blank = obj.getScore("  ");
        blank.setScore(5);


        /* Island */
        Score islandInfo = obj.getScore(Core.getCore().getUtils().getColor("&8» &e&lIsland Info:"));
        islandInfo.setScore(4);

        /* Level */
        Team level = board.registerNewTeam("level");
        level.addEntry(Core.getCore().getUtils().getColor("&4"));
        level.setPrefix(Core.getCore().getUtils().getColor("&7 Level: "));
        level.setSuffix(Core.getCore().getUtils().getColor("&f" + (island == null ? 0 : island.getLastLevel())));
        obj.getScore(Core.getCore().getUtils().getColor("&4")).setScore(3);

        /* Members */
        Team members = board.registerNewTeam("members");
        members.addEntry(Core.getCore().getUtils().getColor("&5"));
        members.setPrefix(Core.getCore().getUtils().getColor("&7 Members: "));
        members.setSuffix(Core.getCore().getUtils().getColor("&f" + (island == null ? 0 : island.getMembers().size()) + "&7/&f" + (island == null ? 0 : island.getMembersUpgrade().getMembers())));
        obj.getScore(Core.getCore().getUtils().getColor("&5")).setScore(2);

        /* Footer */
        Score footer = obj.getScore(Core.getCore().getUtils().getColor("&8&m--------------------&r"));
        footer.setScore(1);

        user.setScoreboard(board);
    }

    public void updateRank(User user) {
        Scoreboard board = user.getScoreboard();
        board.getTeam("rank").setSuffix(Core.getCore().getUtils().getColor("&f" + user.getRank().getName()));
    }
    public void updateBalance(User user) {
        Scoreboard board = user.getScoreboard();
        board.getTeam("balance").setSuffix(Core.getCore().getUtils().getColor("&f$" + user.getBalance()));
    }
    public void updateTag(User user) {
        Scoreboard board = user.getScoreboard();
        board.getTeam("tag").setSuffix(Core.getCore().getUtils().getColor("&f" + "None"));
    }
    public void updateLevel(User user) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        Scoreboard board = user.getScoreboard();
        board.getTeam("level").setSuffix(Core.getCore().getUtils().getColor("&f" + (island == null ? 0 : island.getLastLevel())));
    }
    public void updateMembers(User user) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        Scoreboard board = user.getScoreboard();
        board.getTeam("members").setSuffix(Core.getCore().getUtils().getColor("&f" + (island == null ? 0 : island.getMembers().size()) + "&7/&f" + (island == null ? 0 : island.getMembersUpgrade().getMembers())));
    }
}
