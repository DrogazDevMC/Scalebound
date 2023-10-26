package nl.itouchinq.scalescore.utilities.command.base;

import nl.itouchinq.scalescore.utilities.command.base.components.CompletionResolver;
import nl.itouchinq.scalescore.utilities.command.exceptions.FEException;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public final class CompletionHandler {
    private final Map<String, CompletionResolver> registeredCompletions = new HashMap<>();

    CompletionHandler() {

    }

    public void register(final String completionId, final CompletionResolver completionResolver) {
        if (!completionId.startsWith("#"))
            throw new FEException("Could not register completion, id - " + completionId + " does not start with #.");
        registeredCompletions.put(completionId, completionResolver);
    }

    List<String> getTypeResult(final String completionId, final Object input) {
        return color(registeredCompletions.get(completionId).resolve(input));
    }

    boolean isNotRegistered(final String id) {
        String identifier = id;
        if (id.contains(":")) identifier = identifier.split(":")[0];
        return registeredCompletions.get(identifier) == null;
    }

    private List<String> color(final List<String> messages) {
        return messages.parallelStream().map(message -> ChatColor.translateAlternateColorCodes('&', message)).collect(Collectors.toList());
    }
}
