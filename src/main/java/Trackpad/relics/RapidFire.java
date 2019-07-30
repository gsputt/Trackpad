package Trackpad.relics;

import Trackpad.relicInterfaces.onAttackToChangeDamageWithTargetRelicHook;
import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class RapidFire extends CustomRelic implements onAttackToChangeDamageWithTargetRelicHook {

    // ID, images, text.
    public static final String ID = trackpad.makeID("RapidFire");
    public static final String IMG = trackpad.makePath(trackpad.RAPID_FIRE);
    public static final String OUTLINE = trackpad.makePath(trackpad.RAPID_FIRE_OUTLINE);


    public RapidFire() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public int changeAttackDamageWithTarget(DamageInfo info, int damageAmount, AbstractCreature target)
    {
        //System.out.println("Relic got called");
        if(info.owner != null && info.owner == AbstractDungeon.player && info.owner != target)
        {
            this.flash();
            return damageAmount + 2;
        }
        else {
            return damageAmount;
        }
    }

    /*@Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.owner == AbstractDungeon.player) {
            this.flash();
            return damageAmount + 2;
        }
        else {
            return damageAmount;
        }
    }*/


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new RapidFire();
    }
}
