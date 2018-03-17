package me.simonm34.skycore.shop;

import me.simonm34.skycore.shop.inventorys.*;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class Shop {
    private Inventory shop = new MainShop().build();
    private Inventory blocks = new Blocks().build();
    private Inventory foods = new Foods().build();
    private Inventory minerals = new Minerals().build();
    private Inventory redstone = new Redstone().build();
    private Inventory spawners = Bukkit.getServer().createInventory(null, 27);
    private Inventory farming = new Farming().build();
    private Inventory dyes = new Dyes().build();
    private Inventory miscellaneous = new Miscellaneous().build();
    private Inventory drops = new Drops().build();

    public Inventory getShop() {
        return shop;
    }
    public Inventory getBlocks() {
        return blocks;
    }
    public Inventory getFoods() {
        return foods;
    }
    public Inventory getMinerals() {
        return minerals;
    }
    public Inventory getRedstone() {
        return redstone;
    }
    public Inventory getSpawners() {
        return spawners;
    }
    public Inventory getFarming() {
        return farming;
    }
    public Inventory getDyes() {
        return dyes;
    }
    public Inventory getMiscellaneous() {
        return miscellaneous;
    }
    public Inventory getDrops() {
        return drops;
    }
}
