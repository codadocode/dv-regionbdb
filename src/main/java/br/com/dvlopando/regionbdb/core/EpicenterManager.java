package br.com.dvlopando.regionbdb.core;

import br.com.dvlopando.regionbdb.GlobalConfig;
import br.com.dvlopando.regionbdb.effect.EPICENTER_EFFECT_TYPE;
import br.com.dvlopando.regionbdb.player.PlayerInfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EpicenterManager {
    private Epicenter epicenter = null;
    private Map<Player, PlayerInfo> players;
    public EpicenterManager()   {
        this.players = new HashMap<Player, PlayerInfo>();
    }
    public void addPlayer(Player player)   {
        if (!this.players.containsKey(player))   {
            PlayerInfo playerInfo = new PlayerInfo(player);
            this.players.put(playerInfo.getPlayer(), playerInfo);
        }
    }
    public void removePlayer(Player player)   {
        if (this.players.containsKey(player))   {
            this.players.remove(player);
        }
    }
    public PlayerInfo getPlayerInfo(Player player)   {
        if (this.players.containsKey(player))   {
            return this.players.get(player);
        }
        return null;
    }
    public boolean cmdCreateEpicenter(Location epicenterLocation)   {
        if (!hasEpicenter())   {
            this.epicenter = new Epicenter(epicenterLocation);
            GlobalConfig.getServer().getLogger().info("Epicenter created with success!");
            return true;
        }
        return false;
    }
    public void cmdDeleteEpicenter()   {
        if (hasEpicenter())   {
            this.epicenter = null;
        }
    }
    public boolean cmdCreateRegion(String regionName, double distance, EPICENTER_EFFECT_TYPE effectType)   {
        if (hasEpicenter())   {
            this.epicenter.createRegion(regionName, distance, effectType);
            return true;
        }
        return false;
    }
    public void cmdEditRegionDistance(String regionName, double distance)   {
        if (hasEpicenter())   {
            this.epicenter.editRegionDistance(regionName, distance);
        }
    }
    public void cmdEditRegionName(String oldName, String newName)   {
        if (hasEpicenter())   {
            this.epicenter.editRegionName(oldName, newName);
        }
    }
    public void cmdAddRegionEffect(String regionName, EPICENTER_EFFECT_TYPE effectType)   {
        if (hasEpicenter())   {
            this.epicenter.addRegionEffect(regionName, effectType);
        }
    }
    public void cmdRemoveRegionEffect(String regionName, EPICENTER_EFFECT_TYPE effectType)   {
        if (hasEpicenter())   {
            this.epicenter.removeRegionEffect(regionName, effectType);
        }
    }
    public void cmdRemoveRegion(String regionName)   {
        if (hasEpicenter())   {
            this.epicenter.removeRegion(regionName);
        }
    }
    public void cmdRunRegion(PlayerInfo playerInfo)   {
        if (hasEpicenter())   {
            this.epicenter.runRegion(playerInfo);
        }
    }
    public boolean hasEpicenter()   {
        if (this.epicenter != null)   {
            return true;
        }
        return false;
    }
}
