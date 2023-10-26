package nl.itouchinq.scalecore.utilities.command.base.components;

import nl.itouchinq.scalecore.utilities.command.base.CommandBase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CommandData {

    private final CommandBase commandBase;

    private Method method;

    private String name;

    private boolean defaultCmd;

    private Class<?> senderClass;

    private final List<Class<?>> params = new ArrayList<>();
    private final List<String> parameterNames = new ArrayList<>();
    private final List<String> permissions = new ArrayList<>();
    private final List<Integer> valuesArgs = new ArrayList<>();

    private final Map<Integer, String> completions = new HashMap<>();

    private Method completionMethod;

    private boolean optional;
    private String wrongUsage;

    public CommandData(final CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setMethod(final Method method) {
        this.method = method;
    }

    public void setDefault(final boolean defaultCmd) {
        this.defaultCmd = defaultCmd;
    }

    public void setSenderClass(final Class<?> senderClass) {
        this.senderClass = senderClass;
    }

    public void addPermission(String permission) {
        permissions.add(permission);
    }

    public void setOptional(final boolean optional) {
        this.optional = optional;
    }

    public void setWrongUsage(final String wrongUsage) {
        this.wrongUsage = wrongUsage;
    }

    public void setCompletionMethod(final Method completionMethod) {
        this.completionMethod = completionMethod;
    }

    public String getName() {
        return name;
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getSenderClass() {
        return senderClass;
    }

    public List<Class<?>> getParams() {
        return params;
    }

    public Map<Integer, String> getCompletions() {
        return completions;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public String getWrongUsage() {
        return wrongUsage;
    }

    public List<String> getParameterNames() {
        return parameterNames;
    }

    public CommandBase getCommandBase() {
        return commandBase;
    }

    public Method getCompletionMethod() {
        return completionMethod;
    }

    public List<Integer> getArgValue() {
        return valuesArgs;
    }

    public boolean isDefault() {
        return defaultCmd;
    }

    public boolean hasOptional() {
        return optional;
    }

    public boolean hasPermissions() {
        return !permissions.isEmpty();
    }
}
