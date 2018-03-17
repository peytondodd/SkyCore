package me.simonm34.skycore.shop.inventorys;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.utils.InventoryBuilder;
import me.simonm34.skycore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Minerals {
    public Inventory build() {
        FileConfiguration data = Core.getCore().getFileManager().getShops();
        InventoryBuilder invBuilder = new InventoryBuilder().setSize(36).setName("&6&lSkyTreasures &8» &eMinerals Shop");
        for (String string : data.getConfigurationSection("shops.minerals.items").getKeys(false)) {
            Material material = Core.getCore().getUtils().parseMaterial(string);
            Integer quantity = data.getInt("shops.minerals.items." + string + ".quantity");
            Double buyPrice = data.getDouble("shops.minerals.items." + string + ".buy price");
            Double sellPrice = data.getDouble("shops.minerals.items." + string + ".sell price");
            Integer materialData = data.getInt("shops.minerals.items." + string + ".data");

            if (material != null) {
                ItemStack item = new ItemBuilder()
                        .setMaterial(material)
                        .setAmount(quantity)
                        .setData(materialData)
                        .setName("&b" + Core.getCore().getUtils().formatString('_', material.name()))
                        .addLore("&7Buy price: &e$" + buyPrice)
                        .addLore("&7Sell price: &e$" + sellPrice)
                        .setData(materialData)
                        .build();
                invBuilder.addItem(item);
            }
        }
        return invBuilder.build();
    }
}
