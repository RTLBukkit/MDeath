package ca.q0r.mdeath;

import ca.q0r.mdeath.commands.*;
import ca.q0r.mdeath.configs.ConfigUtil;
import ca.q0r.mdeath.configs.LocaleUtil;
import ca.q0r.mdeath.events.CommandListener;
import ca.q0r.mdeath.events.EntityListener;
import ca.q0r.mdeath.types.ConfigType;

import com.miraclem4n.mchat.commands.MChatCommand;
import com.miraclem4n.mchat.metrics.Metrics;
import com.miraclem4n.mchat.util.MessageUtil;
import com.miraclem4n.mchat.util.TimerUtil;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class MDeath extends JavaPlugin {
    // Default Plugin Data
    public PluginManager pm;
    public PluginDescriptionFile pdfFile;

    // Metrics
    public Metrics metrics;

    // Spout
    public Boolean spoutB = false;

    // Maps


    public void onEnable() {
        // Initialize Plugin Data
        pm = getServer().getPluginManager();
        pdfFile = getDescription();

        try {
            // Initialize and Start the Timer
            TimerUtil timer = new TimerUtil();

            initializeConfigs();

            setupPlugins();

            setupCommands();

            setupEvents();

            // Stop the Timer
            timer.stop();

            // Calculate Startup Timer
            long diff = timer.difference();

            MessageUtil.log("[" + pdfFile.getName() + "] " + pdfFile.getName() + " v" + pdfFile.getVersion() + " is enabled! [" + diff + "ms]");
        } catch(NoClassDefFoundError ignored) {
            pm.disablePlugin(this);
        }
    }

    public void onDisable() {
        try {
            // Initialize and Start the Timer
            TimerUtil timer = new TimerUtil();

            getServer().getScheduler().cancelTasks(this);

            unloadConfigs();

            // Stop the Timer
            timer.stop();

            // Calculate Shutdown Timer
            long diff = timer.difference();

            MessageUtil.log("[" + pdfFile.getName() + "] " + pdfFile.getName() + " v" + pdfFile.getVersion() + " is disabled! [" + diff + "ms]");
        } catch(NoClassDefFoundError ignored) {
            System.err.println("[" + pdfFile.getName() + "] MChat not found disabling!");
            System.out.println("[" + pdfFile.getName() + "] " + pdfFile.getName() + " v" + pdfFile.getVersion() + " is disabled!");
        }
    }

    Boolean setupPlugin(String pluginName) {
        Plugin plugin = pm.getPlugin(pluginName);

        if (plugin != null) {
            MessageUtil.log("[" + pdfFile.getName() + "] <Plugin> " + plugin.getDescription().getName() + " v" + plugin.getDescription().getVersion() + " hooked!.");
            return true;
        }

        return false;
    }

    void setupPlugins() {}

    void setupCommands() {
        regCommands("mdeath", new MDeathCommand(this));
    }

    void regCommands(String command, CommandExecutor executor) {
        if (getCommand(command) != null)
            getCommand(command).setExecutor(executor);
    }

    void setupEvents() {
        pm.registerEvents(new EntityListener(this), this);
    }

    public void initializeConfigs() {
        ConfigUtil.initialize();
        LocaleUtil.initialize();
    }

    public void reloadConfigs() {
        ConfigUtil.initialize();
        LocaleUtil.initialize();
    }

    public void unloadConfigs() {
        ConfigUtil.dispose();
        LocaleUtil.dispose();
    }
}
