package nl.itouchinq.scalecore.user;

import lombok.Getter;
import nl.itouchinq.scalecore.ScaleCore;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class UserManager implements Listener {

    private final ScaleCore plugin;
    private final Map<UUID, User> users;

    public UserManager(ScaleCore plugin) {
        this.plugin = plugin;
        this.users = new HashMap<>();

        this.load();

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void load() {
        for (Document document : plugin.getMongoHandler().getUsers().find()) {
            users.put(UUID.fromString(document.getString("_id")), new User(plugin, document));
        }
    }

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();
        User user = new User(plugin, uuid);

        if (!users.containsKey(uuid)) {
            users.put(uuid, user);

            user.save();
        }
    }
}
