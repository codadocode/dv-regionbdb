package br.com.dvlopando.regionbdb.core;

import br.com.dvlopando.regionbdb.effect.EPICENTER_EFFECT_TYPE;
import org.bukkit.entity.Player;

import java.util.List;

public class EpicenterManager {
    private Epicenter epicenter = null;
    public void createEpicenter(Player player)   {
        if (!hasEpicenter())   {
            this.epicenter = new Epicenter(player.getLocation());
        }
    }
    public void deleteEpicenter()   {
        if (hasEpicenter())   {
            this.epicenter = null;
        }
    }
    public void createEpicenterRegion(Player player, double distance, List<EPICENTER_EFFECT_TYPE>effectsTypes)   {
        if (hasEpicenter())   {
            List<EpicenterRegion> epicenterRegions = getEpicenterRegion(player);
            if (epicenterRegions.size() > 0)   {
                for (EpicenterRegion epicenterRegion : epicenterRegions)   {
                    distance += epicenterRegion.getEpicenterDistance();
                }
            }
            EpicenterRegion newEpicenterRegion = new EpicenterRegion(distance);
            newEpicenterRegion.addEffect(effectsTypes);
            this.epicenter.addRegion(newEpicenterRegion);
        }
    }
    public void createEpicenterRegion(Player player, double distance, EPICENTER_EFFECT_TYPE effectType)   {
        if (hasEpicenter())   {
            List<EpicenterRegion> epicenterRegions = getEpicenterRegion(player);
            if (epicenterRegions.size() > 0)   {
                for (EpicenterRegion epicenterRegion : epicenterRegions)   {
                    distance += epicenterRegion.getEpicenterDistance();
                }
            }
            EpicenterRegion newEpicenterRegion = new EpicenterRegion(distance);
            newEpicenterRegion.addEffect(effectType);
            this.epicenter.addRegion(newEpicenterRegion);
        }
    }
    public void removeEpicenterRegion(Player player)   {
        if (hasEpicenter())   {
            List<EpicenterRegion> epicenterRegions = getEpicenterRegion(player);
            if (epicenterRegions.size() > 0)   {
                this.epicenter.removeRegion(epicenterRegions.get(epicenterRegions.size() - 1));
            }
        }
    }
    public void editEpicenterRegionDistance(Player player, double distance)   {
        if (hasEpicenter())   {
            List<EpicenterRegion> epicenterRegions = getEpicenterRegion(player);
            if (epicenterRegions.size() > 0)   {
                epicenterRegions.get(epicenterRegions.size() - 1).editEpicenterDistance(distance);
            }
        }
    }
    public List<EpicenterRegion> getEpicenterRegion(Player player)   {
        if (hasEpicenter())   {
            return this.epicenter.getRegion(player);
        }
        return null;
    }
    public void addEpicenterEffect(Player player, EPICENTER_EFFECT_TYPE effectType)   {
        if (hasEpicenter())   {
            List<EpicenterRegion> epicenterRegions = getEpicenterRegion(player);
            if (epicenterRegions.size() > 0)   {
                epicenterRegions.get(epicenterRegions.size() - 1).addEffect(effectType);
            }
        }
    }
    public void addEpicenterEffect(Player player, List<EPICENTER_EFFECT_TYPE> effectsType)   {
        if (hasEpicenter())   {
            List<EpicenterRegion> epicenterRegions = getEpicenterRegion(player);
            if (epicenterRegions.size() > 0)   {
                epicenterRegions.get(epicenterRegions.size() - 1).addEffect(effectsType);
            }
        }
    }
    public void removeEpicenterEffect(Player player, List<EPICENTER_EFFECT_TYPE> effectsType)   {
        if (hasEpicenter())   {
            List<EpicenterRegion> epicenterRegions = getEpicenterRegion(player);
            if (epicenterRegions.size() > 0)   {
                epicenterRegions.get(epicenterRegions.size() - 1).removeEffect(effectsType);
            }
        }
    }
    public void removeEpicenterEffect(Player player, EPICENTER_EFFECT_TYPE effectType)   {
        if (hasEpicenter())   {
            List<EpicenterRegion> epicenterRegions = getEpicenterRegion(player);
            if (epicenterRegions.size() > 0)   {
                epicenterRegions.get(epicenterRegions.size() - 1).addEffect(effectType);
            }
        }
    }
    public void checkPlayer(Player player)   {
        if (hasEpicenter())   {
            List<EpicenterRegion> epicenterRegions = getEpicenterRegion(player);
            if (epicenterRegions.size() > 0)   {
                for (EpicenterRegion epicenterRegion : epicenterRegions)   {
                    epicenterRegion.applyEffects(player);
                }
            }
        }
    }
    public EPICENTER_EFFECT_TYPE stringToEpicenterEffect(String effect)   {
        if (hasEpicenter())   {
            switch (effect)   {
                case "nausea":
                    return EPICENTER_EFFECT_TYPE.NAUSEA;
                case "blindness":
                    return EPICENTER_EFFECT_TYPE.BLINDNESS;
                case "poison":
                    return EPICENTER_EFFECT_TYPE.POISON;
                case "weakness":
                    return EPICENTER_EFFECT_TYPE.WEAKNESS;
                case "hunger":
                    return EPICENTER_EFFECT_TYPE.HUNGER;
            }
        }
        return null;
    }
    public boolean hasEpicenter()   {
        if (this.epicenter != null)   {
            return true;
        }
        return false;
    }
}
