package me.simonm34.skycore.warp;

import org.bukkit.Location;
import org.bukkit.Material;

public class Warp {
    private String name;
    private Location location;
    private Material material;

    public Warp(String name, Location location, Material material) {
        this.name = name;
        this.location = location;
        this.material = material;
    }

    public String getName() {
        return name;
    }
    public Location getLocation() {
        return location;
    }
    public Material getItem() {
        return material;
    }
}
