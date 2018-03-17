package me.simonm34.skyblock.utils;

import me.simonm34.skycore.Core;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Utils {
    public String[] LocationToArray(Location loc) {
        return new String[]{loc.getX() + "",
                loc.getY() + "",
                loc.getZ() + "",
                loc.getYaw() + "",
                loc.getPitch() + ""};
    }
    public Set<UUID> StringListToUUIDSet(List<String> list) {
        Set<UUID> uuids = new HashSet<>();
        for (String uuid : list)
            uuids.add(UUID.fromString(uuid));
        return uuids;
    }
    public Location StringListToLocation(List<String> list) {
        return new Location(Core.getCore().getSkyblock().getIslandManager().getWorld(),
                parseDouble(list.get(0)),
                parseDouble(list.get(1)),
                parseDouble(list.get(2)),
                parseFloat(list.get(3)),
                parseFloat(list.get(4)));
    }

    public double parseDouble(String string) {
        double d;
        try {
            d = Double.parseDouble(string);
        } catch (Exception e) {
            return 0.0;
        }
        return d;
    }
    public float parseFloat(String string) {
        float f;
        try {
            f = Float.parseFloat(string);
        } catch (Exception e) {
            return 0;
        }
        return f;
    }
}
