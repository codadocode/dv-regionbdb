package br.com.dvlopando.regionbdb.core;

import br.com.dvlopando.regionbdb.GlobalConfig;
import br.com.dvlopando.regionbdb.effect.EPICENTER_EFFECT_TYPE;
import br.com.dvlopando.regionbdb.effect.EpicenterEffect;
import br.com.dvlopando.regionbdb.player.PlayerInfo;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EpicenterRegion {
    private Epicenter epicenter;
    private String regionName;
    private boolean active;
    private double distance;
    private List<EpicenterEffect> effects;
    private List<PlayerInfo> insidePlayers;
    public EpicenterRegion(String regionName, double distance, Epicenter epicenter)   {
        this.epicenter = epicenter;
        this.regionName = regionName;
        this.distance = distance;
        this.active = true;
        this.effects = new ArrayList<EpicenterEffect>();
        this.insidePlayers = new ArrayList<PlayerInfo>();
    }
    public double getEpicenterDistance()   {
        return this.distance;
    }
    public void setEpicenterDistance(double distance)   {
        this.distance = distance;
    }
    public void setRegionName(String regionName)   {
        this.regionName = regionName;
    }
    public String getRegionName()   {
        return this.regionName;
    }
    public boolean isActive()   {
        return this.active;
    }
    public void setActive(boolean active)   {
        this.active = active;
    }
    public void addPlayer(PlayerInfo playerInfo)   {
        this.insidePlayers.add(playerInfo);
        playerInfo.setActualEpicenterRegion(this);
        applyEffect(playerInfo);
    }
    public void removePlayer(PlayerInfo playerInfo)   {
        if (this.insidePlayers.contains(playerInfo))  {
            this.insidePlayers.remove(playerInfo);
            playerInfo.setActualEpicenterRegion(null);
        }
    }
    public boolean inRegion(PlayerInfo playerInfo)   {
        double playerDistance = playerInfo.getPlayer().getLocation().distance(epicenter.getEpicenterLocation());
        List<EpicenterRegion> tmpEpicenterRegionList = new ArrayList<EpicenterRegion>(this.epicenter.getEpicenterRegionsMap().values());
        int indexInList = tmpEpicenterRegionList.indexOf(this);
        if (this.insidePlayers.contains(playerInfo))   {
            if (indexInList == (tmpEpicenterRegionList.size() - 1))   {
                if (playerDistance < this.distance)   {
                    removePlayer(playerInfo);
                    for (EpicenterEffect epicenterEffect : this.effects)   {
                        playerInfo.removeEffect(epicenterEffect.getEffectType());
                    }
                    return false;
                }
            }else   {
                if (playerDistance < this.distance || playerDistance > tmpEpicenterRegionList.get(indexInList + 1).getEpicenterDistance())   {
                    removePlayer(playerInfo);
                    for (EpicenterEffect epicenterEffect : this.effects)   {
                        playerInfo.removeEffect(epicenterEffect.getEffectType());
                    }
                    return false;
                }
            }
            return true;
        }else   {
            if (indexInList == (tmpEpicenterRegionList.size() - 1))   {
                if (playerDistance > this.distance)   {
                    addPlayer(playerInfo);
                    return true;
                }
            }else   {
                if (playerDistance > this.distance && playerDistance <= tmpEpicenterRegionList.get(indexInList + 1).getEpicenterDistance())   {
                    addPlayer(playerInfo);
                    return true;
                }
            }
        }
        return false;
    }
    /*
    public boolean hasEffect(PlayerInfo playerInfo, EPICENTER_EFFECT_TYPE effectType)   {

    }
    */
    public void applyEffect(PlayerInfo playerInfo)   {
        if (this.effects.size() > 0)   {
            playerInfo.removeAllActualEffects();
            for (EpicenterEffect actualEffect : this.effects)   {
                playerInfo.addEffect(actualEffect.getEffectType());
                actualEffect.applyEffect(playerInfo.getPlayer());
            }
        }
    }
    public void addEffect(EPICENTER_EFFECT_TYPE effectType)   {
        if (!this.effects.contains(effectType))   {
            this.effects.add(new EpicenterEffect(effectType));
        }
    }
    public void removeEffect(EPICENTER_EFFECT_TYPE effectType)   {
        if (this.effects.contains(effectType))   {
            this.effects.remove(effectType);
        }
    }
}
