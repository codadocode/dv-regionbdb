package br.com.dvlopando.regionbdb.event;

import br.com.dvlopando.regionbdb.GlobalConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RegisterPlayerEvent implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event)   {
        GlobalConfig.getEpicenterManager().addPlayer(event.getPlayer());
        GlobalConfig.getServer().getLogger().info("Player " + event.getPlayer().getName() + " has been registered on EpicenterManager!");
    }
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event)   {
        GlobalConfig.getEpicenterManager().removePlayer(event.getPlayer());
        GlobalConfig.getServer().getLogger().info("Player " + event.getPlayer().getName() + " has been unregistered on EpicenterManager!");
    }
}
