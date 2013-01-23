package ca.q0r.mdeath.commands;

import ca.q0r.mdeath.MDeath;
import ca.q0r.mdeath.configs.ConfigUtil;
import ca.q0r.mdeath.configs.LocaleUtil;

import com.miraclem4n.mchat.configs.CensorUtil;
import com.miraclem4n.mchat.configs.InfoUtil;
import com.miraclem4n.mchat.util.MessageUtil;
import com.miraclem4n.mchat.util.MiscUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MDeathCommand implements CommandExecutor {
    MDeath plugin;

    public MDeathCommand(MDeath instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0)
            return false;
        
        if (args[0].equalsIgnoreCase("version")) {
            if (!MiscUtil.hasCommandPerm(sender, "mchat.version"))
                return true;

            String cVersion = "&6MineCraft Version: &2" + plugin.pdfFile.getVersion();

            cVersion = cVersion.replaceFirst("-", "^*^&6Jenkins Build &5#&5: &2");
            cVersion = cVersion.replaceFirst("-", "^*^&6Release Version: &2");
            cVersion = cVersion.replaceFirst("_", "^*^&6Fix &5#&5: &2");

            String[] vArray = cVersion.split("\\^\\*\\^");

            MessageUtil.sendMessage(sender, "&6Full Version: &1" + plugin.pdfFile.getVersion());

            for (String string : vArray)
                MessageUtil.sendMessage(sender, string);

            return true;
        } else if (args[0].equalsIgnoreCase("reload")
                || args[0].equalsIgnoreCase("r")) {
            if (args.length > 1)
                if (args[1].equalsIgnoreCase("config")
                        || args[1].equalsIgnoreCase("co")) {
                    if (!MiscUtil.hasCommandPerm(sender, "mchat.reload.config"))
                        return true;

                    ConfigUtil.initialize();
                    MessageUtil.sendMessage(sender, "Config Reloaded.");
                    return true;
                } else if (args[1].equalsIgnoreCase("locale")
                        || args[1].equalsIgnoreCase("l")) {
                    if (!MiscUtil.hasCommandPerm(sender, "mchat.reload.locale"))
                        return true;

                    LocaleUtil.initialize();
                    MessageUtil.sendMessage(sender, "Locale Reloaded.");
                    return true;
                } else if (args[1].equalsIgnoreCase("all")
                        || args[1].equalsIgnoreCase("a")) {
                    if (!MiscUtil.hasCommandPerm(sender, "mchat.reload.all"))
                        return true;

                    plugin.reloadConfigs();
                    plugin.initializeConfigs();
                    MessageUtil.sendMessage(sender, "All Config's Reloaded.");
                    return true;
                }
        }

        return false;
    }
}
