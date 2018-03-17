package me.simonm34.skycore.user;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.chat.ChatType;
import me.simonm34.skycore.punishments.Punishment;
import me.simonm34.skycore.rank.Rank;
import me.simonm34.skycore.warp.Warp;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public class CoreUser implements User {
    private Player player;
    private PermissionAttachment permissionAttachment;
    private Rank rank;
    private Rank subrank;
    private Double balance;
    private List<Warp> homes;
    private List<Punishment> punishments;
    private UUID reply;
    private UUID teleportRequest;
    private ChatType chatType;
    private UUID spectating;
    private UUID spectator;
    private boolean canFly = false;
    private boolean godMode;
    private boolean invsee;
    private boolean teleporting;
    private Long chatCooldown;

    public CoreUser(Player player, Rank rank, Rank subrank, Double balance, List<Warp> homes, List<Punishment> punishments, boolean canFly, boolean godMode) {
        this.player = player;
        setRank(rank);
        setSubrank(subrank);
        setBalance(balance);
        setHomes(homes);
        setPunishments(punishments);
        setChat(ChatType.PUBLIC);
        setCanFly(canFly);
        setGodMode(godMode);
        setIsInvsee(false);
        setTeleporting(false);
    }

    public Player getPlayer() {
        return player;
    }
    public String getName() {
        return player.getName();
    }
    public UUID getUUID() {
        return player.getUniqueId();
    }
    public String getNickname() {
        return player.getDisplayName();
    }
    public boolean hasPerm(String string) {
        return player.hasPermission(string);
    }
    public PlayerInventory getInv() {
        return player.getInventory();
    }
    public Inventory getEnderchest() {
        return player.getEnderChest();
    }
    public Location getLoc() {
        return player.getLocation();
    }
    public boolean isFlying() {
        return player.isFlying();
    }
    public List<ItemStack> getItems() {
        List<ItemStack> items = Arrays.asList(player.getInventory().getContents());
        items.addAll(Arrays.asList(player.getInventory().getArmorContents()));
        return items;
    }
    public Scoreboard getScoreboard() {
        return player.getScoreboard();
    }
    public Rank getRank() {
        return rank;
    }
    public Rank getSubrank() {
        return subrank;
    }
    public Double getBalance() {
        return balance;
    }
    public Warp getHome(String string) {
        for (Warp warp : homes)
            if (warp.getName().equalsIgnoreCase(string))
                return warp;
        return null;
    }
    public List<Warp> getHomes() {
        return homes;
    }
    public List<Punishment> getPunishments() {
        return punishments;
    }
    public UUID getReply() {
        return reply;
    }
    public UUID getTeleportRequest() {
        return teleportRequest;
    }
    public ChatType getChat() {
        return chatType;
    }
    public UUID getSpectating() {
        return spectating;
    }
    public UUID getSpectator() {
        return spectator;
    }
    public boolean canFly() {
        return canFly;
    }
    public boolean isGodMode() {
        return godMode;
    }
    public boolean isInvsee() {
        return invsee;
    }
    public boolean isTeleporting() {
        return teleporting;
    }
    public Long getChatCooldown() {
        return chatCooldown;
    }

    public void setNickname(String string) {
        player.setDisplayName(string);
    }
    public void setTablistName(String string) {
        player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', string));
    }
    public void setGamemode(GameMode gamemode) {
        player.setGameMode(gamemode);
    }
    public void teleport(Location location) {
        player.teleport(location);
    }
    public void openInv(Inventory inventory) {
        player.openInventory(inventory);
    }
    public void openWorkbench() {
        player.openWorkbench(null, true);
    }
    public void clearInv() {
        player.getInventory().clear();
    }
    public void closeInv() {
        player.closeInventory();
    }
    public void setScoreboard(Scoreboard scoreboard) {
        player.setScoreboard(scoreboard);
    }
    public void setFlySpeed() {
        //TODO
    }
    public void setWalkSpeed() {
        //TODO
    }
    public void setHealth(Double health) {
        player.setHealth(health);
    }
    public void hideUser(User user) {
        player.hidePlayer(Core.getCore(), user.getPlayer());
    }
    public void showUser(User user) {
        player.showPlayer(Core.getCore(), user.getPlayer());
    }
    public void loadPerms() {
        PermissionAttachment permissionAttachment = player.addAttachment(Core.getCore());
        if (rank != null) {
            for (String perm : rank.getPermissions())
                permissionAttachment.setPermission(perm, true);
            for (Rank rank1 : rank.getChildren())
                for (String perm : rank1.getPermissions())
                    permissionAttachment.setPermission(perm, true);
        }
        if (subrank != null) {
            for (String perm : subrank.getPermissions())
                permissionAttachment.setPermission(perm, true);
            for (Rank rank1 : subrank.getChildren())
                for (String perm : rank1.getPermissions())
                    permissionAttachment.setPermission(perm, true);
        }
        this.permissionAttachment = permissionAttachment;
    }
    public void unloadPerms() {
        player.removeAttachment(permissionAttachment);
    }
    public void setRank(Rank rank) {
        this.rank = rank;
    }
    public void setSubrank(Rank subrank) {
        this.subrank = subrank;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public void setHomes(List<Warp> homes) {
        this.homes = homes;
    }
    public void addHome(Warp warp) {
        homes.add(warp);
    }
    public void removeHome(Warp warp) {
        homes.remove(warp);
    }
    public void setPunishments(List<Punishment> punishments) {
        this.punishments = punishments;
    }
    public void addPunishment(Punishment punishment) {
        punishments.add(punishment);
    }
    public void removePunishment(Punishment punishment) {
        punishments.remove(punishment);
    }
    public void setReply(UUID uuid) {
        this.reply = uuid;
    }
    public void setTeleportRequest(UUID teleportRequest) {
        this.teleportRequest = teleportRequest;
    }
    public void setChat(ChatType chatType) {
        this.chatType = chatType;
    }
    public void setSpectating(UUID spectating) {
        this.spectating = spectating;
    }
    public void setSpectator(UUID spectator) {
        this.spectator = spectator;
    }
    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
        player.setAllowFlight(canFly);
        player.setFlying(canFly);
    }
    public void setGodMode(boolean godMode) {
        this.godMode = godMode;
    }
    public void setIsInvsee(boolean invsee) {
        this.invsee = invsee;
    }
    public void setTeleporting(boolean teleporting) {
        this.teleporting = teleporting;
    }
    public void setChatCooldown(Long chatCooldown) {
        this.chatCooldown = chatCooldown;
    }

    public void sendMsg(String string) {
        sendRawMsg("&6&lSkyTreasure &8» " + string);
    }
    public void sendMsg(BaseComponent[] baseComponents) {
        player.spigot().sendMessage(baseComponents);
    }
    public void sendRawMsg(String string) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
    }
    public void sendUsage(String cmd, String usage) {
        sendMsg("&7Usage: /" + cmd + " " + usage);
    }
}
