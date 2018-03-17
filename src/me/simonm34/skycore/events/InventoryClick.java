package me.simonm34.skycore.events;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skyblock.upgrades.Generator;
import me.simonm34.skyblock.upgrades.Members;
import me.simonm34.skyblock.upgrades.Size;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryClick implements Listener {
    public InventoryClick() {
        Bukkit.getServer().getPluginManager().registerEvent(InventoryClickEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            InventoryClickEvent e = (InventoryClickEvent) event;
            User user = Core.getCore().getUserManager().getUser(e.getWhoClicked().getUniqueId());
            Inventory inv = e.getClickedInventory();
            ItemStack item = e.getCurrentItem();

            if (item == null) return;
            if (!item.hasItemMeta()) return;
            if (!item.getItemMeta().hasDisplayName()) return;

            if (isWarpMenu(user, inv, item, e))
                return;
            if (isHomesMenu(user, inv, item, e))
                return;
            if (isShopsMenu(user, inv, item, e))
                return;
            if (isIslandUpgradesMenu(user, inv, item, e))
                return;
            if (isNameColorMenu(user, inv, item, e))
                return;
        }, Core.getCore());
    }

    private boolean isWarpMenu(User user, Inventory inv, ItemStack item, InventoryClickEvent e) {
        if (inv.getName().equals(Core.getCore().getUtils().getColor("&6&lSkyTreasure &8» &eWarps"))) {
            e.setCancelled(true);
            user.closeInv();

            Warp warp = Core.getCore().getWarpManager().getWarp(ChatColor.stripColor(item.getItemMeta().getDisplayName()));
            if (warp == null)
                return true;

            if (!user.hasPerm("skycore.teleport.cooldown.bypass")) {
                user.setTeleporting(true);
                user.sendMsg("&7You will be teleported in &e3 &7seconds!");

                new BukkitRunnable() {
                    public void run() {
                        if (user.isTeleporting()) {
                            user.teleport(warp.getLocation());
                            user.sendMsg("&7You were warped to &e" + warp.getName() + "&7!");
                            user.setTeleporting(false);
                        }
                    }
                }.runTaskLater(Core.getCore(), 60);
                return true;
            }
            user.teleport(warp.getLocation());
            user.sendMsg("&7You were warped to &e" + warp.getName() + "&7!");
            return true;
        }
        if (inv.getName().equals(Core.getCore().getUtils().getColor("&6&lSkyTreasure &8» &eDelete Warp"))) {
            e.setCancelled(true);
            user.closeInv();

            Warp warp = Core.getCore().getWarpManager().getWarp(ChatColor.stripColor(item.getItemMeta().getDisplayName()));
            if (warp == null)
                return true;

            Core.getCore().getWarpManager().removeWarp(warp);
            user.sendMsg("&7Warp &e" + warp.getName() + " &7was removed!");
        }
        return false;
    }
    private boolean isHomesMenu(User user, Inventory inv, ItemStack item, InventoryClickEvent e) {
        if (inv.getName().equals(Core.getCore().getUtils().getColor("&6&lSkyTreasure &8» &eHomes"))) {
            e.setCancelled(true);
            user.closeInv();

            Warp warp = user.getHome(ChatColor.stripColor(item.getItemMeta().getDisplayName()));
            if (warp == null)
                return true;

            if (!user.hasPerm("skycore.teleport.cooldown.bypass")) {
                user.setTeleporting(true);
                user.sendMsg("&7You will be teleported in &e3 &7seconds!");

                new BukkitRunnable() {
                    public void run() {
                        if (user.isTeleporting()) {
                            user.teleport(warp.getLocation());
                            user.sendMsg("&7You were warped to &e" + warp.getName() + "&7!");
                            user.setTeleporting(false);
                        }
                    }
                }.runTaskLater(Core.getCore(), 60);
                return true;
            }
            user.teleport(warp.getLocation());
            user.sendMsg("&7You were warped to &e" + warp.getName() + "&7!");
            return true;
        }
        if (inv.getName().equals(Core.getCore().getUtils().getColor("&6&lSkyTreasure &8» &eDelete Home"))) {
            e.setCancelled(true);
            user.closeInv();

            Warp warp = Core.getCore().getWarpManager().getWarp(ChatColor.stripColor(item.getItemMeta().getDisplayName()));
            if (warp == null)
                return true;

            Core.getCore().getWarpManager().removeWarp(warp);
            user.sendMsg("&7Home &e" + warp.getName() + " &7was removed!");
        }
        return false;
    }
    private boolean isShopsMenu(User user, Inventory inv, ItemStack item, InventoryClickEvent e) {
        if (inv.getName().equals(Core.getCore().getUtils().getColor("&6&lSkyTreasure &8» &eShop"))) {
            e.setCancelled(true);

            switch (ChatColor.stripColor(item.getItemMeta().getDisplayName())) {
                case "Blocks":
                    user.openInv(Core.getCore().getShop().getBlocks());
                    user.sendMsg("&7Opened the &eBlocks &7Shop!");
                    return true;
                case "Foods":
                    user.openInv(Core.getCore().getShop().getFoods());
                    user.sendMsg("&7Opened the &eFoods &7Shop!");
                    return true;
                case "Minerals":
                    user.openInv(Core.getCore().getShop().getMinerals());
                    user.sendMsg("&7Opened the &eMinerals &7Shop!");
                    return true;
                case "Redstone":
                    user.openInv(Core.getCore().getShop().getRedstone());
                    user.sendMsg("&7Opened the &eRedstone &7Shop!");
                    return true;
                case "Spawners":
                    user.openInv(Core.getCore().getShop().getSpawners());
                    user.sendMsg("&7Opened the &eSpawners &7Shop!");
                    return true;
                case "Farming":
                    user.openInv(Core.getCore().getShop().getFarming());
                    user.sendMsg("&7Opened the &eFarming &7Shop!");
                    return true;
                case "Dyes":
                    user.openInv(Core.getCore().getShop().getDyes());
                    user.sendMsg("&7Opened the &eDyes &7Shop!");
                    return true;
                case "Miscellaneous":
                    user.openInv(Core.getCore().getShop().getMiscellaneous());
                    user.sendMsg("&7Opened the &eMiscellaneous &7Shop!");
                    return true;
                case "Mob Drops":
                    user.openInv(Core.getCore().getShop().getDrops());
                    user.sendMsg("&7Opened the &eMob Drops &7Shop!");
                    return true;
            }
            return true;
        }
        return false;
    }
    private boolean isIslandUpgradesMenu(User user, Inventory inv, ItemStack item, InventoryClickEvent e) {
        if (inv.getName().equals(Core.getCore().getUtils().getColor("&6&lSkyTreasures &8» &eUpgrades"))) {
            e.setCancelled(true);

            Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
            if (island == null) {
                user.sendMsg("&cYou do not yet have an island!");
                user.closeInv();
                return true;
            }
            switch (ChatColor.stripColor(item.getItemMeta().getDisplayName())) {
                case "Maxed Size!":
                case "Maxed Members!":
                case "Maxed Generator!":
                    user.closeInv();
                    return true;
                case "Size Upgrades":
                    Size size = island.getSizeUpgrade();
                    /*if (user.getBalance() < size.getPrice()) {
                        user.sendMsg("&cYou do not have enough money to upgrade!");
                        return true;
                    }*/
                    island.setSizesUpgrade(size.getLevel() + 1);
                    user.sendMsg("&7You upgraded your &eisland size &7to level &e" + island.getSizeUpgrade().getLevel() + "&7!");
                    return true;
                case "Members Upgrade":
                    Members members = island.getMembersUpgrade();
                    /*if (user.getBalance() < members.getPrice()) {
                        user.sendMsg("&cYou do not have enough money to upgrade!");
                        return true;
                    }*/
                    island.setSizesUpgrade(members.getLevel() + 1);
                    user.sendMsg("&7You upgraded &emax members &7on your island to level &e" + island.getMembersUpgrade().getLevel() + "&7!");
                    return true;
                case "Generator Upgrade":
                    Generator generator = island.getGeneratoUpgrader();
                    /*if (user.getBalance() < generator.getPrice()) {
                        user.sendMsg("&cYou do not have enough money to upgrade!");
                        return true;
                    }*/
                    island.setSizesUpgrade(generator.getLevel() + 1);
                    user.sendMsg("&7You upgraded your islands &egenerator &7to level &e" + island.getGeneratoUpgrader().getLevel() + "&7!");
                    return true;
            }
            return true;
        }
        return false;
    }
    private boolean isNameColorMenu(User user, Inventory inv, ItemStack item, InventoryClickEvent e) {
        if (inv.getName().equals(Core.getCore().getUtils().getColor("&6&lSkyTreasures &8» &eName Color"))) {
            e.setCancelled(true);
            user.closeInv();

            String color = ChatColor.stripColor(item.getItemMeta().getDisplayName());
            switch (color) {
                /*case "Custom":
                    user.sendMsg("&7Opened the &eCustom Name Color &7menu!");
                    return true;
                    /*/
                case "Reset":
                    user.setNickname(user.getName());
                    user.sendMsg("&7Your nickname was reset!");
                    return true;
            }
            ChatColor chatColor = ChatColor.valueOf(color.replace(" ", "_").toUpperCase());
            user.setNickname(chatColor + user.getName());
            user.sendMsg("&7Your nickname was set to &e" + user.getNickname() + "&7!");
            return true;
        }
        return false;
    }
}
