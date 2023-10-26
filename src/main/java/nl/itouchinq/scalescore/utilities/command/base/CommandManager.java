package nl.itouchinq.scalescore.utilities.command.base;

import nl.itouchinq.scalescore.utilities.command.annotation.Alias;
import nl.itouchinq.scalescore.utilities.command.annotation.Command;
import nl.itouchinq.scalescore.utilities.command.exceptions.FEException;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@SuppressWarnings("unused")
public final class CommandManager implements Listener {

    // The plugin's main class
    private final Plugin plugin;

    // The command map
    private final CommandMap commandMap;

    // List of commands;
    private final Map<String, CommandHandler> commands = new HashMap<>();
    private Map<String, org.bukkit.command.Command> bukkitCommands = new HashMap<>();

    // The parameter handler
    private final ParameterHandler parameterHandler = new ParameterHandler();
    // The completion handler
    private final CompletionHandler completionHandler = new CompletionHandler();
    // The messages handler
    private final MessageHandler messageHandler = new MessageHandler();

    // If should or not hide tab complete for no permissions
    private boolean hideTab;

    public CommandManager(final Plugin plugin) {
        this(plugin, false);
    }

    public CommandManager(final Plugin plugin, final boolean hideTab) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.hideTab = hideTab;

        this.commandMap = getCommandMap();
    }

    public ParameterHandler getParameterHandler() {
        return parameterHandler;
    }

    public CompletionHandler getCompletionHandler() {
        return completionHandler;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void register(final CommandBase... commands) {
        for (CommandBase command : commands) {
            register(command);
        }
    }

    public void register(final CommandBase command) {
        // Calls the code to run on command register
        command.onRegister();

        final Class<?> commandClass = command.getClass();

        // Checks for the command annotation.
        if (!commandClass.isAnnotationPresent(Command.class)) {
            throw new FEException("Class " + command.getClass().getName() + " needs to have @Command!");
        }

        // Gets the command annotation value.
        final String commandName = commandClass.getAnnotation(Command.class).value();
        // Gets the aliases from the setAlias method
        final List<String> aliases = command.getAliases();

        //Checks if the class has some alias and adds them.
        if (commandClass.isAnnotationPresent(Alias.class)) {
            aliases.addAll(Arrays.asList(commandClass.getAnnotation(Alias.class).value()));
        }

        org.bukkit.command.Command oldCommand = commandMap.getCommand(commandName);

        // From ACF
        // To allow commands to be registered on the plugin.yml
        if (oldCommand instanceof PluginIdentifiableCommand && ((PluginIdentifiableCommand) oldCommand).getPlugin() == this.plugin) {
            bukkitCommands.remove(commandName);
            oldCommand.unregister(commandMap);
        }

        // Used to get the command map to register the commands.
        try {
            final CommandHandler commandHandler;
            if (commands.containsKey(commandName)) {
                commands.get(commandName).addSubCommands(command);
                return;
            }

            // Sets the message handler to be used in the command class
            command.setMessageHandler(messageHandler);

            // Creates the command handler
            commandHandler = new CommandHandler(parameterHandler, completionHandler,
                    messageHandler, command, commandName, aliases, hideTab);

            // Registers the command
            commandMap.register(commandName, plugin.getName(), commandHandler);

            // Puts the handler in the list to unregister later.
            commands.put(commandName, commandHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideTabComplete(final boolean hideTab) {
        this.hideTab = hideTab;

        for (final String cmdName : commands.keySet()) {
            commands.get(cmdName).setHideTab(hideTab);
        }
    }

    @EventHandler
    public void onPluginDisable(final PluginDisableEvent event) {
        if (event.getPlugin() != plugin) return;
        unregisterAll();
    }

    private void unregisterAll() {
        commands.values().forEach(command -> command.unregister(commandMap));
    }

    private CommandMap getCommandMap() {
        CommandMap commandMap = null;

        try {
            final Server server = Bukkit.getServer();
            final Method getCommandMap = server.getClass().getDeclaredMethod("getCommandMap");
            getCommandMap.setAccessible(true);

            commandMap = (CommandMap) getCommandMap.invoke(server);

            final Field bukkitCommands = SimpleCommandMap.class.getDeclaredField("knownCommands");
            bukkitCommands.setAccessible(true);

            //noinspection unchecked
            this.bukkitCommands = (Map<String, org.bukkit.command.Command>) bukkitCommands.get(commandMap);
        } catch (final Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Could not get Command Map, Commands won't be registered!");
        }

        return commandMap;
    }
}

