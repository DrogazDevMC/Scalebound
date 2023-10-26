package nl.itouchinq.scalescore.utilities.command.base.components;

public final class TypeResult {
    private Object resolvedValue;
    private String argumentName;

    public TypeResult(final Object resolvedValue, final Object argumentName) {
        this.resolvedValue = resolvedValue;
        this.argumentName = String.valueOf(argumentName);
    }

    public TypeResult(final Object argumentName) {
        this(null, argumentName);
    }

    public Object getResolvedValue() {
        return resolvedValue;
    }

    public String getArgumentName() {
        return argumentName;
    }
}
