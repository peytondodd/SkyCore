package me.simonm34.skycore.shop.inventorys;

import me.simonm34.skycore.utils.InventoryBuilder;
import me.simonm34.skycore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MainShop {
    public Inventory build() {
        ItemStack blocks = new ItemBuilder().setName("&bBlocks").setMaterial(Material.GRASS).build();
        ItemStack food = new ItemBuilder().setName("&bFoods").setMaterial(Material.COOKED_RABBIT).build();
        ItemStack minerals = new ItemBuilder().setName("&bMinerals").setMaterial(Material.DIAMOND).build();
        ItemStack redstone = new ItemBuilder().setName("&bRedstone").setMaterial(Material.HOPPER).build();
        ItemStack spawners = new ItemBuilder().setName("&bSpawners").setMaterial(Material.MOB_SPAWNER).build();
        ItemStack farming = new ItemBuilder().setName("&bFarming").setMaterial(Material.CACTUS).build();
        ItemStack dyes = new ItemBuilder().setName("&bDyes").setMaterial(Material.INK_SACK).build();
        ItemStack miscellaneous = new ItemBuilder().setName("&bMiscellaneous").setMaterial(Material.SADDLE).build();
        ItemStack drops = new ItemBuilder().setName("&bMob Drops").setMaterial(Material.BLAZE_ROD).build();

        Inventory inv = new InventoryBuilder()
                .setSize(27)
                .setName("&6&lSkyTreasures &8» &eShop")
                .setItem(0, blocks)
                .setItem(1, food)
                .setItem(2, minerals)
                .setItem(3, redstone)
                .setItem(4, spawners)
                .setItem(5, farming)
                .setItem(6, dyes)
                .setItem(7, miscellaneous)
                .setItem(8, drops)
                .build();
        return inv;
    }
}
