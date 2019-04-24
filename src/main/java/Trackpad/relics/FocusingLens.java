package Trackpad.relics;

import Trackpad.powers.FocusingLensPower;
import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FocusingLens extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("FocusingLens");
    public static final String IMG = trackpad.makePath(trackpad.FOCUSING_LENS);
    public static final String OUTLINE = trackpad.makePath(trackpad.FOCUSING_LENS_OUTLINE);


    public FocusingLens() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.CLINK);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if(target != AbstractDungeon.player && info.type == DamageInfo.DamageType.NORMAL && !target.hasPower(FocusingLensPower.POWER_ID))
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(target, this));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(target, AbstractDungeon.player,
                            new FocusingLensPower(target)));
        }
    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new FocusingLens();
    }
}
