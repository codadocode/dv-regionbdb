package br.com.dvlopando.regionbdb.event;

import br.com.dvlopando.regionbdb.GlobalConfig;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;

public class RegionEvent implements Listener {
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event)   {
        GlobalConfig.getEpicenterManager().cmdRunRegion(GlobalConfig.getEpicenterManager().getPlayerInfo(event.getPlayer()));
    }
}
