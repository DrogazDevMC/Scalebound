package nl.itouchinq.scalecore.utilities.command.base.components;

import java.util.List;

@FunctionalInterface
public interface CompletionResolver {

    List<String> resolve(Object input);
}
