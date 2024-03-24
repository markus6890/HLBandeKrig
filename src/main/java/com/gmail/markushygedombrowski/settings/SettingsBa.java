package com.gmail.markushygedombrowski.settings;

import com.gmail.markushygedombrowski.HLWarp;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsBa {
    private int apsorb;
    private int strengh;
    private int speed;
    private boolean aktivall = false;
    private boolean aktivc = false;
    private boolean aktivb = false;
    private boolean aktiva = false;
    private boolean aktiv = false;
    private List<Player> fangeListe = new ArrayList<>();
    private HLWarp hlwarp;

    public SettingsBa(HLWarp hlwarp) {
        this.hlwarp = hlwarp;
    }

    public void load(FileConfiguration config) {
        this.apsorb = config.getInt("buff.buffamplifer.absorption");
        this.strengh = config.getInt("buff.buffamplifer.strengh");
        this.speed = config.getInt("buff.buffamplifer.speed");


    }
    public int getApsorb() {
        return apsorb;
    }
    public int getStrengh() {
        return strengh;
    }
    public int getSpeed() {
        return speed;
    }
    public boolean isAktivall() {
        return aktivall;
    }
    public void setAktivall(boolean aktiv) {
        this.aktivall = aktiv;
    }
    public boolean isAktivc() {
        return aktivc;
    }
    public void setAktivc(boolean aktivc) {
        this.aktivc = aktivc;
    }
    public boolean isAktivb() {
        return aktivb;
    }
    public void setAktivb(boolean aktivb) {
        this.aktivb = aktivb;
    }
    public boolean isAktiva() {
        return aktiva;
    }
    public void setAktiva(boolean aktiva) {
        this.aktiva = aktiva;
    }
    public boolean isAktiv() {
        return aktiv;
    }
    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }
    public Player getFange(String UUID) {
        for(Player p : fangeListe) {
            if(Objects.equals(p.getUniqueId(), java.util.UUID.fromString(UUID))) {
                return p;
            }
        }
        return null;
    }
    public void addFange(Player p) {
        fangeListe.add(p);
    }
    public void removeFange(Player p) {
        fangeListe.remove(p);
    }
    public List<Player> getFangeListe() {
        return fangeListe;
    }
    public void tpAllFange(String region) {
        for(Player p : fangeListe) {
            p.teleport(hlwarp.getWarpManager().getWarpInfo(region).getLocation());
        }
    }
    public void clearFangeListe() {
        fangeListe.clear();
    }
}
