package com.gmail.markushygedombrowski.listeners;

import com.gmail.markushygedombrowski.HLBandeKrig;
import com.gmail.markushygedombrowski.HLWarp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    private HLWarp hlWarp;
    private HLBandeKrig plugin;

    public RespawnListener(HLWarp hlWarp, HLBandeKrig plugin) {
        this.hlWarp = hlWarp;
        this.plugin = plugin;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        if(p.hasPermission("fange")) {
            if(p.hasMetadata("bandekrigDeathfange")) {
                p.removeMetadata("bandekrigDeathfange",plugin);
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        p.teleport(hlWarp.getWarpManager().getWarpInfo("bandekrigdeath").getLocation());
                    }
                }, 5);
                p.sendMessage("§7[§cBandeKrig§7] §cDu kan nu spectate eventet");
                return;
            }
        }

    }
}
