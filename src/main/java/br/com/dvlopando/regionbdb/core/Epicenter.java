package br.com.dvlopando.regionbdb.core;

import br.com.dvlopando.regionbdb.effect.EPICENTER_EFFECT_TYPE;
import br.com.dvlopando.regionbdb.player.PlayerInfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Epicenter {
    private boolean active;
    private Location epicenterLocation;
    private Map<String,EpicenterRegion> epicenterRegions;
    public Epicenter(Location epicenterLocation)    {
        this.epicenterLocation = epicenterLocation;
        this.epicenterRegions = new HashMap<String, EpicenterRegion>();
    }
    public Location getEpicenterLocation()   {
        return this.epicenterLocation;
    }
    public void setEpicenterLocation(Location tmpLocation)   {
        this.epicenterLocation = tmpLocation;
    }
    public boolean isActive()   {
        return this.active;
    }
    public void setActive(boolean active)   {
        this.active = active;
    }
    public void createRegion(String regionName, double distance)   {
        if (!this.epicenterRegions.containsKey(regionName))   {
            EpicenterRegion newRegion = new EpicenterRegion(regionName, distance,this);
            this.epicenterRegions.put(newRegion.getRegionName(), newRegion);
        }
    }
    public void createRegion(String regionName, double distance, EPICENTER_EFFECT_TYPE effectType)   {
        if (!this.epicenterRegions.containsKey(regionName))   {
            EpicenterRegion newRegion = new EpicenterRegion(regionName, distance, this);
            newRegion.addEffect(effectType);
            this.epicenterRegions.put(newRegion.getRegionName(), newRegion);
        }
    }
    public void editRegionDistance(String regionName, double distance)  {
        if (this.epicenterRegions.containsKey(regionName))   {
            this.epicenterRegions.get(regionName).setEpicenterDistance(distance);
        }
    }
    public void editRegionName(String actualName, String newName)   {
        if (this.epicenterRegions.containsKey(actualName))   {
            this.epicenterRegions.get(actualName).setRegionName(newName);
        }
    }
    public void addRegionEffect(String regionName, EPICENTER_EFFECT_TYPE effectType)   {
        if (this.epicenterRegions.containsKey(regionName))   {
            this.epicenterRegions.get(regionName).addEffect(effectType);
        }
    }
    public void removeRegionEffect(String regionName, EPICENTER_EFFECT_TYPE effectType)   {
        if (this.epicenterRegions.containsKey(regionName))   {
            this.epicenterRegions.get(regionName).removeEffect(effectType);
        }
    }
    public void removeRegion(String regionName)   {
        if (this.epicenterRegions.containsKey(regionName))   {
            this.epicenterRegions.remove(regionName);
        }
    }
    public void runRegion(PlayerInfo playerInfo)   {
        for (EpicenterRegion epicenterRegion : this.epicenterRegions.values())   {
            epicenterRegion.inRegion(playerInfo);
        }
    }
    public boolean hasRegion()   {
        if (this.epicenterRegions.size() > 0)   {
            return true;
        }
        return false;
    }
    public Map<String, EpicenterRegion> getEpicenterRegionsMap()   {
        return this.epicenterRegions;
    }
}
