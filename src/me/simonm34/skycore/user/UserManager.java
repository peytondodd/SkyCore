package me.simonm34.skycore.user;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.punishments.PunishType;
import me.simonm34.skycore.punishments.Punishment;
import me.simonm34.skycore.rank.Rank;
import me.simonm34.skycore.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class UserManager {
    private Set<CoreUser> users = new HashSet<>();

    public CoreUser getUser(UUID uuid) {
        if (uuid == null)
            return null;
        for (CoreUser user : users)
            if (user.getUUID() == uuid)
                return user;
        return null;
    }
    public CoreUser getUserByName(String string) {
        Player player = Bukkit.getServer().getPlayer(string);
        if (player == null)
            return null;
        return getUser(player.getUniqueId());
    }
    public Set<CoreUser> getUsers() {
        return users;
    }

    public void addUser(CoreUser coreUser) {
        users.add(coreUser);
    }
    public void removeUser(CoreUser coreUser) {
        users.remove(coreUser);
    }
    public void loadUser(Player player) {
        FileConfiguration data = Core.getCore().getUserFile().setupUser(player.getUniqueId());

        Rank rank = Core.getCore().getRankManager().getRank(data.getString("ranks.rank"));
        Rank subrank = Core.getCore().getRankManager().getRank(data.getString("ranks.subrank"));
        if (rank == null)
            rank = Core.getCore().getRankManager().getDefaultRank();

        if (data.getString("data.nickname").equalsIgnoreCase("None"))
            data.set("data.nickname", player.getName());
        player.setDisplayName(data.getString("data.nickname"));

        Double balance = data.getDouble("data.balance");

        boolean canFly = data.getBoolean("info.is flying");
        boolean godMode = data.getBoolean("info.is god");

        List<Warp> homes = new ArrayList<>();
        if (data.getConfigurationSection("homes") != null) {
            for (String name : data.getConfigurationSection("homes").getKeys(false)) {
                Double x = data.getDouble("warps." + name + ".x");
                Double y = data.getDouble("warps." + name + ".y");
                Double z = data.getDouble("warps." + name + ".z");
                Float yaw = (float) data.getDouble("warps." + name + ".yaw");
                Float pitch = (float) data.getDouble("warps." + name + ".pitch");
                World world = Bukkit.getServer().getWorld(data.getString("warps." + name + ".world"));
                Material material = Material.BOOK;

                if (x == null || y == null || z == null || yaw == null || pitch == null || world == null)
                    continue;

                // TODO
                homes.add(new Warp(name, new Location(world, x, y, z, yaw, pitch), material));
            }
        }

        List<Punishment> punishments = new ArrayList<>();
        if (data.getConfigurationSection("punishments") != null) {
            for (String string : data.getConfigurationSection("punishments").getKeys(false)) {
                if (data.getConfigurationSection("punishments." + string) != null) {
                    for (String string1 : data.getConfigurationSection("punishments." + string).getKeys(false)) {
                        PunishType punishType = Core.getCore().getUtils().parsePunishType(string);
                        String reason = data.getString("punishments." + string + "." + string1 + ".reason");
                        String by = data.getString("punishments." + string + "." + string1 + ".by");
                        Long expire = data.getLong("punishments." + string + "." + string1 + ".expire");
                        boolean active = data.getBoolean("punishments." + string + "." + string1 + ".active");

                        if (punishType == null || reason == null || by == null || expire == null)
                            continue;

                        punishments.add(new Punishment(punishType, reason, by, active, expire));
                    }
                }
            }
        }

        CoreUser user = new CoreUser(player,
                rank,
                subrank,
                balance,
                homes,
                punishments,
                canFly,
                godMode);
        addUser(user);
        user.loadPerms();
    }
    public void saveUser(Player player) {
        CoreUser user = getUser(player.getUniqueId());

        FileConfiguration data = Core.getCore().getUserFile().setupUser(player.getUniqueId());
        data.set("ranks.rank", user.getRank().getName());
        data.set("ranks.subrank", user.getSubrank() == null ? "None" : user.getSubrank().getName());

        data.set("data.nickname", user.getNickname());
        data.set("data.balance", user.getBalance());

        data.set("info.is flying", user.canFly());
        data.set("info.is god", user.isGodMode());

        for (Warp warp : user.getHomes()) {
            data.set("warps." + warp.getName() + ".x", warp.getLocation().getX());
            data.set("warps." + warp.getName() + ".y", warp.getLocation().getY());
            data.set("warps." + warp.getName() + ".z", warp.getLocation().getZ());
            data.set("warps." + warp.getName() + ".yaw", warp.getLocation().getYaw());
            data.set("warps." + warp.getName() + ".pitch", warp.getLocation().getPitch());
            data.set("warps." + warp.getName() + ".world", warp.getLocation().getWorld().getName());
        }

        for (Punishment punishment : user.getPunishments()) {
            int size = data.getConfigurationSection("punishments." + punishment.getPunishType().name()) == null ? 1 : data.getConfigurationSection("punishments." + punishment.getPunishType().name()).getKeys(false).size();
            data.set("punishments." + punishment.getPunishType().name() + "." + size + ".reason", punishment.getReason());
            data.set("punishments." + punishment.getPunishType().name() + "." + size + ".by", punishment.getBy());
            data.set("punishments." + punishment.getPunishType().name() + "." + size + ".expire", punishment.getExpire() == null ? "Never" : punishment.getExpire());
            data.set("punishments." + punishment.getPunishType().name() + "." + size + ".active", punishment.isActive());
        }

        user.unloadPerms();
        Core.getCore().getUserFile().saveUser();
        removeUser(user);
    }
}
