package nl.itouchinq.scalecore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            switch (event.getItem().getType()) {
                // TODO
                case ANVIL:
                case CRAFTING_TABLE:
                case FURNACE:
                case CHEST:
                case SMOKER:
                case BLAST_FURNACE:
                case COMPOSTER:
                case STONECUTTER:
                case NOTE_BLOCK:
                case BREWING_STAND:
                case ENCHANTING_TABLE:
                case GRINDSTONE:
                case END_PORTAL_FRAME:
                case SMITHING_TABLE:
                case RESPAWN_ANCHOR:
                case CAULDRON:
                case CARTOGRAPHY_TABLE:
                case BELL:
                case LOOM:
                case TRAPPED_CHEST: {
                    event.setCancelled(true);
                }
            }
        }
    }
}
