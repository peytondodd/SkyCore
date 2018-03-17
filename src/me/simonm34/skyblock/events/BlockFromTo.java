package me.simonm34.skyblock.events;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockFromTo implements Listener {
    public BlockFromTo() {
        Core.getCore().getServer().getPluginManager().registerEvents(this, Core.getCore());
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent e) {
        Block block = e.getBlock();
        Block toBlock = e.getToBlock();

        if (block.getType() == Material.WATER ||
                block.getType() == Material.STATIONARY_WATER ||
                block.getType() == Material.LAVA ||
                block.getType() == Material.STATIONARY_LAVA) {
            if (toBlock.getType() == Material.AIR) {
                if (generatesCobble(block)) {
                    Island island = Core.getCore().getSkyblock().getIslandManager().getIslandAtLocation(block.getLocation());
                    if (island != null)
                        block.setType(island.getGeneratoUpgrader().getMaterial());
                }
            }
        }
    }

    private BlockFace[] blockFaces = new BlockFace[]{
            BlockFace.SELF,
            BlockFace.UP,
            BlockFace.DOWN,
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST
    };
    public boolean generatesCobble(Block block){
        Material mirrorID1 = (block.getType() == Material.WATER || block.getType() == Material.STATIONARY_WATER ? Material.LAVA : Material.WATER);
        Material mirrorID2 = (block.getType() == Material.WATER || block.getType() == Material.STATIONARY_WATER ? Material.STATIONARY_LAVA : Material.STATIONARY_WATER);
        for (BlockFace face : blockFaces){
            Block r = block.getRelative(face, 1);
            if (r.getType() == mirrorID1 || r.getType() == mirrorID2){
                return true;
            }
        }
        return false;
    }
}
