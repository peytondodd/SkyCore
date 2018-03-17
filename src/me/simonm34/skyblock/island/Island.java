package me.simonm34.skyblock.island;

import me.simonm34.skyblock.upgrades.Generator;
import me.simonm34.skyblock.upgrades.Members;
import me.simonm34.skyblock.upgrades.Size;
import me.simonm34.skycore.Core;
import org.bukkit.Location;
import org.bukkit.Material;

import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Island {
    private UUID islandID;
    private UUID owner;
    private Set<UUID> members;
    private Set<UUID> cooped;
    private Set<UUID> banned;
    private Location center;
    private Location spawnPoint;
    private Location welcomePoint;
    private int generatorsUpgrade;
    private int membersUpgrade;
    private int sizesUpgrade;
    private long lastLevel;
    private boolean locked;
    private int minX;
    private int minZ;
    private int maxX;
    private int maxZ;
    private boolean remove = false;

    public Island(UUID islandID, UUID owner, Location center) {
        this.islandID = islandID;
        this.owner = owner;
        this.members = new HashSet<>();
        this.cooped = new HashSet<>();
        banned = new HashSet<>();
        this.center = center;
        this.spawnPoint = center;
        this.welcomePoint = center;
        this.generatorsUpgrade = 1;
        this.membersUpgrade = 1;
        this.sizesUpgrade = 1;
        this.lastLevel = 0;
        this.locked = false;
        this.minX = center.getBlockX() - 200;
        this.minZ = center.getBlockZ() - 200;
        this.maxX = center.getBlockX() + 200;
        this.maxZ = center.getBlockZ() + 200;
    }
    public Island(UUID islandID, UUID owner, Set<UUID> members, Set<UUID> cooped, Set<UUID> banned, Location center, Location spawnPoint, Location welcomePoint, int generatorsUpgrade, int membersUpgrade, int sizesUpgrade, long lastLevel, boolean locked, int minX, int minZ, int maxX, int maxZ) {
        this.islandID = islandID;
        this.owner = owner;
        this.members = members;
        this.cooped = cooped;
        this.banned = banned;
        this.center = center;
        this.spawnPoint = spawnPoint;
        this.welcomePoint = welcomePoint;
        this.generatorsUpgrade = generatorsUpgrade;
        this.membersUpgrade = membersUpgrade;
        this.sizesUpgrade = sizesUpgrade;
        this.lastLevel = lastLevel;
        this.locked = locked;
        this.minX = minX;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxZ = maxZ;
    }

    public UUID getIslandID() {
        return islandID;
    }
    public Set<UUID> getAllMembers() {
        Set<UUID> members = new HashSet<>(this.members);
        members.add(owner);
        return members;
    }
    public Set<UUID> getAllMembersCoop() {
        Set<UUID> members = new HashSet<>(getAllMembers());
        members.addAll(cooped);
        return members;
    }
    public UUID getOwner() {
        return owner;
    }
    public Set<UUID> getMembers() {
        return members;
    }
    public Set<UUID> getCooped() {
        return cooped;
    }
    public Set<UUID> getBanned() {
        return banned;
    }
    public Location getCenter() {
        return center;
    }
    public Location getSpawnPoint() {
        return spawnPoint;
    }
    public Location getWelcomePoint() {
        return welcomePoint;
    }
    public Generator getGeneratoUpgrader() {
        Generator generator = Core.getCore().getSkyblock().getUpgradesManager().getGenerator(generatorsUpgrade);
        if (generator == null) {
            generatorsUpgrade = 1;
            return getGeneratoUpgrader();
        }
        return generator;
    }
    public Members getMembersUpgrade() {
        Members members = Core.getCore().getSkyblock().getUpgradesManager().getMember(membersUpgrade);
        if (members == null) {
            membersUpgrade = 1;
            return getMembersUpgrade();
        }
        return members;
    }
    public Size getSizeUpgrade() {
        Size size = Core.getCore().getSkyblock().getUpgradesManager().getSize(sizesUpgrade);
        if (size == null) {
            sizesUpgrade = 1;
            return getSizeUpgrade();
        }
        return size;
    }
    public long getLastLevel() {
        return lastLevel;
    }
    public boolean isLocked() {
        return locked;
    }
    public int getMinX() {
        return minX;
    }
    public int getMinZ() {
        return minZ;
    }
    public int getMaxX() {
        return maxX;
    }
    public int getMaxZ() {
        return maxZ;
    }
    public int getMinProtectedX() {
        return minX + getSizeUpgrade().getArea();
    }
    public int getMinProtectedZ() {
        return minZ + getSizeUpgrade().getArea();
    }
    public int getMaxProtectedX() {
        return maxX - getSizeUpgrade().getArea();
    }
    public int getMaxProtectedZ() {
        return maxZ - getSizeUpgrade().getArea();
    }
    public boolean confirmRemove() {
        return remove;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }
    public void addMember(UUID uuid) {
        members.add(uuid);
    }
    public void removeMember(UUID uuid) {
        members.remove(uuid);
    }
    public void addCoop(UUID uuid) {
        cooped.add(uuid);
    }
    public void removeCoop(UUID uuid) {
        cooped.remove(uuid);
    }
    public void addBan(UUID uuid) {
        banned.add(uuid);
    }
    public void removeBan(UUID uuid) {
        banned.remove(uuid);
    }
    public void setSpawnPoint(Location spawnPoint) {
        this.spawnPoint = spawnPoint;
    }
    public void setWelcomePoint(Location welcomePoint) {
        this.welcomePoint = welcomePoint;
    }
    public void setGeneratorsUpgrade(int generatorsUpgrade) {
        this.generatorsUpgrade = generatorsUpgrade;
    }
    public void setMembersUpgrade(int membersUpgrade) {
        this.membersUpgrade = membersUpgrade;
    }
    public void setSizesUpgrade(int sizesUpgrade) {
        this.sizesUpgrade = sizesUpgrade;
    }
    public void setLastLevel(long lastLevel) {
        this.lastLevel = lastLevel;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public void setRemove(boolean remove) {
        this.remove = remove;
    }
}
