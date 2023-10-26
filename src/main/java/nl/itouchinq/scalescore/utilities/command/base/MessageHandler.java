package nl.itouchinq.scalescore.utilities.command.base;

import nl.itouchinq.scalescore.ScaleScore;
import nl.itouchinq.scalescore.utilities.command.base.components.MessageResolver;
import nl.itouchinq.scalescore.utilities.command.exceptions.FEException;
import nl.fenixnetwerk.modules.utils.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

import java.util.HashMap;
import java.util.Map;

import static nl.itouchinq.scalescore.utilities.command.base.components.FEUtil.color;


public class MessageHandler {

    private final Map<String, MessageResolver> messages = new HashMap<>();

    private final Configuration configuration = ScaleScore.get().getConfig();

    MessageHandler() {
        register("cmd.no.permission", sender -> sender.sendMessage(CC.translate(configuration.getString("general.no-perms"))));
        register("cmd.no.console", sender -> sender.sendMessage(CC.translate(configuration.getString("general.no-console"))));
        register("cmd.no.player", sender -> sender.sendMessage(CC.translate(configuration.getString("general.no-player"))));
        register("cmd.no.exists", sender -> sender.sendMessage(CC.translate(configuration.getString("general.no-exists"))));
        register("cmd.wrong.usage", sender -> sender.sendMessage(color("&cVerkeerde usage!")));
    }

    public void register(final String messageId, final MessageResolver messageResolver) {
        messages.put(messageId, messageResolver);
    }

    boolean hasId(String messageId) {
        return messages.get(messageId) != null;
    }

    void sendMessage(final String messageId, final CommandSender sender) {
        final MessageResolver messageResolver = messages.get(messageId);
        if (messageResolver == null) throw new FEException("The message ID \"" + messageId + "\" does not exist!");
        messageResolver.resolve(sender);
    }
}
