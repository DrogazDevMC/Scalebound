package nl.itouchinq.scalecore.utilities.command.base.components;

import org.bukkit.command.CommandSender;

@FunctionalInterface
public interface MessageResolver {

    void resolve(CommandSender sender);

}