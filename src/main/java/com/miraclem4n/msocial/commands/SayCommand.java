package com.miraclem4n.msocial.commands;

import com.miraclem4n.mchat.api.API;
import com.miraclem4n.mchat.types.IndicatorType;
import com.miraclem4n.mchat.util.MiscUtil;
import com.miraclem4n.msocial.MSocial;
import com.miraclem4n.msocial.types.LocaleType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SayCommand implements CommandExecutor {
    MSocial plugin;

    public SayCommand(MSocial instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("mchatsay")
                || !MiscUtil.hasCommandPerm(sender, "mchat.say"))
            return true;

        if (args.length > 0) {
            String message = "";

            for (String arg : args)
                message += " " + arg;

            message = message.trim();

            plugin.getServer().broadcastMessage(API.replace(LocaleType.FORMAT_SAY.getVal(), "msg", message, IndicatorType.LOCALE_VAR));
            return true;
        }

        return false;
    }
}
