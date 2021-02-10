package br.com.dvlopando.regionbdb.player;

import br.com.dvlopando.regionbdb.core.EpicenterRegion;
import br.com.dvlopando.regionbdb.effect.EPICENTER_EFFECT_TYPE;
import br.com.dvlopando.regionbdb.effect.EpicenterEffect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfo {
    private Player player;
    private List<EPICENTER_EFFECT_TYPE> actualEffects;
    private EpicenterRegion actualEpicenterRegion = null;
    public PlayerInfo(Player player)   {
        this.player = player;
        this.actualEffects = new ArrayList<EPICENTER_EFFECT_TYPE>();
    }
    public void removeEffect(EPICENTER_EFFECT_TYPE effectType)   {
        if (this.actualEffects.size() > 0)   {
            if (this.actualEffects.contains(effectType))   {
                this.actualEffects.remove(effectType);
                PotionEffectType potionEffectType = EpicenterEffect.getPotionEffect(effectType);
                this.player.removePotionEffect(potionEffectType);
            }
        }
    }
    public void removeAllActualEffects()   {
        if (this.actualEffects.size() > 0)   {
            for (EPICENTER_EFFECT_TYPE effectType : this.actualEffects)   {
                PotionEffectType potionEffectType = EpicenterEffect.getPotionEffect(effectType);
                this.player.removePotionEffect(potionEffectType);
            }
        }
    }
    public void addEffect(EPICENTER_EFFECT_TYPE effectType)   {
        if (!this.actualEffects.contains(effectType))   {
            this.actualEffects.add(effectType);
        }
    }
    public Player getPlayer()   {
        return this.player;
    }
    public EpicenterRegion getEpicenterRegion()   {
        return this.actualEpicenterRegion;
    }
    public void setActualEpicenterRegion(EpicenterRegion epicenterRegion)   {
        this.actualEpicenterRegion = epicenterRegion;
    }
}
