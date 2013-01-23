package ca.q0r.mdeath.configs;

import ca.q0r.mdeath.types.LocaleType;
import com.miraclem4n.mchat.MChat;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LocaleUtil {
    static YamlConfiguration config;
    static File file;
    static Boolean changed;

    public static void initialize() {
        load();
    }

    public static void dispose() {
        config = null;
        file = null;
        changed = null;
    }

    public static void load() {
        file = new File("plugins/MDeath/locale.yml");//TODO: change this to use proper method to get plugin directory

        config = YamlConfiguration.loadConfiguration(file);

        config.options().indent(4);
        config.options().header("MDeath Locale");

        changed = false;

        loadDefaults();
    }

    private static void loadDefaults() {
        checkOption("message.config.reloaded", "%config Reloaded.");
        checkOption("message.config.updated", "%config has been updated.");
        checkOption("message.death.inFire", "%player went up in flames.");
        checkOption("message.death.onFire", "%player burned to death.");
        checkOption("message.death.lava", "%player tried to swim in lava.");
        checkOption("message.death.inWall", "%player suffocated in a wall.");
        checkOption("message.death.drown", "%player drowned.");
        checkOption("message.death.starve", "%player starved to death.");
        checkOption("message.death.cactus", "%player was pricked to death.");
        checkOption("message.death.fall", "%player hit the ground too hard.");
        checkOption("message.death.outOfWorld", "%player fell out of the world.");
        checkOption("message.death.generic", "%player died.");
        checkOption("message.death.explosion", "%player blew up.");
        checkOption("message.death.magic", "%player was killed by magic.");
        checkOption("message.death.entity", "%player was slain by %killer.");
        checkOption("message.death.arrow", "%player was shot by %killer.");
        checkOption("message.death.fireball", "%player was fireballed by %killer.");
        checkOption("message.death.thrown", "%player was pummeled by %killer.");

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

    private static void checkOption(String option, Object defValue) {
        if (!config.isSet(option))
            set(option, defValue);
    }
}
