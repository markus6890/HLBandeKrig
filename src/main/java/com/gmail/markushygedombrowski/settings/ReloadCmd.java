package com.gmail.markushygedombrowski.settings;

import com.gmail.markushygedombrowski.HLBandeKrig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCmd implements CommandExecutor {
    private HLBandeKrig plugin;

    public ReloadCmd(HLBandeKrig plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender.hasPermission("HLreload"))) {
            commandSender.sendMessage("§aYou do not have permission to do that");
            return true;
        }
        plugin.reload();

        return true;

    }
}
