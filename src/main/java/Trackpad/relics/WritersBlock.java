package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class WritersBlock extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("WritersBlock");
    public static final String IMG = trackpad.makePath(trackpad.WRITERS_BLOCK);
    public static final String OUTLINE = trackpad.makePath(trackpad.WRITERS_BLOCK_OUTLINE);

    public boolean trigger = false;

    public WritersBlock() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.SOLID);
    }

    @Override
    public void onPlayerEndTurn() {
        if (this.trigger) {
            this.trigger = false;
            this.flash();
            this.stopPulse();
            AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 6));
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }

    }

    @Override
    public void atTurnStart() {
        this.trigger = true;
        this.beginLongPulse();
    }

    @Override
    public void onVictory()
    {
        this.stopPulse();
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if(c.type == AbstractCard.CardType.SKILL)
        {
            this.stopPulse();
            this.trigger = false;
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
        return new WritersBlock();
    }
}
