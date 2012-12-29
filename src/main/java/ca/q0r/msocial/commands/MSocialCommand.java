package ca.q0r.msocial.commands;

import ca.q0r.msocial.MSocial;
import ca.q0r.msocial.configs.ConfigUtil;
import com.miraclem4n.mchat.util.MessageUtil;
import com.miraclem4n.mchat.util.MiscUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MSocialCommand implements CommandExecutor {
    MSocial plugin;

    public MSocialCommand(MSocial instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("msocial"))
            return true;

        if (args.length == 0)
            return false;

        if (args[0].equalsIgnoreCase("reload")
                || args[0].equalsIgnoreCase("r")) {
            if (!MiscUtil.hasCommandPerm(sender, "msocial.reload"))
                return true;

            ConfigUtil.initialize();
            MessageUtil.sendMessage(sender, "Config Reloaded.");
            return true;
        }

        return false;
    }
}
