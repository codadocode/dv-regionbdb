package br.com.dvlopando.regionbdb;

import br.com.dvlopando.regionbdb.core.EpicenterManager;
import br.com.dvlopando.regionbdb.effect.EPICENTER_EFFECT_TYPE;
import br.com.dvlopando.regionbdb.event.RegionEvent;
import br.com.dvlopando.regionbdb.event.RegisterPlayerEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RegionBDB extends JavaPlugin {
    private EpicenterManager manager;
    @Override
    public void onEnable() {
        super.onEnable();
        this.getServer().getLogger().info("Enabling Region BuffDBuff Plugin.");
        this.manager = new EpicenterManager();
        GlobalConfig.setServer(this.getServer());
        GlobalConfig.setEpicenterManager(this.manager);
        this.getServer().getPluginManager().registerEvents(new RegisterPlayerEvent(), this);
        this.getServer().getPluginManager().registerEvents(new RegionEvent(), this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player playerSender = (Player)sender;
        boolean result;
        switch (command.getName())   {
            case "epccreate":
                result = GlobalConfig.getEpicenterManager().cmdCreateEpicenter(playerSender.getLocation());
                if (result)   {
                    playerSender.sendMessage("Epicenter has been created!");
                }
                break;
            case "epcaddregion":
                if (args.length == 3)   {
                    String regionName = args[0];
                    double regionDistance = Double.valueOf(args[1]);
                    EPICENTER_EFFECT_TYPE effectType = stringToEffectType(args[2]);
                    result = GlobalConfig.getEpicenterManager().cmdCreateRegion(regionName, regionDistance, effectType);
                    if (result)   {
                        playerSender.sendMessage("Epicenter region " + regionName + " has been created!");
                    }
                }
                break;
        }
        return true;
    }
    @Override
    public void onDisable() {
        super.onDisable();
        this.getServer().getLogger().info("Disabling Region BuffDBuff Plugin.");
    }
    public EPICENTER_EFFECT_TYPE stringToEffectType(String effectName)   {
        switch(effectName)   {
            case "weakness":
                return EPICENTER_EFFECT_TYPE.WEAKNESS;
            case "blindness":
                return EPICENTER_EFFECT_TYPE.BLINDNESS;
            case "hunger":
                return EPICENTER_EFFECT_TYPE.HUNGER;
            case "poison":
                return EPICENTER_EFFECT_TYPE.POISON;
        }
        return null;
    }
}
