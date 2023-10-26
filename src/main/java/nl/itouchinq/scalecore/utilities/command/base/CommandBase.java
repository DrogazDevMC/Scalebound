package nl.itouchinq.scalecore.utilities.command.base;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class CommandBase {
    private final Map<String, String> arguments = new HashMap<>();

    private MessageHandler messageHandler;

    private final List<String> aliases = new ArrayList<>();

    public void onRegister() {
    }

    public void setAliases(final List<String> aliases) {
        this.aliases.addAll(aliases);
    }

    public String getArgument(final String name) {
        return arguments.getOrDefault(name, null);
    }

    public void sendMessage(final String messageId, final CommandSender sender) {
        messageHandler.sendMessage(messageId, sender);
    }

    public void sendMessage(final Player player, final String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void sendMessage(final CommandSender sender, final String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    void setMessageHandler(final MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    void clearArgs() {
        arguments.clear();
    }

    void addArgument(final String name, final String argument) {
        arguments.put(name, argument);
    }

    List<String> getAliases() {
        return aliases;
    }
}
