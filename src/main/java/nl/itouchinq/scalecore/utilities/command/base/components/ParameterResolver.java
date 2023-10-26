package nl.itouchinq.scalecore.utilities.command.base.components;

@FunctionalInterface
public interface ParameterResolver {

    TypeResult resolve(Object argument);
}
