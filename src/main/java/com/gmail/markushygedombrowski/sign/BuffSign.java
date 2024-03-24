package com.gmail.markushygedombrowski.sign;

import com.gmail.markushygedombrowski.settings.SettingsBa;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BuffSign implements Listener {
    private SettingsBa settings;

    public BuffSign(SettingsBa settings) {
        this.settings = settings;
    }

    @EventHandler
    public void onPlayerClickSign(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.SIGN || event.getClickedBlock().getType() == Material.SIGN_POST || event.getClickedBlock().getType() == Material.WALL_SIGN) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if(sign.getLine(0).equalsIgnoreCase("§8===============") && sign.getLine(1).equalsIgnoreCase("§C§lBandeKrig") && sign.getLine(2).equalsIgnoreCase("§c§lBuff") && sign.getLine(3).equalsIgnoreCase("§8===============")) {
                    if(!settings.isAktiv()) {
                        p.sendMessage("§cBandeKrigs event er ikke aktivt!");
                        return;
                    }
                    if(!p.hasPermission("vagtbuff")) {
                        p.sendMessage("§cDu har ikke permission til at bruge dette skilt!");
                        return;
                    }
                    p.removePotionEffect(PotionEffectType.ABSORPTION);
                    p.removePotionEffect(PotionEffectType.SPEED);
                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100000, settings.getApsorb()));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000,settings.getSpeed()));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, settings.getStrengh()));
                }
            }
        }
    }
}