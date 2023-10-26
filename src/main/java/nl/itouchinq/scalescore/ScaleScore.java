package nl.itouchinq.scalescore;

import lombok.Getter;
import nl.fenixnetwerk.modules.menu.MenuListener;
import nl.fenixnetwerk.modules.utils.CC;
import nl.itouchinq.scalescore.config.ConfigManager;
import nl.itouchinq.scalescore.mongo.MongoHandler;
import nl.itouchinq.scalescore.user.User;
import nl.itouchinq.scalescore.user.UserManager;
import nl.itouchinq.scalescore.utilities.command.base.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class ScaleScore extends JavaPlugin {

    private MongoHandler mongoHandler;

    private UserManager userManager;
    private CommandManager commandManager;

    private ConfigManager configManager;

    public static ScaleScore get() {
        return getPlugin(ScaleScore.class);
    }

    @Override
    public void onEnable() {
        initializeConfig();

        CC.console(CC.LINE);
        CC.console(" ");

        CC.console("&7- Enabling &4&lScaleCore");
        CC.console("&7- Made By &cScale");
        CC.console("&7- Version &c" + getDescription().getVersion());

        registerManagers();

        CC.console(" ");
        CC.console("&7[&4&lScaleCore&7] &fEnabled &4&lScaleCore");

        CC.console(CC.LINE);
    }

    @Override
    public void onDisable() {
        long time = System.currentTimeMillis();

        CC.console(CC.LINE);
        CC.console(" ");

        CC.console("&7- Disabling &4&lScaleCore");
        CC.console("&7- Made By &cScale");
        CC.console("&7- Version &c" + getDescription().getVersion());
        CC.console(" ");

        userManager.getUsers().values().forEach(User::save);

        CC.console("&7[&4&lScaleCore&7] &7Has been disabled in &a" + (System.currentTimeMillis() - time) + "ms");
        CC.console(CC.LINE);
    }

    private void registerManagers() {
        this.mongoHandler = new MongoHandler(this);
        this.commandManager = new CommandManager(this);

        this.userManager = new UserManager(this);

        Bukkit.getPluginManager().registerEvents(new MenuListener(this), this);
    }

    private void initializeConfig() {
        this.configManager = new ConfigManager();
        this.configManager.loadFiles(this);
    }
}
