package me.simonm34.skyblock.islandcommands;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skyblock.upgrades.Generator;
import me.simonm34.skyblock.upgrades.Members;
import me.simonm34.skyblock.upgrades.Size;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.utils.InventoryBuilder;
import me.simonm34.skycore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class IslandUpgradesCMD implements IslandCommand {
    public void execute(User user, String cmd, String[] args) {
        Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
        if (island == null) {
            user.sendMsg("&cYou do not yet have an island!");
            return;
        }
        ItemStack blank = new ItemBuilder()
                .setMaterial(Material.STAINED_GLASS_PANE)
                .setData(15)
                .setName(" ")
                .build();

        Size newSize = Core.getCore().getSkyblock().getUpgradesManager().getSize(island.getSizeUpgrade().getLevel() + 1);
        ItemBuilder size = new ItemBuilder().
                setMaterial(Material.GRASS)
                .setName(newSize == null ? "&cMaxed Size!" : "&eSize Upgrades");
        if (newSize != null) {
            size.addLore("&7Upgrade your island protection size");
            size.addLore("&eNew Size: &f" + newSize.getArea() + "x" + newSize.getArea());
            size.addLore("&ePrice: &f$" + newSize.getPrice());
        }

        Members newMembers = Core.getCore().getSkyblock().getUpgradesManager().getMember(island.getMembersUpgrade().getLevel() + 1);
        ItemBuilder members = new ItemBuilder()
                .setMaterial(Material.SKULL_ITEM)
                .setName(newMembers == null ? "&cMaxed Members!" : "&eMembers Upgrade");
        if (newMembers != null) {
            members.addLore("&7Upgrade your max team size!");
            members.addLore("&eNew Max Members: &f" + newMembers.getMembers());
            members.addLore("&ePrice: &f$" + newMembers.getPrice());
        }

        Generator newGenerator = Core.getCore().getSkyblock().getUpgradesManager().getGenerator(island.getGeneratoUpgrader().getLevel() + 1);
        ItemBuilder generator = new ItemBuilder()
                .setMaterial(Material.DIAMOND_ORE)
                .setName(newGenerator == null ? "&cMaxed Generator!" : "&eGenerator Upgrade");
        if (newGenerator != null) {
            generator.addLore("&7Upgrade your island generator to get ores spawning!");
            generator.addLore("&eNew Generator Level: &f" + newGenerator.getLevel());
            generator.addLore("&ePrice: &f$" + newGenerator.getPrice());
        }

        InventoryBuilder invBuilder = new InventoryBuilder().setName("&6&lSkyTreasures &8» &eUpgrades").setSize(27);
        for (int i = 0; i != 27; i++)
            invBuilder.addItem(blank);
        invBuilder.setItem(10, size.build());
        invBuilder.setItem(13, members.build());
        invBuilder.setItem(16, generator.build());
        user.openInv(invBuilder.build());
        user.sendMsg("&7Opened the &eIsland Upgrades &7menu!");
    }
}
