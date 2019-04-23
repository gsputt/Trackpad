package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CultistFigurine extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("CultistFigurine");
    public static final String IMG = trackpad.makePath(trackpad.CULTIST_FIGURINE);
    public static final String OUTLINE = trackpad.makePath(trackpad.CULTIST_FIGURINE_OUTLINE);


    public CultistFigurine() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.SOLID);
        this.counter = 0;
    }

    @Override
    public void onEquip()
    {
        this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        this.counter += 1;
        if(this.counter >= 3)
        {
            this.counter = 0;
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            new StrengthPower(AbstractDungeon.player, 1), 1));
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
        return new CultistFigurine();
    }
}
