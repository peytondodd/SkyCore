package me.simonm34.skyblock.upgrades;

import org.bukkit.Material;

public class Generator {
    private int level;
    private double price;

    public Generator(int level, double price) {
        this.level = level;
        this.price = price;
    }

    public int getLevel() {
        return level;
    }
    public double getPrice() {
        return price;
    }
    public Material getMaterial() {
         switch (level) {
             case 1:
                 return Material.STONE;
             case 2:
                 return Material.STONE;
             case 3:
                 return Material.STONE;
             case 4:
                 return Material.STONE;
             case 5:
                 return Material.STONE;
         }
         return Material.STONE;
    }
}
