package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.utils.DyeBuilder;
import me.simonm34.skycore.utils.InventoryBuilder;
import me.simonm34.skycore.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NameColorCMD extends Command {
    public NameColorCMD() {
        super("namecolor", "namecolour", "color", "colour");
    }
    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            user.openInv(getNameColorGUI());
            user.sendMsg("&7Opened the &eName Color &7menu!");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }

    private Inventory getNameColorGUI() {
        ItemStack dark_red = new DyeBuilder().setName(ChatColor.DARK_RED + "Dark Red").setColor(DyeColor.RED).build();
        ItemStack red = new DyeBuilder().setName(ChatColor.RED + "Red").setColor(DyeColor.PINK).build();
        ItemStack orange = new DyeBuilder().setName(ChatColor.GOLD + "Gold").setColor(DyeColor.ORANGE).build();
        ItemStack yellow = new DyeBuilder().setName(ChatColor.YELLOW + "Yellow").setColor(DyeColor.YELLOW).build();
        ItemStack dark_green = new DyeBuilder().setName(ChatColor.DARK_GREEN + "Dark Green").setColor(DyeColor.GREEN).build();
        ItemStack green = new DyeBuilder().setName(ChatColor.DARK_GREEN + "Green").setColor(DyeColor.LIME).build();
        ItemStack aqua = new DyeBuilder().setName(ChatColor.AQUA + "Aqua").setColor(DyeColor.LIGHT_BLUE).build();
        ItemStack cyan = new DyeBuilder().setName(ChatColor.DARK_AQUA + "Dark Aqua").setColor(DyeColor.CYAN).build();
        ItemStack blue = new DyeBuilder().setName(ChatColor.BLUE + "Blue").setColor(DyeColor.BLUE).build();
        ItemStack custom = new ItemBuilder().setName(ChatColor.RESET + "Custom").setMaterial(Material.PAPER).build();
        ItemStack reset = new ItemBuilder().setName(ChatColor.RESET + "Reset").setMaterial(Material.ARROW).build();
        Inventory inv = new InventoryBuilder().setName("&6&lSkyTreasures &8» &eName Color").setSize(18)
                .setItem(0, dark_red)
                .setItem(1, red)
                .setItem(2, orange)
                .setItem(3, yellow)
                .setItem(4, dark_green)
                .setItem(5, green)
                .setItem(6, aqua)
                .setItem(7, cyan)
                .setItem(8, blue)
                .setItem(9, custom)
                .setItem(17, reset)
                .build();
        return inv;
    }
}
