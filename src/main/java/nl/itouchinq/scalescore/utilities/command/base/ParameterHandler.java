package nl.itouchinq.scalescore.utilities.command.base;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import nl.itouchinq.scalescore.utilities.command.base.components.CommandData;
import nl.itouchinq.scalescore.utilities.command.base.components.ParameterResolver;
import nl.itouchinq.scalescore.utilities.command.base.components.TypeResult;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"WeakerAccess", "UnstableApiUsage"})
public final class ParameterHandler {
    private final Map<Class<?>, ParameterResolver> registeredTypes = new HashMap<>();

    ParameterHandler() {
        register(Short.class, arg -> {
            final Integer integer = Ints.tryParse(String.valueOf(arg));
            return integer == null ? new TypeResult(arg) : new TypeResult(integer.shortValue(), arg);
        });
        register(Integer.class, arg -> new TypeResult(Ints.tryParse(String.valueOf(arg)), arg));
        register(Long.class, arg -> new TypeResult(Longs.tryParse(String.valueOf(arg)), arg));
        register(Float.class, arg -> new TypeResult(Floats.tryParse(String.valueOf(arg)), arg));
        register(Double.class, arg -> new TypeResult(Doubles.tryParse(String.valueOf(arg)), arg));

        register(String.class, arg -> arg instanceof String ? new TypeResult(arg, arg) : new TypeResult(arg));

        register(String[].class, arg -> {
            if (arg instanceof String[]) return new TypeResult(arg, arg);
            // Will most likely never happen.
            return new TypeResult(arg);
        });

        register(Boolean.class, arg -> new TypeResult(Boolean.valueOf(String.valueOf(arg)), arg));
        register(boolean.class, arg -> new TypeResult(Boolean.valueOf(String.valueOf(arg)), arg));

        register(Player.class, arg -> new TypeResult(Bukkit.getPlayer(String.valueOf(arg)), arg));
        register(OfflinePlayer.class, arg -> new TypeResult(Bukkit.getOfflinePlayer(String.valueOf(arg))));
        register(Material.class, arg -> new TypeResult(Material.matchMaterial(String.valueOf(arg)), arg));

        register(Sound.class, arg -> {
            final String soundValue = Arrays.stream(Sound.values())
                    .map(Enum::name)
                    .filter(name -> name.equalsIgnoreCase(String.valueOf(arg)))
                    .findFirst().orElse(null);

            return soundValue == null ? new TypeResult(null, arg) : new TypeResult(Sound.valueOf(soundValue), arg);
        });

        register(World.class, arg -> new TypeResult(Bukkit.getWorld(String.valueOf(arg)), arg));
    }

    public void register(final Class<?> clss, final ParameterResolver parameterResolver) {
        registeredTypes.put(clss, parameterResolver);
    }

    Object getTypeResult(final Class<?> clss, final Object object, final CommandData subCommand, final String paramName) {
        final TypeResult result = registeredTypes.get(clss).resolve(object);
        subCommand.getCommandBase().addArgument(paramName, result.getArgumentName());

        return result.getResolvedValue();
    }

    boolean isRegisteredType(final Class<?> clss) {
        return registeredTypes.get(clss) != null;
    }
}
