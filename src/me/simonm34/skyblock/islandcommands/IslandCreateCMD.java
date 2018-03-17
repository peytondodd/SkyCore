package me.simonm34.skyblock.islandcommands;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.api.events.IslandLevelChangeEvent;
import me.simonm34.skycore.api.events.IslandMemberChangeEvent;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class IslandCreateCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            Location loc = Core.getCore().getSkyblock().getIslandManager().getNextLocation();
            while (loc == null)
                loc = Core.getCore().getSkyblock().getIslandManager().getNextLocation();

            UUID islandID = UUID.randomUUID();
            while (Core.getCore().getSkyblock().getIslandManager().getIslandByID(islandID) != null)
                islandID = UUID.randomUUID();

            island = new Island(islandID, user.getUUID(), loc);
            if (!createIsland(island)) {
                user.sendMsg("&cThere was an error whilst creating your island!");
                return;
            }

            Core.getCore().getSkyblock().getIslandManager().addIsland(island);

            Location islandLoc = new Location(island.getCenter().getWorld(), island.getCenter().getX() + 500, 121.0, island.getCenter().getZ() + 500);
            user.teleport(islandLoc);
            user.sendMsg("&7You created an island!");
            Bukkit.getServer().getPluginManager().callEvent(new IslandMemberChangeEvent(island));
            Bukkit.getServer().getPluginManager().callEvent(new IslandLevelChangeEvent(island));
            return;
        }
        user.sendMsg("&cYou already have an island created!");
    }

    private boolean createIsland(Island island) {
        try {
            EditSession session = Core.getCore().getWorldEdit().getWorldEdit().getEditSessionFactory().getEditSession(
                    new BukkitWorld(Core.getCore().getSkyblock().getIslandManager().getWorld()),
                    Core.getCore().getSkyblock().getIslandManager().getWorld().getMaxHeight());

            Core.getCore().getSkyblock().getIsland().paste(session, new Vector(
                    island.getCenter().getX() - 14.5,
                    island.getCenter().getY() - 17.5,
                    island.getCenter().getZ() - 12.5),
                    false);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
