package me.simonm34.skycore.rank;

import me.simonm34.skycore.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class RankManager {
    private Set<Rank> ranks;

    public void loadRanks() {
        ranks = new HashSet<>();

        FileConfiguration data = Core.getCore().getFileManager().setupRanks();
        for (String name : data.getConfigurationSection("ranks").getKeys(false)) {
            boolean isDefault = (data.get("ranks." + name + ".default") != null &&
                    data.getBoolean("ranks." + name + ".default"));
            String prefix = (data.get("ranks." + name + ".prefix") == null ? "" :
                    data.getString("ranks." + name + ".prefix"));
            String chatColor = (data.get("ranks." + name + ".chatcolor") == null ? "" :
                    ChatColor.translateAlternateColorCodes('&', data.getString("ranks." + name + ".chatcolor")));
            List<String> permissions = (data.get("ranks." + name + ".permissions") == null ? new ArrayList<>() :
                    data.getStringList("ranks." + name + ".permissions"));

            Rank rank = new Rank(isDefault, name, prefix, chatColor, permissions);
            ranks.add(rank);
        }

        if (getDefaultRank() == null) {
            Core.getCore().getLogger().log(Level.SEVERE, "Could not find default rank. Disabling!");
            Bukkit.getPluginManager().disablePlugin(Core.getCore());
            return;
        }

        for (String name : data.getConfigurationSection("ranks").getKeys(false)) {
            Rank rank1 = getRank(name);
            if (rank1 != null) {
                List<Rank> children = new ArrayList<>();
                if (data.get("ranks." + name + ".children") != null) {
                    for (String string : data.getStringList("ranks." + name + ".children")) {
                        Rank rank = getRank(string);
                        if (rank != null)
                            children.add(rank);
                    }
                }
                rank1.setChildren(children);
            }
        }

        Core.getCore().getLogger().log(Level.INFO, "Successfully loaded " + ranks.size() + " ranks!");
    }

    public Rank getRank(String string) {
        for (Rank rank : ranks)
            if (rank.getName().equalsIgnoreCase(string))
                return rank;
        return null;
    }
    public Rank getDefaultRank() {
        for (Rank rank : ranks)
            if (rank.isDefault())
                return rank;
        return null;
    }
    public Set<Rank> getRanks() {
        return ranks;
    }
}
