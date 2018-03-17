package me.simonm34.skyblock;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import me.simonm34.skyblock.commands.IslandCMD;
import me.simonm34.skyblock.events.*;
import me.simonm34.skyblock.invite.InviteManager;
import me.simonm34.skyblock.island.IslandManager;
import me.simonm34.skyblock.upgrades.UpgradesManager;
import me.simonm34.skyblock.utils.FileManager;
import me.simonm34.skyblock.utils.Utils;
import me.simonm34.skycore.Core;

import java.io.File;

public class Skyblock {
    private CuboidClipboard islandSelection;
    private UpgradesManager upgradesManager;
    private IslandManager islandManager;
    private InviteManager inviteManager;
    private FileManager fileManager;
    private Utils utils;

    public void onEnable() {
        loadDepends();
        loadCommands();
        loadEvents();
        loadSchematic();

        getIslandManager().setupWorld();
        getFileManager().setupDirectory();
        getUpgradesManager().loadUpgrades();
        getIslandManager().loadIslands();
    }
    public void onDisable() {
        getIslandManager().saveIslands();
    }

    private void loadDepends() {
        upgradesManager = new UpgradesManager();
        islandManager = new IslandManager();
        inviteManager = new InviteManager();
        fileManager = new FileManager();
        utils = new Utils();
    }
    private void loadCommands() {
        new IslandCMD();
    }
    private void loadEvents() {
        new PlayerMove();
        new BlockPlace();
        new BlockBreak();
        new BlockFromTo();
        new SignChange();
    }
    private void loadSchematic() {
        File file = new File(Core.getCore().getDataFolder(), "island.schematic");
        try {
            islandSelection = MCEditSchematicFormat.getFormat(file).load(file);
        } catch (Exception e) {}
    }

    public CuboidClipboard getIsland() {
        return islandSelection;
    }
    public UpgradesManager getUpgradesManager() {
        return upgradesManager;
    }
    public IslandManager getIslandManager() {
        return islandManager;
    }
    public InviteManager getInviteManager() {
        return inviteManager;
    }
    public FileManager getFileManager() {
        return fileManager;
    }
    public Utils getUtils() {
        return utils;
    }
}
