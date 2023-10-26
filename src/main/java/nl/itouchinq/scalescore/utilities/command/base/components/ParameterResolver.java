package nl.itouchinq.scalescore.utilities.command.base.components;

@FunctionalInterface
public interface ParameterResolver {

    TypeResult resolve(Object argument);
}
