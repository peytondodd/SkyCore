package me.simonm34.skycore.kit;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class KitManager {
    private List<Kit> kits;

    public void loadKits() {
        kits = new ArrayList<>();

        FileConfiguration data = Core.getCore().getFileManager().setupKits();
        for (String string : data.getConfigurationSection("kits").getKeys(false)) {
            String name = Core.getCore().getUtils().formatString(' ', string);
            Long cooldown = data.getLong("kits." + string + ".cooldown");
            boolean shown = data.getBoolean("kits." + string + ".shown");
            List<ItemStack> items = new ArrayList<>();
            for (String string1 : data.getConfigurationSection("kits." + string + ".items").getKeys(false)) {
                Material material = Core.getCore().getUtils().parseMaterial(string1);
                if (material == null) continue;

                ItemBuilder item = new ItemBuilder();
                if (data.get("kits." + string + ".items." + string1 + ".amount") == null)
                    item.setAmount(data.getInt("kits." + string + ".items." + string1 + ".amount"));
                if (data.get("kits." + string + ".items." + string1 + ".name") == null)
                    item.setName(data.getString("kits." + string + ".items." + string1 + ".name"));
                if (data.get("kits." + string + ".items." + string1 + ".lore") == null)
                    for (String string3 : data.getStringList("kits." + string + ".items." + string1 + ".lore"))
                        item.addLore(string3);
                if (data.get("kits." + string + ".items." + string1 + ".enchantments") == null)
                    for (String string3 : data.getConfigurationSection("kits." + string + ".items." + string1 + ".enchantments").getKeys(false))
                        if (Enchantment.getByName(string3.toUpperCase()) != null)
                            item.addEnchantment(Enchantment.getByName(string3.toUpperCase()), data.getInt("kits." + string + ".items." + string1 + ".enchantments." + string3));

                items.add(item.build());
            }
            kits.add(new Kit(name, items, cooldown, shown));
        }
    }

    public Kit getKit(String name) {
        for (Kit kit : kits)
            if (kit.getName().equalsIgnoreCase(name))
                return kit;
        return null;
    }
}
