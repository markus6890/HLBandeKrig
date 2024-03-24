package com.gmail.markushygedombrowski.commands;

import com.gmail.markushygedombrowski.HLWarp;
import com.gmail.markushygedombrowski.settings.SettingsBa;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class StartEventCommand implements CommandExecutor {
    private SettingsBa settingsBa;
    private HLWarp hlwarp;

    public StartEventCommand(SettingsBa settingsBa, HLWarp hlwarp) {
        this.settingsBa = settingsBa;
        this.hlwarp = hlwarp;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String alias, String[] strings) {
        if(!(commandSender instanceof Player)) {
            System.out.println("kun spillere kan bruge denne command");
            return true;
        }
        Player p = (Player) commandSender;
        if(!p.hasPermission("startevent")) {
            p.sendMessage("§cDu har ikke permission til at starte et event");
            return true;
        }
        if(alias.equalsIgnoreCase("restartbandekrig")) {
            if(settingsBa.isAktiv()) {
                Bukkit.broadcastMessage("§7=========================");
                Bukkit.broadcastMessage("§c§lBandekrigs eventet vi tager en ny runde");
                Bukkit.broadcastMessage("§7=========================");
                settingsBa.tpAllFange("bandekrig");
                return true;
            }
        }


        if(settingsBa.isAktiv()) {
            settingsBa.setAktiv(false);
            p.hasPermission("Du har stoppet eventet");
            Bukkit.broadcastMessage("§7=========================");
            Bukkit.broadcastMessage("§c§lBandekrigs eventet er nu SLUT");
            Bukkit.broadcastMessage("§7=========================");
            List<Player> fangelist = settingsBa.getFangeListe();
            for (Player player : fangelist) {
                if(player.hasPermission("a-fange")) {
                    player.teleport(hlwarp.getWarpManager().getWarpInfo("a").getLocation());
                } else if(player.hasPermission("b-fange")) {
                    player.teleport(hlwarp.getWarpManager().getWarpInfo("b").getLocation());
                } else if(player.hasPermission("c-fange")) {
                    player.teleport(hlwarp.getWarpManager().getWarpInfo("c").getLocation());
                }

            }
            settingsBa.clearFangeListe();
            return true;
        }
        String group = "bobi";
        settingsBa.setAktiv(!settingsBa.isAktiv());
        if(strings.length == 0) {
            settingsBa.setAktivall(!settingsBa.isAktivall());
            group = "§aAlle";
        } else if(strings[0].equalsIgnoreCase("a")) {
            settingsBa.setAktiva(!settingsBa.isAktiva());
            group = "§aA";
        } else if(strings[0].equalsIgnoreCase("b")) {
            settingsBa.setAktivb(!settingsBa.isAktivb());
            group = "§bB";


        } else if(strings[0].equalsIgnoreCase("c")) {
            settingsBa.setAktivc(!settingsBa.isAktivc());
            group = "§cC";

        }

        p.sendMessage("§aDu har startet et event");
        Bukkit.broadcastMessage("§7=========================");
        Bukkit.broadcastMessage("§c§lBandekrigs §ceventet er nu §a§lStartet!");
        Bukkit.broadcastMessage("§7For " + group);
        Bukkit.broadcastMessage("§7=========================");


        return true;
    }
}
