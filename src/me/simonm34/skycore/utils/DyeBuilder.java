package me.simonm34.skycore.utils;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DyeBuilder {
    private String name;
    private DyeColor color;

    public DyeBuilder setName(String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);;
        return this;
    }
    public DyeBuilder setColor(DyeColor color) {
        this.color = color;
        return this;
    }
    public ItemStack build() {
        ItemStack item = new ItemStack(Material.INK_SACK, 1, (short) color.getDyeData());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}