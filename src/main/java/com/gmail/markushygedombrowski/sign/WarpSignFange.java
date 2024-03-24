package com.gmail.markushygedombrowski.sign;

import com.gmail.markushygedombrowski.HLWarp;
import com.gmail.markushygedombrowski.settings.SettingsBa;
import com.gmail.markushygedombrowski.utils.Utils;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class WarpSignFange implements Listener {

    private HLWarp hlWarp;
    private SettingsBa settings;

    public WarpSignFange(HLWarp hlWarp, SettingsBa settings) {
        this.hlWarp = hlWarp;
        this.settings = settings;
    }

    @EventHandler
    public void onPlayerClickSign(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.SIGN || event.getClickedBlock().getType() == Material.SIGN_POST || event.getClickedBlock().getType() == Material.WALL_SIGN) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLine(0).equalsIgnoreCase("§8===============") && sign.getLine(1).equalsIgnoreCase("§C§lBandeKrig") && sign.getLine(2).equalsIgnoreCase("§c§lFange") && sign.getLine(3).equalsIgnoreCase("§8===============")) {
                    if (!settings.isAktiv()) {
                        p.sendMessage("§cBandeKrigs event er ikke aktivt!");
                        return;
                    }
                    if (activeMessagePrBlock(p)) return;
                    p.teleport(hlWarp.getWarpManager().getWarpInfo("bandekrig").getLocation());
                    settings.addFange(p);



                }
            }
        }
    }

    private boolean activeMessagePrBlock(Player p) {
        if (settings.isAktivall()) {
            return false;
        } else if (Utils.isLocInRegion(p.getLocation(), "a")) {
            if (!settings.isAktiva()) {
                p.sendMessage("§cBandeKrigs event er ikke aktivt for §aA!");
                return true;
            }

        } else if (Utils.isLocInRegion(p.getLocation(), "b")) {
            if (!settings.isAktivb()) {
                p.sendMessage("§cBandeKrigs event er ikke aktivt for §bB!");
                return true;
            }
        } else if (Utils.isLocInRegion(p.getLocation(), "c")) {
            if (!settings.isAktivc()) {
                p.sendMessage("§cBandeKrigs event er ikke aktivt for §cC!");
                return true;
            }
        }
        return false;
    }

}
