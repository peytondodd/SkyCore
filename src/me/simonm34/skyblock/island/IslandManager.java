package me.simonm34.skyblock.island;

import me.simonm34.skyblock.generator.EmptyGenerator;
import me.simonm34.skycore.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.*;
import java.util.logging.Level;

public class IslandManager {
    private World world;
    private List<Island> islands;

    public void loadIslands() {
        islands = new ArrayList<>();

        File directory = new File(Core.getCore().getDataFolder(), "islands");
        for (File file : directory.listFiles()) {
            UUID islandID = UUID.fromString(file.getName().replace(".yml", ""));
            FileConfiguration data = Core.getCore().getSkyblock().getFileManager().setupIsland(islandID);

            UUID owner = UUID.fromString(data.getString("players.owner"));
            Set<UUID> members = Core.getCore().getSkyblock().getUtils().StringListToUUIDSet(data.getStringList("players.members"));
            Set<UUID> cooped = Core.getCore().getSkyblock().getUtils().StringListToUUIDSet(data.getStringList("players.cooped"));
            Set<UUID> banned = Core.getCore().getSkyblock().getUtils().StringListToUUIDSet(data.getStringList("players.banned"));

            Location center = Core.getCore().getSkyblock().getUtils().StringListToLocation(data.getStringList("locations.center"));
            Location spawnPoint = Core.getCore().getSkyblock().getUtils().StringListToLocation(data.getStringList("locations.spawn point"));
            Location welcomePoint = Core.getCore().getSkyblock().getUtils().StringListToLocation(data.getStringList("locations.welcome point"));
            int minX = data.getInt("locations.minimum x");
            int maxX = data.getInt("locations.maximum x");
            int minZ = data.getInt("locations.minimum z");
            int maxZ = data.getInt("locations.maximum z");

            int sizeUpgrade = data.getInt("upgrades.size");
            int membersUpgrade = data.getInt("upgrades.members");
            int generatorUpgrade = data.getInt("upgrades.generator");

            long lastLevel = data.getLong("data.last level");
            boolean locked = data.getBoolean("data.locked");

            Island island = new Island(islandID,
                    owner,
                    members,
                    cooped,
                    banned,
                    center,
                    spawnPoint,
                    welcomePoint,
                    sizeUpgrade,
                    membersUpgrade,
                    generatorUpgrade,
                    lastLevel,
                    locked,
                    minX,
                    minZ,
                    maxX,
                    maxZ);
            islands.add(island);
        }
        Core.getCore().getLogger().log(Level.INFO, "Successfully loaded " + islands.size() + " islands!");
    }
    public void saveIslands() {
        File directory = new File(Core.getCore().getDataFolder(), "islands");
        for (File file : directory.listFiles())
            file.delete();

        for (Island island : islands) {
            FileConfiguration data = Core.getCore().getSkyblock().getFileManager().setupIsland(island.getIslandID());
            data.set("players.owner", island.getOwner().toString());
            data.set("players.members", new ArrayList<>(island.getMembers()));
            data.set("players.cooped", new ArrayList<>(island.getCooped()));
            data.set("players.banned", new ArrayList<>(island.getBanned()));

            data.set("locations.center", Core.getCore().getSkyblock().getUtils().LocationToArray(island.getCenter()));
            data.set("locations.spawn point", Core.getCore().getSkyblock().getUtils().LocationToArray(island.getSpawnPoint()));
            data.set("locations.welcome point", Core.getCore().getSkyblock().getUtils().LocationToArray(island.getWelcomePoint()));
            data.set("locations.minimum x", island.getMinX());
            data.set("locations.maximum x", island.getMaxX());
            data.set("locations.minimum z", island.getMinZ());
            data.set("locations.maximum z", island.getMaxZ());

            data.set("upgrades.size", island.getSizeUpgrade().getLevel());
            data.set("upgrades.members", island.getMembersUpgrade().getLevel());
            data.set("upgrades.generator", island.getGeneratoUpgrader().getLevel());

            data.set("data.last level", island.getLastLevel());
            data.set("data.locked", island.isLocked());
            Core.getCore().getSkyblock().getFileManager().saveIsland();
        }
    }

    public World getWorld() {
        return world;
    }
    public Island getIsland(UUID uuid) {
        for (Island island : islands)
            if (island.getAllMembers().contains(uuid))
                return island;
        return null;
    }
    public Island getIslandByID(UUID islandID) {
        for (Island island : islands)
            if (island.getIslandID() == islandID)
                return island;
        return null;
    }
    public Island getIslandAtLocation(Location loc) {
        for (Island island : islands)
            if (loc.getBlockX() >= island.getMinProtectedX() && loc.getBlockX() <= island.getMaxProtectedX())
                if (loc.getBlockZ() >= island.getMinProtectedX() && loc.getBlockZ() <= island.getMaxProtectedZ())
                    return island;
        return null;
    }
    public Location getNextLocation() {
        if (islands.isEmpty()) {
            return new Location(getWorld(), 0, 115, 0);
        }
        for (Island island : islands) {
            Location loc = new Location(getWorld(), island.getCenter().getBlockX() + 400, 115, island.getCenter().getBlockZ() + 400);
            if (!IsIslandCenterAtLoc(loc))
                return loc;

            loc = new Location(getWorld(), island.getCenter().getBlockX() + 400, 115, island.getCenter().getBlockZ() - 400);
            if (!IsIslandCenterAtLoc(loc))
                return loc;

            loc = new Location(getWorld(), island.getCenter().getBlockX() - 400, 115, island.getCenter().getBlockZ() + 400);
            if (!IsIslandCenterAtLoc(loc))
                return loc;

            loc = new Location(getWorld(), island.getCenter().getBlockX() - 400, 115, island.getCenter().getBlockZ() - 400);
            if (!IsIslandCenterAtLoc(loc))
                return loc;
        }
        return null;
    }
    public boolean IsIslandCenterAtLoc(Location loc) {
        for (Island island : islands)
            if (island.getCenter().getBlockX() == loc.getBlockX() && island.getCenter().getBlockZ() == loc.getBlockZ())
                return true;
        return false;
    }

    public void setupWorld() {
        if (Bukkit.getServer().getWorld("Skyblock") == null) {
            WorldCreator worldCreator = new WorldCreator("Skyblock");
            worldCreator.generateStructures(false);
            worldCreator.generator(new EmptyGenerator());
            Bukkit.getServer().createWorld(worldCreator);
        }
        this.world = Bukkit.getServer().getWorld("Skyblock");
    }
    public void addIsland(Island island) {
        islands.add(island);
    }
    public void removeIsland(Island island) {
        islands.remove(island);
    }
}
