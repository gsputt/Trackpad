package Trackpad.relicInterfaces;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public interface onAttackToChangeDamageWithTargetRelicHook {
    int changeAttackDamageWithTarget(DamageInfo info, int damageAmount, AbstractCreature target);
}
