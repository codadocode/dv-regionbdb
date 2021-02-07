package br.com.dvlopando.regionbdb.effect;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EpicenterEffect {
    private EPICENTER_EFFECT_TYPE effectType;
    public EpicenterEffect(EPICENTER_EFFECT_TYPE effectType)   {
        this.effectType = effectType;
    }
    public void applyEffects(Player player)   {
        switch (effectType)   {
            case NAUSEA:
                break;
            case BLINDNESS:
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
                break;
            case HUNGER:
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 60, 1));
                break;
            case POISON:
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
                break;
            case WEAKNESS:
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 60, 1));
                break;
        }
    }
        public EPICENTER_EFFECT_TYPE getEffectType()   {
        return this.effectType;
    }
}
