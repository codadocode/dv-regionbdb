package br.com.dvlopando.regionbdb.effect;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EpicenterEffect {
    private EPICENTER_EFFECT_TYPE effectType;
    public EpicenterEffect(EPICENTER_EFFECT_TYPE effectType)   {
        this.effectType = effectType;
    }
    public void applyEffect(Player player)   {
        switch (effectType)   {
            case NAUSEA:
                break;
            case BLINDNESS:
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1));
                break;
            case HUNGER:
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, Integer.MAX_VALUE, 1));
                break;
            case POISON:
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, 1));
                break;
            case WEAKNESS:
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1));
                break;
        }
    }
    public static PotionEffectType getPotionEffect(EPICENTER_EFFECT_TYPE effectType)   {
        switch (effectType)   {
            case NAUSEA:
                break;
            case BLINDNESS:
                return PotionEffectType.BLINDNESS;
            case HUNGER:
                return PotionEffectType.HUNGER;
            case POISON:
                return PotionEffectType.POISON;
            case WEAKNESS:
                return PotionEffectType.WEAKNESS;
        }
        return null;
    }
        public EPICENTER_EFFECT_TYPE getEffectType()   {
        return this.effectType;
    }
}
