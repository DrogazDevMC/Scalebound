package nl.itouchinq.scalescore.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import nl.itouchinq.scalescore.ScaleScore;
import nl.itouchinq.scalescore.config.ConfigType;
import nl.itouchinq.scalescore.config.impl.Settings;
import org.bson.Document;
import org.bukkit.configuration.file.FileConfiguration;

@Getter
public class MongoHandler {

    private final ScaleScore plugin;

    private final MongoClient client;
    private final MongoDatabase database;

    private final MongoCollection<Document> users;

    public MongoHandler(ScaleScore plugin) {
        this.plugin = plugin;

        boolean auth = Settings.MONGO_AUTH_ENABLED.getAsBoolean();
        String host = Settings.MONGO_HOST.getString();
        int port = Settings.MONGO_PORT.getAsInt();

        String uri = "mongodb://" + host + ":" + port;

        if (auth) {
            String username = Settings.MONGO_AUTH_USERNAME.getString();
            String password = Settings.MONGO_AUTH_PASSWORD.getString();
            uri = "mongodb://" + username + ":" + password + "@" + host + ":" + port;
        }

        this.client = MongoClients.create(uri);
        this.database = client.getDatabase("scalebound");

        this.users = database.getCollection("users");
    }
}
