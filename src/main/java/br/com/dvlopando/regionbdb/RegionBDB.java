package br.com.dvlopando.regionbdb;

import br.com.dvlopando.regionbdb.core.EpicenterManager;
import br.com.dvlopando.regionbdb.effect.EPICENTER_EFFECT_TYPE;
import br.com.dvlopando.regionbdb.event.RegionEvent;
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
        this.getServer().getPluginManager().registerEvents(new RegionEvent(), this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player playerSender = (Player)sender;
        if (command.getName().equals("epc"))   {
            String function = args[0];
            switch (function)   {
                case "addregion":
                    if (args.length == 3)   {
                        double distance = Double.valueOf(args[1]);
                        EPICENTER_EFFECT_TYPE effectType = this.manager.stringToEpicenterEffect(args[2]);
                        if (effectType != null)   {
                            this.manager.createEpicenterRegion(playerSender, distance, effectType);
                        }
                    }
                    break;
                case "createepc":
                    this.manager.createEpicenter(playerSender);
                    break;
                case "addeffect":
                    if (args.length == 2)   {
                        EPICENTER_EFFECT_TYPE effectType = this.manager.stringToEpicenterEffect(args[1]);
                        if (effectType != null)   {
                            this.manager.addEpicenterEffect(playerSender, effectType);
                        }
                    }
                    break;
            }
        }
        return super.onCommand(sender, command, label, args);
    }
    @Override
    public void onDisable() {
        super.onDisable();
        this.getServer().getLogger().info("Disabling Region BuffDBuff Plugin.");
    }
}
