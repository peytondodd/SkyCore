package me.simonm34.skycore.warp;

import me.simonm34.skycore.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class WarpManager {
    private List<Warp> warps;

    public Warp getWarp(String string) {
        for (Warp warp : warps)
            if (warp.getName().equalsIgnoreCase(string))
                return warp;
        return null;
    }
    public List<Warp> getWarps() {
        return warps;
    }

    public void loadWarps() {
        warps = new ArrayList<>();

        FileConfiguration data = Core.getCore().getFileManager().setupWarps();
        if (data.getConfigurationSection("warps") == null) return;
        for (String name : data.getConfigurationSection("warps").getKeys(false)) {
            Double x = data.getDouble("warps." + name + ".x");
            Double y = data.getDouble("warps." + name + ".y");
            Double z = data.getDouble("warps." + name + ".z");
            Float yaw = (float) data.getDouble("warps." + name + ".yaw");
            Float pitch = (float) data.getDouble("warps." + name + ".pitch");
            World world = Bukkit.getServer().getWorld(data.getString("warps." + name + ".world"));
            Material material = Core.getCore().getUtils().parseMaterial(data.getString("warps." + name + ".item"));

            if (x == null || y == null || z == null || yaw == null || pitch == null || world == null || material == null)
                continue;

            warps.add(new Warp(name, new Location(world, x, y, z, yaw, pitch), material));
        }
    }
    public void saveWarps() {
        FileConfiguration data = Core.getCore().getFileManager().setupWarps();

        for (Warp warp : warps) {
            data.set("warps." + warp.getName() + ".x", warp.getLocation().getX());
            data.set("warps." + warp.getName() + ".y", warp.getLocation().getY());
            data.set("warps." + warp.getName() + ".z", warp.getLocation().getZ());
            data.set("warps." + warp.getName() + ".yaw", warp.getLocation().getYaw());
            data.set("warps." + warp.getName() + ".pitch", warp.getLocation().getPitch());
            data.set("warps." + warp.getName() + ".world", warp.getLocation().getWorld().getName());
            data.set("warps." + warp.getName() + ".item", warp.getItem().name().toLowerCase());
        }
        Core.getCore().getFileManager().saveWarps();
    }
    public void addWarp(Warp warp) {
        warps.add(warp);
    }
    public void removeWarp(Warp warp) {
        warps.remove(warp);
    }
}
