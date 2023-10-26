package nl.itouchinq.scalecore.config.impl;

import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

@RequiredArgsConstructor
public enum Settings {

    // DATABASE
    MONGO_HOST("mongo.host"),
    MONGO_PORT("mongo.port"),
    MONGO_AUTH_ENABLED("mongo.auth.enabled"),
    MONGO_AUTH_USERNAME("mongo.auth.username"),
    MONGO_AUTH_PASSWORD("mongo.auth.password"),



    END(null);

    private static FileConfiguration config;
    private final String path;

    public static void setConfiguration(FileConfiguration c) {
        config = c;
    }

    public int getAsInt() {
        return config.getInt(path);
    }

    public boolean getAsBoolean() {
        return config.getBoolean(path);
    }

    public String getString() {
        return config.getString(path);
    }

    public List<String> getAsStringList() {
        return config.getStringList(path);
    }
}
