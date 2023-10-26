package nl.itouchinq.scalecore.listeners;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.BoundingBox;

import java.util.function.Predicate;

public class CoinWalkListener implements Listener {

    @EventHandler
    public void onWalk(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // TODO verwerkt niet

        if (event.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.ARMOR_STAND) {
            Predicate<Entity> filter = entity -> entity instanceof ArmorStand && entity.getBoundingBox().overlaps(player.getBoundingBox());
            player.getWorld().getNearbyEntities(player.getLocation(), 1, 2, 1, filter).forEach(hitArmorStand -> {
                //every entity in here will be an armorstand that matches our filter
                player.sendMessage("Je hebt een coin gepakt!");
                hitArmorStand.remove();
                //send message and remove
            });
        }
    }
}
