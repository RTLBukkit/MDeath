package ca.q0r.mdeath.configs;

import org.bukkit.configuration.file.YamlConfiguration;

import ca.q0r.mdeath.MDeath;

import com.miraclem4n.mchat.util.MessageUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class ConfigUtil {
    static YamlConfiguration config;
    static File file;
    static Boolean changed;
    
    static HashMap<String, List<String>> aliasMap = new HashMap<String, List<String>>();

    public static void initialize() {
        load();
    }

    public static void dispose() {
        config = null;
        file = null;
        changed = null;
        aliasMap = null;
    }

    public static void load() {
        file = new File("plugins/MDeath/config.yml");//TODO: change this to use proper method to get plugin directory

        config = YamlConfiguration.loadConfiguration(file);

        config.options().indent(4);
        config.options().header("MDeath Config");

        changed = false;

        loadDefaults();
    }

    private static void loadDefaults() {
    	editOption("mchat-format-events", "mchat.formatEvents");
    	editOption("mchat.formatEvents", "mchat.alter.events");
    	editOption("mchat.alterEvents", "mchat.alter.events");
        editOption("mchat.alterDeathMessages", "mchat.alter.death");

        checkOption("mchat.alter.events", true);
        checkOption("mchat.alter.death", true);
        checkOption("suppress.useDeath", false);
        checkOption("suppress.maxDeath", 30);

        loadAliases();
        setupAliasMap();

        save();
    }

    public static void set(String key, Object obj) {
        config.set(key, obj);

        changed = true;
    }

    public static Boolean save() {
        if (!changed)
            return false;

        try {
            config.save(file);
            changed = false;
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static HashMap<String, List<String>> getAliasMap() {
        return aliasMap;
    }

    private static void checkOption(String option, Object defValue) {
        if (!config.isSet(option))
            set(option, defValue);
    }

    private static void editOption(String oldOption, String newOption) {
        if (config.isSet(oldOption)) {
            set(newOption, config.get(oldOption));
            set(oldOption, null);
        }
    }

    private static void removeOption(String option) {
        if (config.isSet(option))
            set(option, null);
    }

    private static void loadAliases() {

        //muteAliases.add("mute");
        //muteAliases.add("quiet");

    }

    private static void setupAliasMap() {
    	try{
        Set<String> keys = config.getConfigurationSection("aliases").getKeys(false);

        for (String key : keys)
            aliasMap.put(key, config.getStringList("aliases." + key));
        }catch(Exception e){
        	MessageUtil.log(Level.WARNING, "[mdeath] failed to load command aliases:\n"+e.getCause());//TODO: redo mchatsuite with proper logging...
        }
    }
}
