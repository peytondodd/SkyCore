package me.simonm34.skycore.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullBuilder {
    private String name;
    private String owner;

    public SkullBuilder setName(String string) {
        this.name = ChatColor.translateAlternateColorCodes('&', string);
        return this;
    }

    public SkullBuilder setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        if (owner != null)
            skull.setOwner(owner);
        if (name != null)
            skull.setDisplayName(name);
        item.setItemMeta(skull);
        return item;
    }
}
