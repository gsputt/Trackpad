package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ToughGloves extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("ToughGloves");
    public static final String IMG = trackpad.makePath(trackpad.TOUGH_GLOVES);
    public static final String OUTLINE = trackpad.makePath(trackpad.TOUGH_GLOVES_OUTLINE);

    public ToughGloves() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if(info.type == DamageInfo.DamageType.THORNS) //&& info.owner instanceof AbstractMonster && info.owner.hasPower(ThornsPower.POWER_ID))
        {
            this.flash();
            return 0;
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
        return new ToughGloves();
    }
}
