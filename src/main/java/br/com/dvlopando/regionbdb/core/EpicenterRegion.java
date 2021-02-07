package br.com.dvlopando.regionbdb.core;

import br.com.dvlopando.regionbdb.effect.EPICENTER_EFFECT_TYPE;
import br.com.dvlopando.regionbdb.effect.EpicenterEffect;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EpicenterRegion {
    private List<EpicenterEffect> effects;
    private double epicenterDistance;
    public EpicenterRegion(double epicenterDistance)   {
        this.epicenterDistance = epicenterDistance;
        this.effects = new ArrayList<EpicenterEffect>();
    }
    public void addEffect(EPICENTER_EFFECT_TYPE effectType)   {
        EpicenterEffect getEffect = checkEffectExists(effectType);
        if (getEffect == null)   {
            EpicenterEffect newEffect = new EpicenterEffect(effectType);
            this.effects.add(newEffect);
        }
    }
    public void addEffect(List<EPICENTER_EFFECT_TYPE> effectsType)   {
        for (EPICENTER_EFFECT_TYPE effectType : effectsType)   {
            EpicenterEffect getEffect = checkEffectExists(effectType);
            if (getEffect == null)  {
                EpicenterEffect newEffect = new EpicenterEffect(effectType);
                this.effects.add(newEffect);
            }
        }
    }
    public void removeEffect(EPICENTER_EFFECT_TYPE effectType)   {
        EpicenterEffect getEffect = checkEffectExists(effectType);
        if (getEffect != null)   {
            this.effects.remove(getEffect);
        }
    }
    public void removeEffect(List<EPICENTER_EFFECT_TYPE> effectsType)   {
        for (EPICENTER_EFFECT_TYPE effectType : effectsType)   {
            EpicenterEffect getEffect = checkEffectExists(effectType);
            if (getEffect != null)   {
                this.effects.remove(getEffect);
            }
        }
    }
    public void editEpicenterDistance(double distance)   {
        this.epicenterDistance = distance;
    }
    public EpicenterEffect checkEffectExists(EPICENTER_EFFECT_TYPE effectType)   {
        for (EpicenterEffect effect : this.effects)   {
            if (effect.getEffectType().equals(effectType))   {
                return effect;
            }
        }
        return null;
    }
    public double getEpicenterDistance()   {
        return this.epicenterDistance;
    }
    public void applyEffects(Player player)   {
        for (EpicenterEffect effect : this.effects)   {
            effect.applyEffects(player);
        }
    }
}
