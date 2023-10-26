package nl.itouchinq.scalecore.config.impl;

import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
public enum Messages {

    PREFIX("general.prefix"),

    NO_PERMISSION("general.no-perms"),
    NO_CONSOLE("general.no-console"),
    NO_PLAYER("general.no-player"),

    INSUFFICIENT_ARGS("general.insufficient-args"),

    END(null);

    private static FileConfiguration config;
    private final String path;

    public static void setConfiguration(FileConfiguration c) {
        config = c;
    }

    public void send(CommandSender receiver, Object... replacements) {
        Object value = config.get(this.path);

        String message;
        if (value == null) {
            message = "ScaleCore: message not found (" + this.path + ")";
        } else {
            message = value instanceof List ? fromList((List<?>) value) : value.toString();
        }

        if (!message.isEmpty()) {
            receiver.sendMessage(ChatColor.translateAlternateColorCodes('&', replace(message, replacements)));
        }
    }

    public void send(CommandSender receiver, String m, Object... replacements) {
        Object value = m;

        String message;
        if (value == null) {
            message = "ScaleCore: message not found (" + this.path + ")";
        } else {
            message = value.toString();
        }

        if (!message.isEmpty()) {
            receiver.sendMessage(ChatColor.translateAlternateColorCodes('&', replace(message, replacements)));
        }
    }

    public void send(Player receiver, Object... replacements) {
        Object value = config.get(this.path);

        String message;
        if (value == null) {
            message = "ScaleCore: message not found (" + this.path + ")";
        } else {
            message = value instanceof List ? fromList((List<?>) value) : value.toString();
        }

        if (!message.isEmpty()) {
            receiver.sendMessage(ChatColor.translateAlternateColorCodes('&', replace(message, replacements)));
        }
    }

    public void send(Player receiver, String m, Object... replacements) {
        Object value = m;

        String message;
        if (value == null) {
            message = "ScaleCore: message not found (" + this.path + ")";
        } else {
            message = value.toString();
        }

        if (!message.isEmpty()) {
            receiver.sendMessage(ChatColor.translateAlternateColorCodes('&', replace(message, replacements)));
        }
    }

    private String replace(String message, Object... replacements) {
        for (int i = 0; i < replacements.length; i += 2) {
            if (i + 1 >= replacements.length) break;
            message = message.replace(String.valueOf(replacements[i]), String.valueOf(replacements[i + 1]));
        }

        String prefix = config.getString(PREFIX.getPath());
        return message.replace("<prefix>", prefix != null && !prefix.isEmpty() ? prefix : "");
    }

    public String getPath() {
        return this.path;
    }

    public Object get() {
        return config.get(path);
    }

    private String fromList(List<?> list) {
        if (list == null || list.isEmpty()) return null;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if(org.bukkit.ChatColor.stripColor(list.get(i).toString()).equals("")) builder.append("\n&r");
            else builder.append(list.get(i).toString()).append(i + 1 != list.size() ? "\n" : "");
        }

        return builder.toString();
    }
}
