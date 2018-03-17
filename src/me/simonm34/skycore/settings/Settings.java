package me.simonm34.skycore.settings;

import me.simonm34.skycore.Core;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Settings {
    private List<String> motd;
    private List<String> swears;
    private List<String> blacklistedNames;
    private String serverMotd;

    public void setSettings() {
        FileConfiguration data = Core.getCore().getConfig();

        motd = data.getStringList("messages.welcome message");
        swears = data.getStringList("swears");
        blacklistedNames = data.getStringList("blacklisted item names");
        serverMotd = data.getString("server motd");
    }

    public List<String> getMotd() {
        return motd;
    }
    public List<String> getSwears() {
        return swears;
    }
    public List<String> getBlacklistedNames() {
        return blacklistedNames;
    }
    public String getServerMotd() {
        return serverMotd;
    }
}
