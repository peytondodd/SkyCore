package me.simonm34.skycore.kit;

import me.simonm34.skycore.user.User;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kit {
    private String name;
    private List<ItemStack> items;
    private long cooldown;
    private boolean show;

    public Kit(String name, List<ItemStack> items, long cooldown, boolean show) {
        this.name = name;
        this.items = items;
        this.cooldown = cooldown;
        this.show = show;
    }

    public String getName() {
        return name;
    }
    public List<ItemStack> getItems() {
        return items;
    }
    public long getCooldown() {
        return cooldown;
    }
    public boolean isShow() {
        return show;
    }

    public void equip(User user) {
        for (ItemStack item : items) {
            HashMap<Integer, ItemStack> overflow = user.getInv().addItem(item);
            if (overflow.isEmpty()) continue;
            for (Map.Entry<Integer, ItemStack> entry : overflow.entrySet()) {
                ItemStack item1 = entry.getValue();
                item1.setAmount(entry.getKey());
                user.getLoc().getWorld().dropItemNaturally(user.getLoc(), item1);
            }
        }
    }
}
