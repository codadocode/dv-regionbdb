package br.com.dvlopando.regionbdb.core;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Epicenter {
    private Location epicenterLocation;
    private boolean active;
    private List<EpicenterRegion> epicenterRegions;
    public Epicenter(Location tmpLocation)   {
        this.epicenterLocation = tmpLocation;
        this.active = true;
        this.epicenterRegions = new ArrayList<EpicenterRegion>();
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
    public List<EpicenterRegion> getRegion(Player player)   {
        List<EpicenterRegion> tmpEpicenterRegions = new ArrayList<EpicenterRegion>();
        for (EpicenterRegion epicenterRegion : this.epicenterRegions)   {
            if (player.getLocation().distance(this.epicenterLocation) > epicenterRegion.getEpicenterDistance())   {
                tmpEpicenterRegions.add(epicenterRegion);
            }
        }
        return tmpEpicenterRegions;
    }
    public void applyEpicenterRegionEffect(Player player,List<EpicenterRegion> epicenterRegions)   {
        for (EpicenterRegion epicenterRegion : epicenterRegions)   {
            epicenterRegion.applyEffects(player);
        }
    }
    public void removeRegion(EpicenterRegion epicenterRegion)   {
        if (this.epicenterRegions.contains(epicenterRegion))   {
            this.epicenterRegions.remove(epicenterRegion);
        }
    }
    public void addRegion(EpicenterRegion newEpicenterRegion)   {
        this.epicenterRegions.add(newEpicenterRegion);
    }
}
