package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class RapidFire extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("RapidFire");
    public static final String IMG = trackpad.makePath(trackpad.RAPID_FIRE);
    public static final String OUTLINE = trackpad.makePath(trackpad.RAPID_FIRE_OUTLINE);


    public RapidFire() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public int onAttackedMonster(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
            this.flash();
            return damageAmount + 2;
        }
        else {
            return damageAmount;
        }
    }


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
