package nl.itouchinq.scalecore.utilities.command.base.components;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public final class FEUtil {

    public static String color(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> color(final List<String> messages) {
        return messages.parallelStream().map(message -> ChatColor.translateAlternateColorCodes('&', message)).collect(Collectors.toList());
    }

}