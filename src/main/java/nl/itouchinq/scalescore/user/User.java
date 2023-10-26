package nl.itouchinq.scalescore.user;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import lombok.Setter;
import nl.itouchinq.scalescore.ScaleScore;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class User {

    private ScaleScore plugin;

    private UUID uuid;

    private String language;

    private int level, coins;

    private String currentJob;

    private ArrayList<User> friends;

    // TODO data variables

    public User(ScaleScore plugin, UUID uuid) {
        this.plugin = plugin;
        this.uuid = uuid;

        this.language = "EN";

        this.level = 0;
        this.coins = 0;

        this.currentJob = "No Job";
        // TODO data instance
    }

    public User(ScaleScore plugin, Document document) {
        this.plugin = plugin;

        this.uuid = UUID.fromString(document.getString("_id"));

        // TODO data
        this.language = document.getString("language");

        this.level = document.getInteger("level");
        this.coins = document.getInteger("coins");

        this.currentJob = document.getString("currentJob");
    }

    public void save() {
        final Document document = plugin.getMongoHandler().getUsers().find(Filters.eq("_id", uuid.toString())).first();

        if (document == null) {
            plugin.getMongoHandler().getUsers().insertOne(toBson());
            return;
        }

        plugin.getMongoHandler().getUsers().replaceOne(document, toBson(), new ReplaceOptions().upsert(true));
    }

    public Document toBson() {
        Document document = new Document("_id", uuid.toString());

        document.append("language", language);

        document.append("level", level);
        document.append("coins", coins);

        document.append("currentJob", currentJob);

        return document;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
