package nl.itouchinq.scalecore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class CoinWalkListener implements Listener {

    @EventHandler
    public void onWalk(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // TODO verwerkt niet

    }
}
