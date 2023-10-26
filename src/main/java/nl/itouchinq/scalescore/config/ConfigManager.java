package nl.itouchinq.scalescore.config;

import com.google.common.collect.Maps;
import nl.itouchinq.scalescore.config.impl.Messages;
import nl.itouchinq.scalescore.config.impl.Settings;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Map;

public class ConfigManager {

    private final Map<ConfigType, ConfigHandler> configurations;

    public ConfigManager() {
        configurations = Maps.newHashMap();
    }

    public void loadFiles(JavaPlugin plugin) {
        registerFile(ConfigType.SETTINGS, new ConfigHandler(plugin, "config"));
        registerFile(ConfigType.MESSAGES, new ConfigHandler(plugin, "messages"));

        Messages.setConfiguration(getFile(ConfigType.MESSAGES).getConfig());
        Settings.setConfiguration(getFile(ConfigType.SETTINGS).getConfig());
    }

    public ConfigHandler getFile(ConfigType type) {
        return configurations.get(type);
    }

    public void reloadFiles() {
        configurations.values().forEach(ConfigHandler::reload);

        Messages.setConfiguration(getFile(ConfigType.MESSAGES).getConfig());
        Settings.setConfiguration(getFile(ConfigType.SETTINGS).getConfig());
    }

    public void registerFile(ConfigType type, ConfigHandler config) {
        configurations.put(type, config);
    }

    public FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }
}
