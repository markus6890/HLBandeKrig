package com.gmail.markushygedombrowski;

import com.gmail.markushygedombrowski.commands.StartEventCommand;
import com.gmail.markushygedombrowski.listeners.DeathListener;
import com.gmail.markushygedombrowski.listeners.RespawnListener;
import com.gmail.markushygedombrowski.settings.ReloadCmd;
import com.gmail.markushygedombrowski.settings.SettingsBa;
import com.gmail.markushygedombrowski.sign.BuffSign;
import com.gmail.markushygedombrowski.sign.WarpSignFange;
import com.gmail.markushygedombrowski.sign.WarpSignVagt;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class HLBandeKrig extends JavaPlugin {
    private SettingsBa settings;
    private FileConfiguration config;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        HLWarp hlWarp = HLWarp.getInstance();
        settings = new SettingsBa(hlWarp);
        settings.load(config);
        BuffSign buffSigns = new BuffSign(settings);
        getServer().getPluginManager().registerEvents(buffSigns, this);
        ReloadCmd reloadCmd = new ReloadCmd(this);
        getCommand("reloadbande").setExecutor(reloadCmd);
        DeathListener deathListener = new DeathListener(this);
        getServer().getPluginManager().registerEvents(deathListener, this);
        RespawnListener respawnListener = new RespawnListener(hlWarp, this);
        getServer().getPluginManager().registerEvents(respawnListener, this);
        WarpSignVagt warpSignVagt = new WarpSignVagt(hlWarp, settings);
        getServer().getPluginManager().registerEvents(warpSignVagt, this);
        StartEventCommand startEventCommand = new StartEventCommand(settings, hlWarp);
        getCommand("startbandekrig").setExecutor(startEventCommand);
        WarpSignFange warpSignFange = new WarpSignFange(hlWarp, settings);
        getServer().getPluginManager().registerEvents(warpSignFange, this);

        System.out.println("====================================");
        System.out.println("HLBandeKrig enabled");
        System.out.println("====================================");


    }
    public void reload() {
        reloadConfig();
        config = getConfig();
        settings.load(config);
    }
    public void onDisable() {

    }
}
