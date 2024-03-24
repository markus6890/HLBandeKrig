package com.gmail.markushygedombrowski.listeners;

import com.gmail.markushygedombrowski.HLBandeKrig;
import com.gmail.markushygedombrowski.HLWarp;
import com.gmail.markushygedombrowski.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class DeathListener implements Listener {

    private HLBandeKrig plugin;

    public DeathListener(HLBandeKrig plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        if(p.hasPermission("fange")) {
            if(Utils.isLocInRegion(p.getLocation(),"bandekrig")) {
                p.sendMessage("§7[§cBandeKrig§7] §cDu døde til bandekrig event");
                p.setMetadata("bandekrigDeathfange",new FixedMetadataValue(plugin,true));

            }
            return;
        }


    }
}
